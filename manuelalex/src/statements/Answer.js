/**
 * Created by Manuel on 13/02/2017.
 */

import {Statement}   from './Statement.js';

export class Answer extends Statement {

    constructor(name = '', allocation = {}, location = null) {
        super(location);

        this.name = name;
        this.allocation = allocation;
    }

    validate() {

    }

    getGeneratedCode(type) {
        return "<div>" + this.name + "<input type='" + type + "' id='" + this.propertyName + "'></div>";
    }

    accept(visitor){
        visitor.visitAnswer(self);
    }


}