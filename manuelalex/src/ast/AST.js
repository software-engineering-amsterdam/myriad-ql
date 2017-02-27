/**
 * Created by Manuel on 20/02/2017.
 */
export class AST {
    constructor(program = {}) {

        this.program = program;

        // this.validate()
    }

    validate() {
        for(let statement of this.program.statements){
            statement.validate();
        }
    }

    validate() {
        for(let statement of this.program.statements){
            statement.validate();
        }
    }

};