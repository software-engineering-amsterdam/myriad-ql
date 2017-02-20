/**
 * Created by Manuel on 20/02/2017.
 */
module.exports = class AST {
    constructor(form = {}) {

        this.form = form;

        // todo fix code smell
        this.validate()
    }

    validate() {
        for(let statement of this.form.statements){
            statement.validate();
        }
    }

    evaluate() {

    }
};