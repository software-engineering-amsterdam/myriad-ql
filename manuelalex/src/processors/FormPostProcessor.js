/**
 * Created by Manuel on 13/02/2017.
 */

import _    from 'lodash';

import {Form} from '../Form.js';
import {Question} from '../statements/Question.js';
import {Answer} from '../statements/Answer.js';

import {IfStatement} from '../statements/IfStatement.js';
import {IfElseStatement} from '../statements/IfElseStatement.js';

import {Comparison} from '../expressions/Comparison.js';
import {Expression} from '../expressions/Expression.js';
import {Allocation} from '../allocation/Allocation.js';

import {MinOperator} from '../operator/MinOperator.js';
import {PlusOperator} from '../operator/PlusOperator.js';
import {DivideOperator} from '../operator/DivideOperator.js';
import {MultiplyOperator} from '../operator/MultiplyOperator.js';
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from '../types/Types.js';
import {Label} from '../Label.js';

export class FormPostProcessor {

    form(data, location, reject) {
        return new Form(data[2][0], _.flattenDeep(data[6]), location);
    }

    question(data, location) {
        return new Question(new Label(data[3].trim()), data[6], data[9], location);
    }

    ifStatement(data, location) {
        return new IfStatement(data[3], _.flattenDeep(data[5][3]), location);
    }

    ifElseStatement(data, location) {
        let ifStatement = data[0];
        return new IfElseStatement(ifStatement.getCondition(), ifStatement.getIfBody(), _.flattenDeep(data[1][4]), location);
    }

    // We may have to retrieve the allocation by retrieving the type 'Allocation' from the array
    answer(data, location) {
        return new Answer(data[3].trim(), data[6], location);
    }

    allocation(data, location) {
        return new Allocation(data[0].trim(), data[3], _.flattenDeep(data[7])[1], location);
    }

    expression(data, location, reject) {
        return new Expression(_.flattenDeep(data[0])[0], data[2], _.flattenDeep(data[4])[0], location);
    }

    plusMinExpression(data, location, reject) {
        return new Expression(_.flattenDeep(data[0])[0],_.flattenDeep(data[1])[0], _.flattenDeep(data[2])[0], location);
    }

    booleanExpression(data) {
        return data;
    }

    comparison(data, location) {
        return new Comparison(data[0], data[2][0], data[4], location);
    }

    and_test(data) {
        return data;
    }

    not_test(data) {
        return data;
    }

    operator(data) {
        return data;
    }

    minOp(data, location) {
        return new MinOperator(location);
    }

    plusOp(data, location) {
        return new PlusOperator(location);
    }

    divideOp(data, location) {
        return new DivideOperator(location);
    }

    multiplyOp(data, location) {
        return new MultiplyOperator(location);
    }

    money(data, location) {
        return new QLMoney(location)
    }

    string(data, location) {
        return new QLString(location);
    }

    number(data, location) {
        return new QLNumber(location);
    }

    date(data, location) {
        return new QLDate(location)
    }

    boolean(data, location) {
        return new QLBoolean(location);
    }

    toString(data) {
        return data.join().split(",").join("");
    }

    toNull() {
        return null;
    }

}
