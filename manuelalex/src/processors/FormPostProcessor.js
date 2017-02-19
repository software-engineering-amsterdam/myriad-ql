/**
 * Created by Manuel on 13/02/2017.
 */

const PostProcessor = require('./PostProcessor.js');

const Form = require('../Form.js');
const Question = require('../statements/Question.js');
const Answer = require('../statements/Answer.js');
const IfStatement = require('../statements/IfStatement.js');

module.exports = class FormPostProcessor extends PostProcessor {

    // todo
    static form(data, location, reject) {
        console.log(`Form: ${JSON.stringify(data)}`);
        return new Form({
            name: data[1][0],
            statements: data[2]
        });
    }

    // todo
    static statements(data, location, reject) {
        console.log(`Statements: ${JSON.stringify(data)}`);
        return data;
    }

    // todo should not been called
    static statement(data, location, reject) {
        console.log(`Statement: ${JSON.stringify(data)}`);
        return data[0];
    }

    static question(data, location, reject) {
        console.log(`Question: ${JSON.stringify(data)}`);
        return new Question({ name: data[0], propertyName: data[3][0], type: data[6][0] });
    }

    static ifStatement(data, location, reject) {
        console.log(`ifStatement: ${JSON.stringify(data)}`);
        return new IfStatement({ name: data[0], propertName: [] });
    }

    // todo
    static answer(data, location, reject) {
        console.log(`Answer: ${JSON.stringify(data)}`);
        return new Answer({});
    }

    static expression(data, location, reject) {
        console.log(`Expression: ${JSON.stringify(data)}`);
        return data;
    }

    static operator(data, location, reject) {
        console.log(`Operator: ${JSON.stringify(data)}`);
        return data;
    }

};