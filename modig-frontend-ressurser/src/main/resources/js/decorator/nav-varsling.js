$(function () {
    if (!navno.securityLevel || navno.securityLevel < 3) {
        //Bruker ikke logget inn, eller logget inn på for lavt sikkerhetsnivå
        return;
    }

    var varsler,
        data = {},
        maaned = ['januar', 'februar', 'mars', 'april', 'mai', 'juni', 'juli', 'august', 'september', 'oktober', 'november', 'desember'],
        feilUnderHentingAvVarsler = false,
        varslerKnapp = $('#toggle-varsler'),
        varslerKnappMobil = $('#toggle-varsler-mobile'),
        varselmeny = $('#varsler-display'),
        mainmenu = $('#mainmenu'),
        tjenesteBaseUrl = varslerKnapp.attr('data-base-url'),
        varselinnboksUrl = '/varselinnboks',
        config = {
            hentSisteVarslerUrl: tjenesteBaseUrl + varselinnboksUrl + '/rest/varsel/hentsiste',
            mineVarslerRelativUrl: varselinnboksUrl,
            oppdatertLestStatusUrl: tjenesteBaseUrl + varselinnboksUrl + '/rest/varsel/erlest',
            maksAntallVarslerSomSkalVises: 5
        },
        tekster = {
            visalle: varslerKnapp.attr('data-tekst-visalle'),
            nyeFlertall: varslerKnapp.attr('data-tekst-visalle-nye-flertall'),
            nyeEntall: varslerKnapp.attr('data-tekst-visalle-nye-entall'),
            ingenvarsler: varslerKnapp.attr('data-tekst-ingenvarsler'),
            error: varslerKnapp.attr('data-tekst-error'),
            lenketekst: varslerKnapp.attr('data-tekst-varselurl-lenketekst')
        };

    visVarselikoner();
    posisjonerMeny();
    fyllMenyMedHtml();

    $.ajax({url: config.hentSisteVarslerUrl})
        .done(function (nyeData) {
            data.nyesteVarsler = nyeData.nyesteVarsler;
            data.antallUleste = nyeData.totaltAntallUleste;

            if (!data.nyesteVarsler) {
                feilUnderHentingAvVarsler = true;
                return;
            }

            if (data.antallUleste > 0) {
                varslerKnapp.addClass('har-nye-varsler');
                varslerKnappMobil.addClass('har-nye-varsler');
            }

            varsler = data.nyesteVarsler.map(function (varsel) {
                var datoOpprettet = new Date(parseFloat(varsel.datoOpprettet));
                return {
                    varseltekst: varsel.varseltekst,
                    maaned: maanedKort(datoOpprettet.getMonth()),
                    dag: addLeadingZeroIfNeeded(datoOpprettet.getDate().toString()),
                    formattertDato: norskDato(datoOpprettet),
                    id: varsel.varselId,
                    datoOpprettet: datoOpprettet,
                    erSett: !!varsel.datoLest,
                    url: varsel.url
                };
            }).sort(nyesteForst);
        })
        .fail(function () {
            feilUnderHentingAvVarsler = true;
        })
        .complete(function () {
            fyllMenyMedHtml(data.antallUleste);
        });

    mainmenu.on('click', '#toggle-varsler', function () {
        var varselikon = $(this);
        varselikon.removeClass('har-nye-varsler');

        varselmeny.toggleClass('open');

        posisjonerMenyIForholdTilIkon(varselikon);
        fyllMenyMedHtml(data.antallUleste);
        settVarslerLest();
    });

    function posisjonerMeny() {
        if ($(window).width() > 768) {
            posisjonerMenyIForholdTilIkon();
        } else {
            posisjonerMenyIForholdTilToppmeny();
        }
    }

    function posisjonerMenyIForholdTilToppmeny() {
        varselmeny.offset({
            left: 0,
            top: mainmenu.height()
        });
    }

    function posisjonerMenyIForholdTilIkon() {
        var offset = varslerKnapp.offset();
        var triangelDistanseFraHoyre = 41;
        var triangelHoyde = 11;
        var marginMellomIkonOgTriangel = 10;
        var left = offset.left - varselmeny.outerWidth() + triangelDistanseFraHoyre + (triangelHoyde * 2) + varslerKnapp.width() / 2;

        var justering = 4;
        var right = $(window).width() - (left + varselmeny.outerWidth()) + justering;
        right = right < 0 ? 0 : right;

        varselmeny.offset({
            top: offset.top + varslerKnapp.height() + triangelHoyde + marginMellomIkonOgTriangel
        });
        varselmeny.css('right', right);
        varselmeny.css('left', 'auto');
    }

    function fyllMenyMedHtml(antallNye) {
        if (feilUnderHentingAvVarsler){
            varselmeny.html('<p class="tekstblokk">' + tekster.error + '</p>');
        } else if (!varsler) {
            varselmeny.html('<div class="ingen-varsler-container"></div>');
        } else if (varsler.length == 0) {
            varselmeny.html('<p class="tekstblokk">' + tekster.ingenvarsler + '</p>');
        } else {
            var varslerTilVisning = varsler.length > config.maksAntallVarslerSomSkalVises ? varsler.slice(0, config.maksAntallVarslerSomSkalVises) : varsler;
            varselmeny.html('<div>' + varslerTilVisning.map(varselTilHtml).join('') + '</div>');
        }

        if (varsler && varsler.length > config.maksAntallVarslerSomSkalVises) {
            varselmeny.append('<div><a class="vis-alle-lenke skillelinje-topp"  href="' + config.mineVarslerRelativUrl + '">' + wrapISpan(tekster.visalle + ' ' + nyeVarslerVisning(antallNye))  + '</a></div>');
        }
    }

    function nyeVarslerVisning(antallNye) {
        var antallNyeVisning = '';
        if(antallNye > 0) {
            antallNyeVisning += '(' + antallNye + ' ';
            antallNyeVisning += antallNye == 1 ? tekster.nyeEntall : tekster.nyeFlertall;
            antallNyeVisning += ')'
        }
        return antallNyeVisning;
    }

    function varselTilHtml(varsel) {
        return '<div class="clearfix varsel-container '+ meldingSettEllerIkke(varsel) + '">' +
            kortDatoTilHtml(varsel.maaned, varsel.dag) + '<div class="varsel-innhold-container">' +
            '<div class="varsel-dato">' + varsel.formattertDato + '</div><div>' +
            '<span class="varsel-melding">' + varsel.varseltekst + '</span>' + leggPaaLenkeHvisUrlFinnes(varsel) + '</div></div></div>';
    }

    function leggPaaLenkeHvisUrlFinnes(varsel) {
        if (varsel.url) {
            return '<a href="' + varsel.url + '">' + wrapISpan(tekster.lenketekst) + '</a>';
        }

        return '';
    }

    function meldingSettEllerIkke(varsel) {
        return varsel.erSett ? 'sett' : '';
    }

    function kortDatoTilHtml(maaned, dag) {
        return '<div class="varsel-kort-dato"><span>' + maaned + '</span></br><span>' + dag + '</span></div>';
    }

    function norskDato(jsDate) {
        var dag = addLeadingZeroIfNeeded(jsDate.getDate().toString());
        var mnd = maaned[jsDate.getMonth()];
        var aar = jsDate.getFullYear();

        var time = addLeadingZeroIfNeeded(jsDate.getHours().toString());
        var min = addLeadingZeroIfNeeded(jsDate.getMinutes().toString());

        return dag + '. ' + mnd + ' ' + aar + ' kl. ' + time + ':' + min;
    }

    function prependZero(string) {
        return '0' + string;
    }

    function addLeadingZeroIfNeeded(string) {
        return string.length > 1 ? string : prependZero(string);
    }

    function maanedKort(maanedIndeks) {
        return maaned[maanedIndeks].substr(0, 3);
    }

    function nyesteForst(varsel1, varsel2) {
        return varsel1.datoOpprettet < varsel2.datoOpprettet;
    }

    function settVarslerLest() {
        if (varsler && varsler.length > 0 && !varsler[0].erSett) {
            $.ajax({url: config.oppdatertLestStatusUrl + '/' + varsler[0].id, method: 'POST'}).done(function () {
                varsler.forEach(function (varsel) {
                    varsel.erSett = true;
                });
            });
        }
    }

    var previousWidth;
    $(window).resize(function () {
        var width = $(this).width();

        if (previousWidth && previousWidth >= 768 && width < 768) {
            varselmeny.removeClass('open');
            posisjonerMenyIForholdTilToppmeny();
        } else if (previousWidth && previousWidth <= 768 && width > 768) {
            varselmeny.removeClass('m-open');
        }

        if (width > 768) {
            posisjonerMenyIForholdTilIkon();
        }

        previousWidth = width;
    });

    function wrapISpan(tekst) {
        return '<span>' + tekst + '</span>';
    }

    function visVarselikoner() {
        varslerKnapp.removeClass('skjul');
        varslerKnappMobil.removeClass('skjul');
    }
});
