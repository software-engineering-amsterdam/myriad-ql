/**
 * Created by Manuel on 13/02/2017.
 */

import {Statement}      from './Statement.js';

export class Question extends Statement {
    constructor(options = {}) {
        super(options);

        this.name = options.name;
        this.propertyName = options.propertyName;
        this.propertyType = options.propertyType;
    }

    validate() {
        if (!this.name.length) {
            this._throwError(`Name should be defined`);
        }
    }

    // todo probably remove this
    getGeneratedCode(type) {
        return "<div>" + this.name + "<input type='" + type + "' onchange='click" + this.propertyName + "()' id='" + this.propertyName + "'></div>";
    }
};