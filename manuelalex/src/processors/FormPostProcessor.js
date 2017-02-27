/**
 * Created by Manuel on 13/02/2017.
 */

import _    from 'lodash';

import {PostProcessor} from './PostProcessor.js';

import {Form} from '../Form.js';
import {Question} from '../statements/Question.js';
import {Answer} from '../statements/Answer.js';

import {IfStatement} from '../statements/IfStatement.js';
import {IfElseStatement} from '../statements/IfElseStatement.js';
import {IfElseIfElseStatement} from '../statements/IfElseIfElseStatement.js';

import {Comparison} from '../expressions/Comparison.js';
import {Expression} from '../expressions/Expression.js';
import {Allocation} from '../allocation/Allocation.js';
import {Factor} from '../expressions/Factor.js';

import {MinOperator} from '../operator/MinOperator.js';
import {PlusOperator} from '../operator/PlusOperator.js';
import {DivideOperator} from '../operator/DivideOperator.js';
import {MultiplyOperator} from '../operator/MultiplyOperator.js';
import {Money} from '../properties/Money.js';

export class FormPostProcessor extends PostProcessor {

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

    statement(data) {
        return data[0];
    }

    question(data, location) {
        return new Question({ name: data[3].trim(), propertyName: data[6], propertyType: data[9], location });
    }

    ifStatement(data, location) {
        return new IfStatement({ condition: _.flattenDeep(data[2][1]), ifBody: data[3][3], location });
    }

    ifElseStatement(data, location) {
        return new IfElseStatement(_.merge(data[0].getOptions(), { location, elseBody: _.flattenDeep(data[1][4]) }));
    }

    ifElseIfElseStatement(data, location) {
        return new IfElseIfElseStatement(_.merge(data[0].getOptions(), {
            location,
            elseIfCondition: data[3][1],
            elseIfBody: _.flattenDeep(data[4][3]),
            elseBody: _.flattenDeep(data[5][4])
        }));
    }

    // We may have to retrieve the allocation by retrieving the type 'Allocation' from the array
    answer(data, location) {
        return new Answer({ name: data[3].trim(), allocation: data[6], location });
    }

    allocation(data, location) {
        return new Allocation({
            propertyName: data[0].trim(),
            type: data[3],
            expression: _.flattenDeep(data[7])[1],
            location
        });
    }

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
            return reject;
        }
    }

    booleanExpression(data) {
        return data;
    }

    comparison(data, location) {
        return new Comparison({
            location,
            leftHand: data[0],
            rightHand: data[4],
            comparisonOperator: data[2][0]
        });
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
        return new MinOperator({ location });
    }

    plusOp(data, location) {
        return new PlusOperator({ location });
    }

    divideOp(data, location) {
        return new DivideOperator({ location });
    }

    multiplyOp(data, location) {
        return new MultiplyOperator({ location });
    }

    money(data, location) {
        return new Money({ location })
    }

}
