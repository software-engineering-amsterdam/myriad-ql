/**
 * Created by Manuel on 13/02/2017.
 */

import {Statement}   from './Statement.js';

export class Answer extends Statement {

    constructor(options = {}) {
        super(options);

        this.name = options.name;
        this.allocation = options.allocation;
    }

    validate() {

    }

    getGeneratedCode(type) {
        return "<div>" + this.name + "<input type='" + type + "' id='" + this.propertyName + "'></div>";
    }

    accept(visitor){
        visitor.visitAnswer(self);
    }
};