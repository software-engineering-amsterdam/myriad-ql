/**
 * Created by Manuel on 13/02/2017.
 */


export class Form {

    constructor(name = '', statements = [], location) {
        this.name = name;
        this.statements = statements;
        this.location = location;
    }

    getStatements(){
        return this.statements || [];
    }

    getName(){
        return this.name || '';
    }

    getLocation(){
        return this.location;
    }
}