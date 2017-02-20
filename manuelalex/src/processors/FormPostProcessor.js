/**
 * Created by Manuel on 13/02/2017.
 */

let _               = require('lodash');

const PostProcessor = require('./PostProcessor.js');

const Form = require('../Form.js');
const Question = require('../statements/Question.js');
const Answer = require('../statements/Answer.js');
const IfStatement = require('../statements/IfStatement.js');
const Expression = require('../expressions/Expression.js');
const Allocation = require('../allocation/Allocation.js');
const Factor = require('../expressions/Factor.js');
const MinOperator = require('../operator/MinOperator.js');

module.exports = class FormPostProcessor extends PostProcessor {

    constructor(){
        super();
    }

    // todo
    form(data, location, reject) {
        console.log(`Form: ${JSON.stringify(data)}`);
        return new Form({
            name: data[1][0],
            statements: data
        });
    }

    // todo
    statements(data, location, reject) {
        console.log(`Statements: ${JSON.stringify(data)}`);
        return data;
    }

    // todo should not been called
    statement(data, location, reject) {
        console.log(`Statement: ${JSON.stringify(data)}`);
        return data[0];
    }

    question(data, location, reject) {
        console.log(`Question: ${JSON.stringify(data)}`);
        return new Question({name: data[2].trim(), propertyName: data[6][0], type: data[9][0].trim()});
    }

    ifStatement(data, location, reject) {
        console.log(`ifStatement: ${JSON.stringify(data)}`);
        return new IfStatement({ name: data[0], propertName: [] });
    }

    // todo
    answer(data, location, reject) {
        console.log(`Answer: ${JSON.stringify(data)}`);

        // We may have to retrieve the allocation by retrieving the type 'Allocation' from the array
        return new Answer({name: data[2].trim(), allocation: data[6]});
    }

    factor(data, location, reject){
        console.log(`Factor: ${JSON.stringify(data)}`);

        /* Factor is an Expression */
        if(data.length === 3){
            return data[1];
        } else {
            return new Factor({

            });
        }
    }

    // todo
    allocation(data, location, reject){
        console.log(`Allocation: ${JSON.stringify(data)}`);
        return new Allocation({
            propertyName: data[0][0].trim(),
            type: data[3][0].trim(),
            expression: []
        });
    }

    // todo
    expression(data, location, reject) {
        console.log(`Expression: ${JSON.stringify(data)}`);

        if(data.length === 3){
            // let term =
            return new Expression({
                term: _.flattenDeep(data[0])[0]
            });
        }

    }

    operator(data, location, reject) {
        console.log(`Operator: ${JSON.stringify(data)}`);
        return data;
    }

    minOp(data, location, reject) {
        return new MinOperator();
    }

};