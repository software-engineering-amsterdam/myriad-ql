/**
 * Created by Manuel on 13/02/2017.
 */

import {IfStatement}  from './IfStatement.js';

export class IfElseStatement extends IfStatement {

    constructor(condition, ifBody, elseBody, location) {
        super(condition, ifBody, elseBody);
        this.elseBody = elseBody;

    }

    getElseBody(){
        return this.elseBody;
    }

    validate() {

    }
};