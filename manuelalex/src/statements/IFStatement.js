/**
 * Created by Manuel on 13/02/2017.
 */

import {Statement}  from './Statement.js';

export class IfStatement extends Statement {

    constructor(condition, ifBody, location) {
        super(location);

        this.condition = condition;
        this.ifBody = ifBody;

    }

    getCondition(){
        return this.condition;
    }

    getIfBody(){
        return this.ifBody;
    }

    accept(visitor){
        visitor.visitIfStatement(this);
    }

    render(visitor, view){
        visitor.renderIfStatement(this, view);
    }
}