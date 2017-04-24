/**
 * Created by Manuel on 20/03/2017.
 */

import {QLNumber}   from './Types.js';

export class Numbers {
    constructor(value, location) {
        this.value = value;
        this.location = location;
    }

    getValue() {
        return this.value;
    }

    getLocation() {
        return this.location;
    }

    accept(visitor, ...params) {
        return visitor.visitNumbers(this, ...params);
    }

    getType(){
        return QLNumber;
    }

    toString(){
        return this.getValue().toString();
    }

}