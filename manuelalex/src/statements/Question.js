/**
 * Created by Manuel on 13/02/2017.
 */

import {Statement}      from './Statement.js';


export class Question extends Statement {
    constructor(label = {}, property = '', propertyType = null, location) {
        super(location);

        this.label = label;
        this.property = property;
        this.propertyType = propertyType;
    }

    getLabel() {
        return this.label;
    }

    getProperty() {
        return this.property;
    }

    getPropertyType() {
        return this.propertyType;
    }

    accept(visitor) {
        visitor.visitQuestion(this);
    }

    render(visitor, view) {
        visitor.renderQuestion(this, view);
    }
}