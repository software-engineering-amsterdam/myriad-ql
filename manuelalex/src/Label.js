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

    accept(visitor, ...params) {
        return visitor.visitLabel(this, ...params);
    }

    contains(obj) {
        return this.getValue() === obj.getValue();
    }
}