/**
 * Created by Manuel on 13/02/2017.
 */

let _ = require('lodash');

const PostProcessor = require('./PostProcessor.js');

const Form = require('../Form.js');
const Question = require('../statements/Question.js');
const Answer = require('../statements/Answer.js');

const IfStatement = require('../statements/IfStatement.js');
const IfElseStatement = require('../statements/IfElseStatement.js');
const IfElseIfElseStatement = require('../statements/IfElseIfElseStatement.js');

const Expression = require('../expressions/Expression.js');
const Allocation = require('../allocation/Allocation.js');
const Factor = require('../expressions/Factor.js');

const MinOperator = require('../operator/MinOperator.js');
const PlusOperator = require('../operator/PlusOperator.js');
const DivideOperator = require('../operator/DivideOperator.js');
const MultiplyOperator = require('../operator/MultiplyOperator.js');
const Money = require('../properties/Money.js');

module.exports = class FormPostProcessor extends PostProcessor {

    constructor() {
        super();
    }

    // todo
    form(data, location, reject) {
        return new Form({
            name: data[2][0],
            statements: _.flattenDeep(data[6])
        });
    }

    statement(data, location, reject) {
        return data[0];
    }

    question(data, location, reject) {
        return new Question({ name: data[3].trim(), propertyName: data[6], propertyType: data[9], location });
    }

    // ifBody statements are one level too deep
    ifStatement(data, location, reject) {
        return new IfStatement({ condition: data[2][1], ifBody: data[3][3], location });
    }

    ifElseStatement(data, location, reject) {
        return new IfElseStatement(_.merge(data[0].getOptions(), { location, elseBody: _.flattenDeep(data[1][4]) }));
    }

    ifElseIfElseStatement(data, location, reject) {
        return new IfElseIfElseStatement(_.merge(data[0].getOptions(), {
            location,
            elseIfConditional: data[3][1],
            elseIfBody: _.flattenDeep(data[4][3]),
            elseBody: _.flattenDeep(data[5][4])
        }));
    }

    // We may have to retrieve the allocation by retrieving the type 'Allocation' from the array
    answer(data, location, reject) {
        return new Answer({ name: data[3].trim(), allocation: data[6, location] });
    }

    // todo probably obsolete
    factor(data, location, reject) {

        if (data[1] instanceof Expression) {
            return
        }
        /* Factor is an Expression */
        if (data.length === 3) {
            return data[1];
        } else {
            return new Factor({});
        }
    }

    // todo
    allocation(data, location, reject) {
        return new Allocation({
            propertyName: data[0].trim(),
            type: data[3],
            expression: _.flattenDeep(data[7])[1],
            location
        });
    }

    // todo
    expression(data, location, reject) {
        if (data.length === 3) {
            return new Expression({
                term: _.flattenDeep(data[0])[0],
                operator: _.flattenDeep(data[1])[0],
                expression: _.flattenDeep(data[2])[0],
                location
            });
        } else {
            console.error(`Retrieved different expression: ${JSON.stringify(data)} at location ${location}`);
        }

    }

    // todo probably obsolete
    operator(data, location, reject) {
        return data;
    }

    minOp(data, location, reject) {
        return new MinOperator({ location });
    }

    plusOp(data, location, reject) {
        return new PlusOperator({ location });
    }

    divideOp(data, location, reject) {
        return new DivideOperator({ location });
    }

    multiplyOp(data, location, reject) {
        return new MultiplyOperator({ location });
    }

    money(data, location, reject) {
        return Money
    }

};