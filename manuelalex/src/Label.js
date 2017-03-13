/**
 * Created by Manuel on 27/02/2017.
 */

export class Label {
    constructor(value) {
        this.value = value;
    }

    getValue() {
        return this.value;
    }

    render(visitor) {
        return visitor.renderLabel(this);
    }

    contains(obj) {
        return this.getValue() === obj.getValue();
    }
}