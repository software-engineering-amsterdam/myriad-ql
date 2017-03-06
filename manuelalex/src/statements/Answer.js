/**
 * Created by Manuel on 13/02/2017.
 */

import {Statement}   from './Statement.js';
import {Label}       from '../Label.js';

export class Answer extends Statement {

    constructor(name = '', allocation = {}, location = null) {
        super(location);

        this.label = new Label(name);
        this.allocation = allocation;
    }

    getLabel(){
        return this.label;
    }

    getAllocation(){
        return this.allocation;
    }

    validate() {

    }

    getGeneratedCode(type) {
        return "<div>" + this.name + "<input type='" + type + "' id='" + this.propertyName + "'></div>";
    }

    accept(visitor){
        visitor.visitAnswer(self);
    }

    render(visitor, view){
        visitor.renderAnswer(this, view);
    }


}