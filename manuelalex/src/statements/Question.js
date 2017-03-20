/**
 * Created by Manuel on 13/02/2017.
 */

import {Statement}      from './Statement.js';


export class Question extends Statement {
    constructor(label = {}, propertyName = '', propertyType = null, location) {
        super(location);

        this.label = label;
        this.propertyName = propertyName;
        this.propertyType = propertyType;
    }

    getLabel(){
        return this.label;
    }

    getPropertyName(){
        return this.propertyName;
    }

    getPropertyType(){
        return this.propertyType;
    }

    accept(visitor){
        visitor.visitQuestion(this);
    }

    render(visitor, view){
        visitor.renderQuestion(this, view);
    }
}