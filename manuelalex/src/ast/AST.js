/**
 * Created by Manuel on 20/02/2017.
 */
export class AST {
    constructor(program = {}) {

        this.program = program;

        // this.validate()
    }

    validate() {
        for(let statement of this.form.statements){
            statement.validate();
        }
    }

    evaluate() {

    }
};