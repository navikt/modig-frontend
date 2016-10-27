'use strict';

Object.defineProperty(exports, "__esModule", {
    value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var NavDatePickers = [];

var DatePicker = function () {
    function DatePicker(element, options) {
        var _this = this;

        _classCallCheck(this, DatePicker);

        DatePicker.INIT_COUNTER++;

        this.props = {
            monthNames: ['Januar', 'Februar', 'Mars', 'April', 'Mai', 'Juni', 'Juli', 'August', 'September', 'Oktober', 'November', 'Desember'],
            dayNames: ['', 'Mandag', 'Tirsdag', 'Onsdag', 'Torsdag', 'Fredag', 'Lørdag', 'Søndag'],
            dateFormat: 'dd.mm.åååå',
            inputLabel: 'Datoformat er dd.mm.åååå, f.eks 24.12.2020. Men alle feltskilletegn blir godtatt',
            inputFormat: /^\d\d\.\d\d\.\d\d\d\d$/,
            bn_dateTitle: 'Velg dato...',
            errMsg: 'Javascript må være aktivert',
            bn_prevLabel: 'Gå til forrige måned',
            bn_nextLabel: 'Gå til neste måned',
            bn_infoLabel: 'Vis tastatursnarveier',
            keyboard: {
                heading: 'Tastatursnarveier - Gå til...',
                arrowLeftRight: { win: 'Pil venstre/høyre', mac: 'Pil venstre/høyre', text: 'forrige/neste dag' },
                arrowRight: { win: 'Pil høyre', mac: 'Pil høyre', text: 'neste dag' },
                arrowUpDown: { win: 'Pil opp/ned', mac: 'Pil opp/ned', text: 'forrige/neste uke' },
                pageUpDown: { win: 'pgUp/pgDown', mac: 'Fn + Pil opp/ned', text: 'forrige/neste måned' },
                ctrlPageUpDown: { win: 'Shift + pgUp/pgDown', mac: 'Ctrl + Fn + Pil opp/ned', text: 'forrige/neste år' },
                homeEnd: { win: 'Home/End', mac: 'Ctrl + Fn + Pil venstre/høyre', text: 'første/siste dag i måneden' }
            }
        };
        if (options.props !== undefined) {
            this.props = options.props;
        }

        this.id = 'dp-' + DatePicker.INIT_COUNTER.toString();
        this.input = element; // element to attach widget to
        this.input.setAttribute('id', this.id);
        this.input.setAttribute('placeholder', this.props.dateFormat);
        element.parentNode.style.whiteSpace = 'nowrap';
        element.parentNode.style.position = 'relative';
        this.label = element.parentNode.querySelector('label');
        this.label.setAttribute('for', this.id);

        this.isMac = navigator.platform.indexOf('Mac') > -1;
        this.isDesktop = !('ontouchstart' in document.documentElement);
        this.hasNativeDateSupport = this.input.type === 'date';
        this.useFallback = this.isDesktop || !this.hasNativeDateSupport;

        if (this.useFallback) {
            this.input.setAttribute('type', 'text');
        } else {
            return;
        }

        var minarr = [];
        this.minDate = null;
        if (options.minDate !== undefined) {
            minarr = options.minDate.split('.');
            this.minDate = new Date(minarr[2], minarr[1] - 1, minarr[0]);
        }
        var maxarr = [];
        this.maxDate = null;
        if (options.maxDate !== undefined) {
            maxarr = options.maxDate.split('.');
            this.maxDate = new Date(maxarr[2], maxarr[1] - 1, maxarr[0]);
        }

        this.input.setAttribute('aria-label', this.props.inputLabel);
        this.input.addEventListener('click', function (e) {
            _this.hideDialog();
            e.stopPropagation();
            return false;
        });
        this.input.addEventListener('blur', function () {
            this.value = DatePicker.formatDate(this.value);
        });
        this.input.insertAdjacentHTML('afterend', this.getMarkup(this.isMac ? 'mac' : 'win'));

        var counter = 'datovelger_' + this.id;
        this.parent = document.getElementById('counterid');
        this.parent.setAttribute('id', counter);

        var events = 'click keydown'.split(' ');

        var _loop = function _loop(i) {
            _this.parent.querySelector('.bn_date').addEventListener(events[i], function (e) {
                var isButtonEvent = e.keyCode === _this.keys.enter || e.keyCode === _this.keys.space;
                if (events[i] === 'keydown' && !isButtonEvent) {
                    return true;
                }
                if (_this.calwrap.getAttribute('aria-hidden') === 'true') {
                    _this.showDialog();
                } else {
                    _this.hideDialog();
                }
                e.stopPropagation();
                return false;
            });
        };

        for (var i = 0; i < events.length; i++) {
            _loop(i);
        }

        this.calwrap = this.parent.querySelector('.cal-wrap');
        this.info = this.parent.querySelector('.bn_info');
        this.mnth = this.parent.querySelector('.month');
        this.mnth.setAttribute('id', this.id + '_month');
        this.prev = this.parent.querySelector('.bn_prev');
        this.next = this.parent.querySelector('.bn_next');
        this.bModal = options.modal; // true if datepicker should appear in a modal dialog box.
        this.grid = null;

        this.keys = {
            tab: 9,
            enter: 13,
            esc: 27,
            space: 32,
            pageup: 33,
            pagedown: 34,
            end: 35,
            home: 36,
            left: 37,
            up: 38,
            right: 39,
            down: 40
        };

        this.keyhandlers = {};
        this.keyhandlers[this.keys.tab] = this.tabHandler;
        this.keyhandlers[this.keys.space] = this.updateTargetBox;
        this.keyhandlers[this.keys.enter] = this.updateTargetBox;
        this.keyhandlers[this.keys.esc] = this.dismissDialog;
        this.keyhandlers[this.keys.left] = this.previousday;
        this.keyhandlers[this.keys.right] = this.nextday;
        this.keyhandlers[this.keys.up] = this.previousweek;
        this.keyhandlers[this.keys.down] = this.nextweek;
        this.keyhandlers[this.keys.pageup] = this.previousMonthOrYear;
        this.keyhandlers[this.keys.pagedown] = this.nextMonthOrYear;
        this.keyhandlers[this.keys.home] = this.startOfMonth;
        this.keyhandlers[this.keys.end] = this.endOfMonth;

        this.updateDateValues(new Date());
    }

    _createClass(DatePicker, [{
        key: 'updateDateValues',
        value: function updateDateValues(date) {
            this.dateObj = date;
            this.curYear = this.dateObj.getFullYear();
            this.year = this.curYear;
            this.curMonth = this.dateObj.getMonth();
            this.month = this.curMonth;
            this.isCurrentDate = true;
            this.date = this.dateObj.getDate();

            // display the current month
            this.mnth.innerHTML = this.props.monthNames[this.month] + ' ' + this.year;

            // populate the calendar grid
            this.popGrid();

            // update the table's activedescdendant to point to the current day
            this.grid.setAttribute('aria-activedescendant', this.grid.querySelector('.today').getAttribute('class'));

            this.info = this.parent.querySelector('.bn_info');
            this.mnth = this.parent.querySelector('.month');
            this.mnth.setAttribute('id', this.id + '_month');
            this.prev = this.parent.querySelector('.bn_prev');
            this.next = this.parent.querySelector('.bn_next');
            this.bindHandlers();

            // hide dialog if in modal mode
            if (this.bModal === true) {
                this.calwrap.setAttribute('aria-hidden', 'true');
            }
        }

        //
        // popGrid() is a member function to populate the datepicker grid with calendar days representing the current month
        //
        // @return N/A
        //

    }, {
        key: 'popGrid',
        value: function popGrid() {
            var numDays = DatePicker.calcNumDays(this.year, this.month);
            var startWeekday = DatePicker.calcStartWeekday(this.year, this.month);
            var weekday = void 0;
            var curDay = void 0;
            var rowCount = 1;
            var gridCells = '<tr id="' + this.id + '_row1">\n';

            // Insert the leading empty cells
            for (weekday = 0; weekday < startWeekday; weekday++) {
                if (weekday === 0) {
                    var curDate = new Date(this.year, this.month, 1);
                    gridCells += '<td class="ukenr">' + DatePicker.calcWeekNumber(curDate) + '</td>\n';
                }
                gridCells += '<td class="empty"></td>\n';
            }

            // insert the days of the month.
            for (curDay = 1; curDay <= numDays; curDay++) {
                var _curDate = new Date(this.year, this.month, curDay);
                if (weekday === 0) {
                    var sunday = new Date(this.year, this.month, curDay + 6);
                    gridCells += '<td class="ukenr">' + DatePicker.calcWeekNumber(sunday) + '</td>\n';
                }

                var classes = [];
                if (curDay === this.date && this.isCurrentDate === true) {
                    classes.push('today');
                }
                if (this.minDate !== null) {
                    if (_curDate.getTime() < this.minDate.getTime()) {
                        classes.push('inactive');
                    }
                }
                if (this.maxDate !== null) {
                    if (_curDate.getTime() > this.maxDate.getTime()) {
                        classes.push('inactive');
                    }
                }

                var id = this.id + '_day' + curDay;
                var cssClasses = classes.join(' ');
                var headers = this.id + '_row' + rowCount + ' ' + this.id + '_' + this.props.dayNames[weekday + 1];

                gridCells += '<td id="' + id + '" class="' + cssClasses + '" headers="' + headers + '" role="gridcell" aria-selected="false">' + curDay + '</td>\n';

                if (weekday === 6 && curDay < numDays) {
                    // This was the last day of the week, close it out
                    // and begin a new one
                    gridCells += '</tr><tr id="' + this.id + '_row' + (rowCount + 1) + '">\n';
                    rowCount++;
                    weekday = 0;
                } else {
                    weekday++;
                }
            }

            // Insert any trailing empty cells
            for (weekday; weekday < 7; weekday++) {
                gridCells += '<td class="empty"></td>\n';
            }
            gridCells += '</tr>\n';
            if (gridCells === undefined) {
                gridCells = '<tr>\n<td class="errMsg" colspan="8">${this.props.errMsg}</td>\n</tr>';
            }

            var activedescendant = 'errMsg';
            if (this.grid !== null) {
                activedescendant = this.grid.getAttribute('aria-activedescendant');
            }
            var table = '<table class="cal" role="grid" aria-activedescendant="' + activedescendant + '" aria-labelledby="' + this.id + '_month" tabindex="0">\n\t\t\t\t<thead>\n\t\t\t\t\t<tr class="ukedager">\n\t\t\t\t\t\t<th></th>\n\t\t\t\t\t\t<th id="' + this.id + '_' + this.props.dayNames[1] + '"><abbr title="' + this.props.dayNames[1] + '">' + this.props.dayNames[1].substring(0, 3) + '</abbr></th>\n\t\t\t\t\t\t<th id="' + this.id + '_' + this.props.dayNames[2] + '"><abbr title="' + this.props.dayNames[2] + '">' + this.props.dayNames[2].substring(0, 3) + '</abbr></th>\n\t\t\t\t\t\t<th id="' + this.id + '_' + this.props.dayNames[3] + '"><abbr title="' + this.props.dayNames[3] + '">' + this.props.dayNames[3].substring(0, 3) + '</abbr></th>\n\t\t\t\t\t\t<th id="' + this.id + '_' + this.props.dayNames[4] + '"><abbr title="' + this.props.dayNames[4] + '">' + this.props.dayNames[4].substring(0, 3) + '</abbr></th>\n\t\t\t\t\t\t<th id="' + this.id + '_' + this.props.dayNames[5] + '"><abbr title="' + this.props.dayNames[5] + '">' + this.props.dayNames[5].substring(0, 3) + '</abbr></th>\n\t\t\t\t\t\t<th id="' + this.id + '_' + this.props.dayNames[6] + '"><abbr title="' + this.props.dayNames[6] + '">' + this.props.dayNames[6].substring(0, 3) + '</abbr></th>\n\t\t\t\t\t\t<th id="' + this.id + '_' + this.props.dayNames[7] + '"><abbr title="' + this.props.dayNames[7] + '">' + this.props.dayNames[7].substring(0, 3) + '</abbr></th>\n\t\t\t\t\t</tr>\n\t\t\t\t</thead>\n\t\t\t\t<tbody>\n\t\t\t\t\t' + gridCells + '\n\t\t\t\t</tbody>\n\t\t\t</table>';

            if (this.grid !== null) {
                this.grid.parentNode.removeChild(this.grid);
            }
            this.parent.querySelector('#bn_prev-label').insertAdjacentHTML('beforebegin', table);
            this.grid = this.parent.querySelector('.cal');
            this.grid.focus();
            this.bindHandlers();
        }
    }, {
        key: 'showPrevMonth',


        //
        // showPrevMonth() is a member function to show the previous month
        //
        // @param (offset int) offset may be used to specify an offset for setting
        //                      focus on a day the specified number of days from
        //                      the end of the month.
        // @return N/A
        //
        value: function showPrevMonth(offset) {
            DatePicker.setPrevState();
            if (this.minDate !== null) {
                if (new Date(this.year, this.month, 1).getTime() <= new Date(this.minDate.getFullYear(), this.minDate.getMonth(), 1).getTime()) {
                    return true;
                }
            }

            // show the previous month
            if (this.month === 0) {
                this.month = 11;
                this.year--;
            } else {
                this.month--;
            }

            this.isCurrentDate = !(this.month !== this.curMonth || this.year !== this.curYear);

            // populate the calendar grid
            this.popGrid();

            this.mnth.innerHTML = this.props.monthNames[this.month] + ' ' + this.year;

            // if offset was specified, set focus on the last day - specified offset
            if (offset !== undefined) {
                var numDays = DatePicker.calcNumDays(this.year, this.month);
                var dayid = this.id + '_day' + (numDays - offset);
                this.grid.setAttribute('aria-activedescendant', dayid);
                var elm = this.grid.querySelector('#' + dayid);
                if (elm) {
                    DatePicker.setGridCellFocus(elm);
                    elm.setAttribute('aria-selected', 'true');
                }
            }
            this.handleGridFocus();
        }
    }, {
        key: 'showNextMonth',


        //
        // showNextMonth() is a member function to show the next month
        //
        // @param (offset int) offset may be used to specify an offset for setting
        //                      focus on a day the specified number of days from
        //                      the beginning of the month.
        // @return N/A
        //
        value: function showNextMonth(offset) {
            DatePicker.setNextState();
            if (this.maxDate !== null) {
                if (new Date(this.year, this.month, 1).getTime() >= new Date(this.maxDate.getFullYear(), this.maxDate.getMonth(), 1).getTime()) {
                    return true;
                }
            }

            // show the next month
            if (this.month === 11) {
                this.month = 0;
                this.year++;
            } else {
                this.month++;
            }

            this.isCurrentDate = !(this.month !== this.curMonth || this.year !== this.curYear);

            // populate the calendar grid
            this.popGrid();

            this.mnth.innerHTML = this.props.monthNames[this.month] + ' ' + this.year;
            // if offset was specified, set focus on the first day + specified offset
            if (offset !== undefined) {
                var dayid = this.id + '_day' + offset;
                this.grid.setAttribute('aria-activedescendant', dayid);
                var day = this.grid.querySelector('#' + dayid);
                if (day) {
                    DatePicker.setGridCellFocus(day);
                    day.setAttribute('aria-selected', 'true');
                }
            }
            this.handleGridFocus();
        }
    }, {
        key: 'showPrevYear',


        //
        // showPrevYear() is a member function to show the previous year
        //
        // @return N/A
        //
        value: function showPrevYear() {
            DatePicker.setPrevState();
            if (this.minDate !== null) {
                if (this.year <= this.minDate.getFullYear()) {
                    return true;
                }
            }

            // decrement the year
            this.year--;
            this.isCurrentDate = !(this.month !== this.curMonth || this.year !== this.curYear);

            // populate the calendar grid
            this.popGrid();
            this.mnth.innerHTML = this.props.monthNames[this.month] + ' ' + this.year;
        }
    }, {
        key: 'showNextYear',


        //
        // showNextYear() is a member function to show the next year
        //
        // @return N/A
        //
        value: function showNextYear() {
            DatePicker.setNextState();
            if (this.maxDate !== null) {
                if (this.year >= this.maxDate.getFullYear()) {
                    return true;
                }
            }

            // increment the year
            this.year++;
            this.isCurrentDate = !(this.month !== this.curMonth || this.year !== this.curYear);

            // populate the calendar grid
            this.popGrid();
            this.mnth.innerHTML = this.props.monthNames[this.month] + ' ' + this.year;
        }
    }, {
        key: 'bindHandlers',


        //
        // bindHandlers() is a member function to bind event handlers for the widget
        //
        // @return N/A
        //
        value: function bindHandlers() {
            var _this2 = this;

            ////////////////////// bind button handlers //////////////////////////////////
            this.prev.onclick = function (e) {
                return _this2.handlePrevClick(e);
            };

            this.next.onclick = function (e) {
                return _this2.handleNextClick(e);
            };

            this.info.onclick = function (e) {
                return _this2.showInfo(e);
            };

            this.prev.onkeydown = function (e) {
                return _this2.handlePrevKeyDown(e);
            };

            this.next.onkeydown = function (e) {
                return _this2.handleNextKeyDown(e);
            };

            this.info.onkeydown = function (e) {
                return _this2.handleInfoKeyDown(e);
            };

            ///////////// bind grid handlers //////////////
            this.grid.onkeydown = function (e) {
                return _this2.handleGridKeyDown(e);
            };

            this.grid.keypress = function (e) {
                return _this2.handleGridKeyPress(e);
            };

            this.grid.onfocus = function (e) {
                return _this2.handleGridFocus(e);
            };

            this.grid.onblur = function (e) {
                return _this2.handleGridBlur(e);
            };

            this.grid.addEventListener('mousedown', function (e) {
                _this2.isDragging = 0;
            });
            this.grid.addEventListener('mousemove', function (e) {
                _this2.isDragging++;
            });
            this.grid.addEventListener('mouseup', function (e) {
                if (_this2.isDragging < 10) {
                    return _this2.handleGridClick(e);
                }
                return true;
            });
        }
    }, {
        key: 'handlePrevClick',


        //
        // handlePrevClick() is a member function to process click events for the prev month button
        //
        // @input (e obj) e is the event object associated with the event
        //
        // @return (boolean) false if consuming event, true if propagating
        //
        value: function handlePrevClick(e) {
            if (e.ctrlKey) {
                this.showPrevYear();
            } else {
                this.showPrevMonth();
            }

            e.stopPropagation();
            return false;
        }
    }, {
        key: 'handleNextClick',


        //
        // handleNextClick() is a member function to process click events for the next month button
        //
        // @input (e obj) e is the event object associated with the event
        //
        // @return (boolean) false if consuming event, true if propagating
        //
        value: function handleNextClick(e) {
            if (e.ctrlKey) {
                this.showNextYear();
            } else {
                this.showNextMonth();
            }

            e.stopPropagation();
            return false;
        }
    }, {
        key: 'handlePrevKeyDown',


        //
        // handlePrevKeyDown() is a member function to process keydown events for the prev month button
        //
        // @input (e obj) e is the event object associated with the event
        //
        // @return (boolean) false if consuming event, true if propagating
        //
        value: function handlePrevKeyDown(e) {
            if (e.altKey) {
                return true;
            }

            switch (e.keyCode) {
                case this.keys.tab:
                {
                    if (this.bModal === false || !e.shiftKey || e.ctrlKey) {
                        return true;
                    }

                    this.info.focus();
                    e.stopPropagation();
                    return false;
                }
                case this.keys.enter:
                case this.keys.space:
                {
                    if (e.shiftKey) {
                        return true;
                    }

                    if (e.ctrlKey) {
                        this.showPrevYear();
                    } else {
                        this.showPrevMonth();
                    }

                    e.stopPropagation();
                    return false;
                }
            }
            return true;
        }
    }, {
        key: 'handleNextKeyDown',


        //
        // handleNextKeyDown() is a member function to process keydown events for the next month button
        //
        // @input (e obj) e is the event object associated with the event
        //
        // @return (boolean) false if consuming event, true if propagating
        //
        value: function handleNextKeyDown(e) {
            if (e.altKey) {
                return true;
            }

            switch (e.keyCode) {
                case this.keys.enter:
                case this.keys.space:
                {
                    if (e.ctrlKey) {
                        this.showNextYear();
                    } else {
                        this.showNextMonth();
                    }
                    e.stopPropagation();
                    return false;
                }
            }
            return true;
        }
    }, {
        key: 'handleInfoKeyDown',
        value: function handleInfoKeyDown(e) {
            if (e.altKey) {
                return true;
            }

            switch (e.keyCode) {
                case this.keys.enter:
                {
                    this.showInfo(e);
                    return false;
                }
                case this.keys.tab:
                {
                    if (e.shiftKey) {
                        this.grid.focus();
                    } else {
                        this.prev.focus();
                    }
                    return false;
                }
            }
            return true;
        }
    }, {
        key: 'tabHandler',
        value: function tabHandler(e) {
            if (this.bModal === true) {
                if (e.shiftKey) {
                    this.next.focus();
                } else {
                    this.info.focus();
                }
                e.stopPropagation();
                return false;
            }
        }
    }, {
        key: 'updateTargetBox',
        value: function updateTargetBox(e, currDay) {
            if (e.ctrlKey) {
                return true;
            }

            // update the target box
            var cur2DigitDay = currDay.innerHTML;
            this.updateTextfield(cur2DigitDay);
            this.sendChangeEvent();
            this.dismissDialog(e);
            this.validateInput();
        }
    }, {
        key: 'dismissDialog',
        value: function dismissDialog(e) {
            // dismiss the dialog box
            this.hideDialog();
            e.stopPropagation();
            return false;
        }
    }, {
        key: 'previousday',
        value: function previousday(e, currDay, days) {
            if (e.ctrlKey || e.shiftKey) {
                return true;
            }
            var dayIndex = DatePicker.index(days, currDay) - 1;
            var prevDay = null;
            if (dayIndex >= 0) {
                prevDay = DatePicker.eq(dayIndex, days);
                if (prevDay) {
                    if (currDay) {
                        DatePicker.removeClass(currDay, 'focus');
                        currDay.setAttribute('aria-selected', 'false');
                    }
                    DatePicker.setGridCellFocus(prevDay);
                    prevDay.setAttribute('aria-selected', 'true');
                    this.grid.setAttribute('aria-activedescendant', prevDay.getAttribute('id'));
                }
            } else {
                this.showPrevMonth(0);
            }

            e.stopPropagation();
            return false;
        }
    }, {
        key: 'nextday',
        value: function nextday(e, currDay, days) {
            if (e.ctrlKey || e.shiftKey) {
                return true;
            }
            var dayIndex = DatePicker.index(days, currDay) + 1;
            var nextDay = null;
            if (dayIndex < days.length) {
                nextDay = DatePicker.eq(dayIndex, days);
                if (nextDay) {
                    if (currDay) {
                        DatePicker.removeClass(currDay, 'focus');
                        currDay.setAttribute('aria-selected', 'false');
                    }
                    DatePicker.setGridCellFocus(nextDay);
                    nextDay.setAttribute('aria-selected', 'true');
                    this.grid.setAttribute('aria-activedescendant', nextDay.getAttribute('id'));
                }
            } else {
                // move to the next month
                this.showNextMonth(1);
            }
            e.stopPropagation();
            return false;
        }
    }, {
        key: 'previousweek',
        value: function previousweek(e, currDay, days) {
            if (e.ctrlKey || e.shiftKey) {
                return true;
            }

            var dayIndex = DatePicker.index(days, currDay) - 7;
            var prevDay = null;
            if (dayIndex >= 0) {
                prevDay = DatePicker.eq(dayIndex, days);
                if (prevDay) {
                    if (currDay) {
                        DatePicker.removeClass(currDay, 'focus');
                        currDay.setAttribute('aria-selected', 'false');
                    }
                    DatePicker.setGridCellFocus(prevDay);
                    prevDay.setAttribute('aria-selected', 'true');
                    this.grid.setAttribute('aria-activedescendant', prevDay.getAttribute('id'));
                }
            } else {
                // move to appropriate day in previous month
                dayIndex = 6 - DatePicker.index(days, currDay);
                this.showPrevMonth(dayIndex);
            }
            e.stopPropagation();
            return false;
        }
    }, {
        key: 'nextweek',
        value: function nextweek(e, currDay, days) {
            if (e.ctrlKey || e.shiftKey) {
                return true;
            }

            var dayIndex = DatePicker.index(days, currDay) + 7;
            var nextDay = null;
            if (dayIndex < days.length) {
                nextDay = DatePicker.eq(dayIndex, days);
                if (nextDay) {
                    if (currDay) {
                        DatePicker.removeClass(currDay, 'focus');
                        currDay.setAttribute('aria-selected', 'false');
                    }
                    DatePicker.setGridCellFocus(nextDay);
                    nextDay.setAttribute('aria-selected', 'true');
                    this.grid.setAttribute('aria-activedescendant', nextDay.getAttribute('id'));
                }
            } else {
                // move to appropriate day in next month
                dayIndex = 8 - (days.length - DatePicker.index(days, currDay));
                this.showNextMonth(dayIndex);
            }
            e.stopPropagation();
            return false;
        }
    }, {
        key: 'nextMonthOrYear',
        value: function nextMonthOrYear(e) {
            this.switchMonthOrYear(this.showNextYear, this.showNextMonth)(e);
        }
    }, {
        key: 'previousMonthOrYear',
        value: function previousMonthOrYear(e) {
            this.switchMonthOrYear(this.showPrevYear, this.showPrevMonth)(e);
        }
    }, {
        key: 'switchMonthOrYear',
        value: function switchMonthOrYear(switchYear, switchMonth) {
            var _this3 = this;

            return function (e) {
                if (e.altKey) {
                    return true;
                }
                if (e.shiftKey) {
                    switchYear.call(_this3);
                } else {
                    switchMonth.call(_this3);
                }
                var currDay = _this3.grid.querySelector('#' + _this3.grid.getAttribute('aria-activedescendant'));
                var days = _this3.grid.querySelectorAll('td:not(.empty):not(.ukenr):not(.inactive)');
                var firstDate = days[0].innerText;
                var firstDayId = _this3.id + '_day' + firstDate;
                var firstDay = _this3.grid.querySelector('#' + firstDayId);
                if (firstDay) {
                    if (currDay) {
                        DatePicker.removeClass(currDay, 'focus');
                        currDay.setAttribute('aria-selected', 'false');
                    }
                    DatePicker.setGridCellFocus(firstDay);
                    firstDay.setAttribute('aria-selected', 'true');
                    _this3.grid.setAttribute('aria-activedescendant', firstDayId);
                }
                e.stopPropagation();
                return false;
            };
        }
    }, {
        key: 'startOfMonth',
        value: function startOfMonth(e, currDay, days) {
            if (!this.isMac) {
                if (e.ctrlKey || e.shiftKey) {
                    return true;
                }
            }
            var firstDate = days[0].innerText;
            var firstDayId = this.id + '_day' + firstDate;
            var firstDay = this.grid.querySelector('#' + firstDayId);
            if (firstDay) {
                if (currDay) {
                    DatePicker.removeClass(currDay, 'focus');
                    currDay.setAttribute('aria-selected', 'false');
                }
                DatePicker.setGridCellFocus(firstDay);
                firstDay.setAttribute('aria-selected', 'true');
                this.grid.setAttribute('aria-activedescendant', firstDayId);
            }
            e.stopPropagation();
            return false;
        }
    }, {
        key: 'endOfMonth',
        value: function endOfMonth(e, currDay, days) {
            if (!this.isMac) {
                if (e.ctrlKey || e.shiftKey) {
                    return true;
                }
            }
            var lastDate = days[days.length - 1].innerText;
            var lastDayId = this.id + '_day' + lastDate;
            var lastDay = this.grid.querySelector('#' + lastDayId);
            if (lastDay) {
                if (currDay) {
                    DatePicker.removeClass(currDay, 'focus');
                    currDay.setAttribute('aria-selected', 'false');
                }
                DatePicker.setGridCellFocus(lastDay);
                lastDay.setAttribute('aria-selected', 'true');
                this.grid.setAttribute('aria-activedescendant', lastDayId);
            }
            e.stopPropagation();
            return false;
        }
    }, {
        key: 'handleGridKeyDown',


        //
        // handleGridKeyDown() is a member function to process keydown events for the datepicker grid
        //
        // @input (e obj) e is the event object associated with the event
        //
        // @return (boolean) false if consuming event, true if propagating
        //
        value: function handleGridKeyDown(e) {
            var currDay = this.grid.querySelector('#' + this.grid.getAttribute('aria-activedescendant'));
            var days = void 0;
            if (this.grid) {
                days = this.grid.querySelectorAll('td:not(.empty):not(.ukenr):not(.inactive)');
            }
            if (e.altKey) {
                return true;
            }
            return (this.keyhandlers[e.keyCode] || DatePicker.passThrough).call(this, e, currDay, days);
        }
    }, {
        key: 'handleGridKeyPress',


        //
        // handleGridKeyPress() is a member function to consume keypress events for browsers that
        // use keypress to scroll the screen and manipulate tabs
        //
        // @input (e obj) e is the event object associated with the event
        //
        // @return (boolean) false if consuming event, true if propagating
        //
        value: function handleGridKeyPress(e) {
            if (e.altKey) {
                return true;
            }

            switch (e.keyCode) {
                case this.keys.tab:
                case this.keys.enter:
                case this.keys.space:
                case this.keys.esc:
                case this.keys.left:
                case this.keys.right:
                case this.keys.up:
                case this.keys.down:
                case this.keys.pageup:
                case this.keys.pagedown:
                case this.keys.home:
                case this.keys.end:
                {
                    e.stopPropagation();
                    return false;
                }
            }
            return true;
        }
    }, {
        key: 'handleGridClick',


        //
        // handleGridClick() is a member function to process mouse click events for the datepicker grid
        //
        // @input (id obj) e is the id of the object triggering the event
        //
        // @input (e obj) e is the event object associated with the event
        //
        // @return (boolean) false if consuming event, true if propagating
        //
        value: function handleGridClick(e) {
            var cell = e.target;
            if (DatePicker.hasClass(cell, 'empty') || DatePicker.hasClass(cell, 'inactive')) {
                return true;
            }
            var focused = this.grid.querySelector('.focus');
            if (focused) {
                DatePicker.removeClass(focused, 'focus');
                focused.setAttribute('aria-selected', 'false');
            }
            DatePicker.setGridCellFocus(cell);
            cell.setAttribute('aria-selected', 'true');
            this.grid.setAttribute('aria-activedescendant', cell.getAttribute('id'));
            var $curDay = this.grid.querySelector('#' + this.grid.getAttribute('aria-activedescendant'));

            // update the target box
            var cur2DigitDay = $curDay.innerHTML;
            this.updateTextfield(cur2DigitDay);
            this.sendChangeEvent();

            // dismiss the dialog box
            this.hideDialog();
            this.validateInput();
            e.stopPropagation();
            return false;
        }
    }, {
        key: 'handleGridFocus',


        //
        // handleGridFocus() is a member function to process focus events for the datepicker grid
        //
        // @input (e obj) e is the event object associated with the event
        //
        // @return (boolean) true
        //
        value: function handleGridFocus() {
            var active = this.grid.querySelector('#' + this.grid.getAttribute('aria-activedescendant'));
            if (!active) {
                var today = this.grid.querySelector('.today');
                DatePicker.setGridCellFocus(today);
                today.setAttribute('aria-selected', 'true');
                this.grid.setAttribute('aria-activedescendant', this.id + '_day' + today.innerHTML);
            } else {
                if (active.getAttribute('aria-selected') === 'false') {
                    DatePicker.setGridCellFocus(active);
                    active.setAttribute('aria-selected', 'true');
                }
            }
            return true;
        }
    }, {
        key: 'handleGridBlur',


        //
        // handleGridBlur() is a member function to process blur events for the datepicker grid
        //
        // @input (e obj) e is the event object associated with the event
        //
        // @return (boolean) true
        //
        value: function handleGridBlur() {
            var active = this.grid.querySelector('#' + this.grid.getAttribute('aria-activedescendant'));
            if (active) {
                DatePicker.removeClass(active, 'focus');
                active.setAttribute('aria-selected', 'false');
            }
            return true;
        }
    }, {
        key: 'showDialog',


        //
        // showDialog() is a member function to show the datepicker and give it focus. This function is only called if
        // the datepicker is used in modal dialog mode.
        //
        // @return N/A
        //
        value: function showDialog() {
            NavDatePickers.forEach(function (picker) {
                picker.hideDialog();
            });
            var thisObj = this;

            // Bind an event listener to the document to capture all mouse events to make dialog modal
            var events = 'click mousedown mouseup mousemove mouseover'.split(' ');
            for (var i = 0; i < events.length; i++) {
                document.addEventListener(events[i], function (e) {
                    DatePicker.ensureFocus(e, thisObj);
                });
            }

            // set active day from input field
            if (this.input.value) {
                var dayarr = this.input.value.split('.');
                if (this.validateInput()) {
                    this.updateDateValues(new Date(dayarr[2], dayarr[1] - 1, dayarr[0]));
                }
            }

            // show the dialog
            this.calwrap.setAttribute('aria-hidden', 'false');
            this.grid.focus();
        }
    }, {
        key: 'hideDialog',


        //
        // hideDialog() is a member function to hide the datepicker and remove focus. This function is only called if
        // the datepicker is used in modal dialog mode.
        //
        // @return N/A
        //
        value: function hideDialog() {
            var thisObj = this;
            // unbind the modal event sinks
            var events = 'click change mousedown mouseup mousemove mouseover'.split(' ');
            for (var i = 0; i < events.length; i++) {
                document.removeEventListener(events[i], function (e) {
                    DatePicker.ensureFocus(e, thisObj);
                });
            }
            // hide the dialog
            this.calwrap.setAttribute('aria-hidden', 'true');
            this.input.focus();
        }
    }, {
        key: 'showInfo',
        value: function showInfo(e) {
            var helpinfo = this.parent.querySelector('.shortcut_info');
            if (helpinfo.offsetHeight === 0) {
                DatePicker.addClass(e.target, 'opp');
                e.target.style.fontFamily = 'sans-serif';
                e.target.style.fontStyle = 'normal';
                e.target.innerHTML = 'x';
                DatePicker.slideDown(helpinfo, 300, 150);
            } else {
                DatePicker.removeClass(e.target, 'opp');
                e.target.style.fontFamily = 'serif';
                e.target.style.fontStyle = 'italic';
                e.target.innerHTML = 'i';
                helpinfo.removeAttribute('style');
            }
        }
    }, {
        key: 'validateInput',
        value: function validateInput() {
            var state = true;
            if (this.useFallback) {
                var value = this.input.value;
                state = DatePicker.validateDateString(value, this.props.inputFormat);
            }
            this.setErrorState(!state);
            return state;
        }
    }, {
        key: 'setErrorState',
        value: function setErrorState(state) {
            var classList = this.input.parentNode.classList;
            if (state ^ classList.contains('feil')) {
                classList.toggle('feil');
            }
        }
        // `getValue` and `setValue` uses a conditional on `this.useFallback` to determine how dates should be
        // fetched or updated. This is due to differences between the type=text and type=date api.

    }, {
        key: 'getValue',
        value: function getValue() {
            if (this.useFallback) {
                var dayarr = this.input.value.split('.');
                var state = DatePicker.validateDateString(this.input.value, this.props.inputFormat);

                return state ? new Date(dayarr[2], dayarr[1] - 1, dayarr[0]) : null;
            } else {
                return this.input.valueAsDate;
            }
        }
    }, {
        key: 'setValue',
        value: function setValue(date) {
            if (!date) {
                this.input.value = null;
                return;
            }

            // Is necessary due to wierd behaviour in the Date API where timezones are not correctly handled.
            var safeDate = new Date(date);
            safeDate.setHours(12);

            if (isNaN(safeDate.getHours())) {
                // Invalid format / date. Set value as text
                this.input.value = date;
            } else if (this.useFallback) {
                this.updateDateValues(safeDate);
                this.updateTextfield(safeDate.getDate());
            } else {
                this.input.valueAsDate = safeDate;
            }
            this.sendChangeEvent();
        }
    }, {
        key: 'updateTextfield',
        value: function updateTextfield(day) {
            this.input.value = (day < 10 ? '0' : '') + day + '.' + (this.month < 9 ? '0' : '') + (this.month + 1) + '.' + this.year;
        }
    }, {
        key: 'sendChangeEvent',
        value: function sendChangeEvent() {
            var event = void 0;
            if (typeof window.Event === 'function') {
                event = new Event('change');
            } else {
                event = document.createEvent('TextEvent');
                event.initEvent('change', false, false);
            }
            this.input.dispatchEvent(event);
        }
    }, {
        key: 'getMarkup',
        value: function getMarkup(platform) {
            return '<div id="counterid" class="datovelger__kalender">\n\t\t\t<div role="button" class="bn_date" title="' + this.props.bn_dateTitle + '" tabindex="0"></div>\n\t\t\t<div class="cal-wrap" aria-hidden="true">\n\t\t\t\t<div class="month-wrap">\n\t\t\t\t\t<div class="bn_prev" role="button" aria-labelledby="bn_prev-label" tabindex="0"></div>\n\t\t\t\t\t<div class="month typo-undertittel" role="heading" aria-live="assertive" aria-atomic="true"></div>\n\t\t\t\t\t<div class="bn_next" role="button" aria-labelledby="bn_next-label" tabindex="0"></div>\n\t\t\t\t</div>\n\t\t\t\t<div id="bn_prev-label" class="offscreen">' + this.props.bn_prevLabel + '</div>\n\t\t\t\t<div id="bn_next-label" class="offscreen">' + this.props.bn_nextLabel + '</div>\n\t\t\t\t<div id="bn_info-label" class="offscreen">' + this.props.bn_infoLabel + '</div>\n\t\t\t\t<div class="info-wrap">\n\t\t\t\t\t<div role="button" aria-labelledby="bn_info-label" class="bn_info" tabindex="0">i</div>\n\t\t\t\t</div>\n\t\t\t\t<section class="shortcut_info">\n\t\t\t\t\t<h1 class="typo-element">' + this.props.keyboard.heading + '</h1>\n\t\t\t\t\t<table>\n\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t<th>' + this.props.keyboard.arrowLeftRight[platform] + '</th>\n\t\t\t\t\t\t\t<td>' + this.props.keyboard.arrowLeftRight.text + '</td>\n\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t<th>' + this.props.keyboard.arrowUpDown[platform] + '</th>\n\t\t\t\t\t\t\t<td>' + this.props.keyboard.arrowUpDown.text + '</td>\n\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t<th>' + this.props.keyboard.pageUpDown[platform] + '</th>\n\t\t\t\t\t\t\t<td>' + this.props.keyboard.pageUpDown.text + '</td>\n\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t<th>' + this.props.keyboard.ctrlPageUpDown[platform] + '</th>\n\t\t\t\t\t\t\t<td>' + this.props.keyboard.ctrlPageUpDown.text + '</td>\n\t\t\t\t\t\t</tr>\n\t\t\t\t\t\t<tr>\n\t\t\t\t\t\t\t<th>' + this.props.keyboard.homeEnd[platform] + '</th>\n\t\t\t\t\t\t\t<td>' + this.props.keyboard.homeEnd.text + '</td>\n\t\t\t\t\t\t</tr>\n\t\t\t\t\t</table>\n\t\t\t\t</section>\n\t\t\t</div>\n\t\t</div>';
        }
    }], [{
        key: 'calcWeekNumber',


        //
        // calcWeekNumber() is a member function to calculate the week number of year
        //
        //
        // @return (integer) week number of year
        //
        value: function calcWeekNumber(date) {
            var target = new Date(date.valueOf()),
                dayNumber = (date.getUTCDay() + 6) % 7,
                firstThursday = void 0;

            target.setUTCDate(target.getUTCDate() - dayNumber + 3);
            firstThursday = target.valueOf();
            target.setUTCMonth(0, 1);

            if (target.getUTCDay() !== 4) {
                target.setUTCMonth(0, 1 + (4 - target.getUTCDay() + 7) % 7);
            }
            return Math.ceil((firstThursday - target) / (7 * 24 * 3600 * 1000)) + 1;
        }
    }, {
        key: 'calcNumDays',


        //
        // calcNumDays() is a member function to calculate the number of days in a given month
        //
        // @return (integer) number of days
        //
        value: function calcNumDays(year, month) {
            return 32 - new Date(year, month, 32).getDate();
        }
    }, {
        key: 'calcStartWeekday',


        //
        // calcstartWeekday() is a member function to calculate the day of the week the first day of a month lands on
        //
        // @return (integer) number representing the day of the week (0=Sunday....6=Saturday)
        //
        value: function calcStartWeekday(year, month) {
            return (new Date(year, month, 1).getDay() + 6) % 7;
        }
    }, {
        key: 'setPrevState',
        value: function setPrevState() {
            if (this.year === undefined || this.month === undefined) {
                return true;
            }
            var currYearMonth = DatePicker.yearMonthString(new Date(this.year, this.month, 1), -1);
            var minYearMonth = DatePicker.yearMonthString(this.minDate, -1);
            if (currYearMonth === minYearMonth) {
                this.prev.setAttribute('aria-disabled', 'true');
            } else {
                this.prev.removeAttribute('aria-disabled');
            }
        }
    }, {
        key: 'setNextState',
        value: function setNextState() {
            if (this.year === undefined || this.month === undefined) {
                return true;
            }
            var currYearMonth = DatePicker.yearMonthString(new Date(this.year, this.month, 1), 1);
            var maxYearMonth = DatePicker.yearMonthString(this.maxDate, 1);
            if (currYearMonth === maxYearMonth) {
                this.next.setAttribute('aria-disabled', 'true');
            } else {
                this.next.removeAttribute('aria-disabled');
            }
        }
    }, {
        key: 'yearMonthString',
        value: function yearMonthString(date, add) {
            var month = date.setMonth(date.getMonth() + add).toString();
            if (month.length === 1) {
                month = '0' + month;
            }
            return date.getYear().toString() + month;
        }
    }, {
        key: 'passThrough',
        value: function passThrough() {
            return true;
        }
    }, {
        key: 'setGridCellFocus',
        value: function setGridCellFocus(element) {
            if (!DatePicker.hasClass(element, 'inactive')) {
                DatePicker.addClass(element, 'focus');
            }
        }
    }, {
        key: 'hasClass',
        value: function hasClass(elem, classname) {
            if (elem.classList) {
                return elem.classList.contains(classname);
            } else {
                return elem.getAttribute('class').indexOf(classname) > -1;
            }
        }
    }, {
        key: 'addClass',
        value: function addClass(elem, classname) {
            if (elem.classList) {
                elem.classList.add(classname);
            } else {
                var classes = elem.getAttribute('class');
                if (classes !== '') {
                    classes += ' ';
                }
                classes += classname;
                elem.setAttribute('class', classes);
            }
        }
    }, {
        key: 'removeClass',
        value: function removeClass(elem, classname) {
            if (elem.classList) {
                elem.classList.remove(classname);
            } else {
                var classes = elem.getAttribute('class');
                classes = classes.replace(classname, '');
                elem.setAttribute('class', classes);
            }
        }
    }, {
        key: 'ensureFocus',
        value: function ensureFocus(e, thisObj) {
            //ensure focus remains on the dialog
            thisObj.grid.focus();
            // Consume all mouse events and do nothing
            e.stopPropagation();
            return false;
        }
    }, {
        key: 'index',
        value: function index(group, element) {
            var n = 0;
            for (var i = 0; i < group.length; i++) {
                if (group[i] === element) {
                    return n;
                }
                if (group[i].nodeType === 1) {
                    n++;
                }
            }
            return -1;
        }
    }, {
        key: 'eq',
        value: function eq(index, element) {
            if (index >= 0 && index < element.length) {
                return element[index];
            } else {
                return -1;
            }
        }
    }, {
        key: 'formatDate',
        value: function formatDate(input) {
            if (!input) {
                return '';
            }
            var dots = input.trim();
            return dots.replace(/[, :;\-_+*/]/g, '.');
        }
    }, {
        key: 'slideDown',
        value: function slideDown(element, duration, finalheight) {
            var s = element.style;
            s.height = '0px';
            var y = 0;
            var framerate = 48;
            var totalframes = duration / framerate;
            var heightincrement = finalheight / totalframes;
            var one_second = 1000;
            var interval = one_second / framerate;
            var tween = function tween() {
                y += heightincrement;
                s.height = y + 'px';
                if (y < finalheight) {
                    setTimeout(tween, interval);
                }
            };
            tween();
        }
    }, {
        key: 'validateDateString',
        value: function validateDateString(datestring, regex) {
            return regex.test(datestring);
        }
    }]);

    return DatePicker;
}();

DatePicker.INIT_COUNTER = 0;

exports.default = DatePicker;