/**
 * Created by Manuel on 13/02/2017.
 */

import {IfStatement}  from './IfStatement.js';

export class IfElseStatement extends IfStatement {

    constructor(options = {}) {
        super(options);
        this.elseBody = options.elseBody;
    }

    validate() {

    }
};