/**
 * Created by Manuel on 13/02/2017.
 */

let IfStatement = require('./IfStatement.js');

module.exports = class IfElseStatement extends IfStatement {

    constructor(options = {}) {
        super(options);
        this.elseBody = options.elseBody;
    }

    validate() {

    }
};