/**
 * Created by Manuel on 20/02/2017.
 */
import {Operator}  from './Operator.js';

export class PlusOperator extends Operator {

    constructor(options = {}){
        super(options)
    }

    toString(){
        return '+';
    }
};