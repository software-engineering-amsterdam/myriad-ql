/**
 * Created by Manuel on 13/02/2017.
 */

import _    from 'lodash';

import {Form} from '../Form.js';
import {Question} from '../statements/Question.js';
import {Answer} from '../statements/Answer.js';
import {IfStatement} from '../statements/IFStatement.js';
import {IfElseStatement} from '../statements/IfElseStatement.js';
import {Expression, PrefixExpression} from '../expressions/Expression.js';
import {Allocation} from '../allocation/Allocation.js';
import {PlusOperator, MinOperator, DivideOperator, MultiplyOperator} from '../operator/Operators.js';
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from '../types/Types.js';
import {Property} from '../types/Property.js';
import {Label} from '../Label.js';

export class ASTBuilder {

    form(data, location, reject) {
        return new Form(data[2][0], _.flattenDeep(data[6]), location);
    }

    question(data, location) {
        return new Question(new Label(data[3].trim()), data[6], data[9], location);
    }

    ifStatement(data, location) {
        return new IfStatement(_.flattenDeep(data[3])[0], _.flattenDeep(data[5][3]), location);
    }

    ifElseStatement(data, location) {
        const ifStatement = data[0];
        return new IfElseStatement(ifStatement.getCondition(), ifStatement.getIfBody(), _.flattenDeep(data[1][1][3]), location);
    }

    // We may have to retrieve the allocation by retrieving the type 'Allocation' from the array
    answer(data, location) {
        return new Answer(data[3].trim(), data[6], location);
    }

    allocation(data, location) {
        return new Allocation(data[0], data[3], _.flattenDeep(data[7])[0], location);
    }

    expression(data, location, reject) {
        return new Expression(_.flattenDeep(data[0])[0], data[2], _.flattenDeep(data[4])[0], location);
    }

    /* needs to be adjusted */
    prefixExpression(data, location, reject) {
        return new PrefixExpression(data[0], _.flattenDeep(data[1])[0], location);
    }

    deepExpression(data, location, reject) {
        return new Expression(_.flattenDeep(data[0])[0], data[2][0], _.flattenDeep(data[4])[0], location);
    }

    factor(data, location, reject) {
        if (data.length > 1) {
            return data[1];
        } else {
            return data;
        }
    }

    plusMinExpression(data, location, reject) {
        return new Expression(_.flattenDeep(data[0])[0], _.flattenDeep(data[1])[0], _.flattenDeep(data[2])[0], location);
    }

    booleanExpression(data) {
        return data;
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
        return new QLMoney(location);
    }

    string(data, location) {
        return new QLString(location);
    }

    number(data, location) {
        return new QLNumber(location);
    }

    date(data, location) {
        return new QLDate(location);
    }

    boolean(data, location) {
        return new QLBoolean(location);
    }

    property(data, location) {
        return new Property(data[0].join(""), location);

    }

    toString(data) {
        return data.join().split(',').join('');
    }

    toNull() {
        return null;
    }

}
