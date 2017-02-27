/**
 * Created by Manuel on 13/02/2017.
 */

import {Statement}      from './Statement.js';


export class Question extends Statement {
    constructor(name = '', propertyName = '', propertyType = null, location) {
        super(location);

        this.name = name;
        this.propertyName = propertyName;
        this.propertyType = propertyType;
    }

    getName(){
        return this.name;
    }

    getPropertyName(){
        return this.propertyName;
    }

    getPropertyType(){
        return this.propertyType;
    }

    validate() {
        if (!this.name.length) {
            this._throwError(`Name should be defined`);
        }
    }

    accept(visitor){
        visitor.visitQuestion(this);
    }
}