/**
 * Created by Manuel on 13/02/2017.
 */

let IfElseStatement = require('./IfElseStatement.js');

module.exports = class IfElseIfElseStatement extends IfElseStatement {

    constructor(options = {}) {
        super(options);

        this.elseIfCondition = options.elseIfCondition;
        this.elseIfBody = options.elseIfBody;
    }

    validate() {

    }
};