/**
 * Created by Manuel on 13/02/2017.
 */

const PostProcessor = require('./PostProcessor.js');

const Form = require('../Form.js');
const Question = require('../statements/Question.js');
const Answer = require('../statements/Answer.js');
const IfStatement = require('../statements/IfStatement.js');
const Expression = require('../expressions/Expression.js');
const Allocation = require('../allocation/Allocation.js');

module.exports = class FormPostProcessor extends PostProcessor {

    constructor(){
        super();

        this._statements = [];

        this.ast = {
            name: '',
            statements: this._statements,
        };

    }

    // todo
    form(data, location, reject) {
        console.log(`Form: ${JSON.stringify(data)}`);
        return new Form({
            name: data[1][0],
            data: data
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
        this._statements.push(data);
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
        return new Answer({name: data[2].trim(), allocation: data});
    }

    factor(data, location, reject){
        console.log(`Factor: ${JSON.stringify(data)}`);
        return data;
    }

    // todo
    expression(data, location, reject) {
        console.log(`Expression: ${JSON.stringify(data)}`);
        return data;
        return new Expression({

        });
    }

    // todo
    allocation(data, location, reject){
        console.log(`Allocation: ${JSON.stringify(data)}`);
        return new Allocation({
            propertyName: data[0][0],

        });
        return data;
    }

    operator(data, location, reject) {
        console.log(`Operator: ${JSON.stringify(data)}`);
        return data;
    }

};