/**
 * Created by Manuel on 20/02/2017.
 */
export class AST {
    constructor(program = {}) {
        this.program = program;
    }

    getProgram(){
        return this.program;
    }

    getStatements(){
        return this.program.getStatements();
    }

    getName(){
        return this.program.getName();
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