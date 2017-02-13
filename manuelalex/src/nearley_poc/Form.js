/**
 * Created by Manuel on 13/02/2017.
 */

let Program = require('./Program.js');

// todo
class Form extends Program {

    constructor(options = {}) {
        super(options);
        this.name = options.name;
        this.statements = options.statements;
    }

}
module.exports = Form;