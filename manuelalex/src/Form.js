/**
 * Created by Manuel on 13/02/2017.
 */

import {Program} from './Program.js';

export class Form extends Program {

    constructor(options = {}) {
        super(options);
        this.name = options.name;
        this.statements = options.statements;
    }
}