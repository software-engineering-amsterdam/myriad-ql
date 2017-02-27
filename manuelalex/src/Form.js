/**
 * Created by Manuel on 13/02/2017.
 */

import {Program} from './Program.js';

export class Form extends Program {

    constructor(name = '', statements = [], location) {
        super();

        this.name = name;
        this.statements = statements;
        this.location = location;
    }

    getStatements(){
        return this.statements;
    }

    getName(){
        return this.name;
    }

    getLocation(){
        return this.location;
    }
}