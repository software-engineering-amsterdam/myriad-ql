/**
 * Created by Manuel on 13/02/2017.
 */

import {Statement}  from './Statement.js';

export class IfStatement extends Statement {

    constructor(options = {}) {
        super(options);

        this.condition = options.condition;
        this.ifBody = options.ifBody;
    }

    validate() {

    }
};