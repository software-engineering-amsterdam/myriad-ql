/**
 * Created by Manuel on 13/02/2017.
 */

import {IfElseStatement} from './IfElseStatement.js';

export class IfElseIfElseStatement extends IfElseStatement {

    constructor(options = {}) {
        super(options);

        this.elseIfCondition = options.elseIfCondition;
        this.elseIfBody = options.elseIfBody;
    }

    validate() {

    }
};