/**
 * Created by Manuel on 13/02/2017.
 */

const PostProcessor = require('./PostProcessor.js');

const Form = require('../Form.js');
const Question = require('../statements/Question.js');
const Answer = require('../statements/Answer.js');
const IfStatement = require('../statements/IfStatement.js');
const Expression = require('../expressions/Expression.js');

module.exports = class FormPostProcessor extends PostProcessor {

    constructor(){
        super();

        this.ast = {
            name: '',
            statements: []
        };

    }

    // todo
    form(data, location, reject) {
        console.log(`Form: ${JSON.stringify(data)}`);
        return data;
        return new Form({
            name: data[1][0],
            statements: data[2]
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
        return new Answer({name: data[2].trim(), allocation: data});
    }

    factor(data, location, reject){
        console.log(`Factor: ${JSON.stringify(data)}`);
        return data;
    }

    expression(data, location, reject) {
        console.log(`Expression: ${JSON.stringify(data)}`);
        return data;
        return new Expression({

        });
    }

    operator(data, location, reject) {
        console.log(`Operator: ${JSON.stringify(data)}`);
        return data;
    }

};