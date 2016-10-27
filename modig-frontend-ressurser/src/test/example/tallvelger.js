(function(scope){
    class Tallvelger {
        constructor(element) {
            this.element = element;
            this.input = element.querySelector('input');
            this.dec = element.querySelector('button:first-child');
            this.inc = element.querySelector('button:last-child');

            this.element.addEventListener('click', this.eventHandler.bind(this));
        }

        updateWith(change) {
            const value = parseFloat(this.input.value) || 0;
            this.input.value = value + change;
        }

        eventHandler(event) {
            if (event.target == this.dec) {
                event.preventDefault();
                this.updateWith(-1);
            } else if (event.target == this.inc) {
                event.preventDefault();
                this.updateWith(1);
            }
        }

        static create(element) {
            return new Tallvelger(element);
        }
    }

    scope.Tallvelger = Tallvelger;
})(exports);