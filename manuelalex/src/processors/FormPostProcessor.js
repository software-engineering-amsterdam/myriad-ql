/**
 * Created by Manuel on 13/02/2017.
 */

const PostProcessor     = require('./PostProcessor.js');

const Form              = require('../Form.js');
const Question          = require('../statements/Question.js');
const Answer            = require('../statements/Answer.js');
const IFStatement       = require('../statements/IFStatement.js');

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
        return data;
    }

    static question(data, location, reject) {
        return new Question({ name: data[0], propertyName: data[3][0], type: data[6][0] });
    }

    // todo
    static answer(data, location, reject) {
        console.log(`Answer: ${JSON.stringify(data)}`);
        return new Answer({});
    }

    // todo
    static ifStatement(data, location, reject) {
        return data;
    }

    static expression(data, location, reject) {
        return data;
    }

};