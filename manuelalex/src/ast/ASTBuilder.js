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
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from '../types/Types.js';
import {Property} from '../types/Property.js';
import {Numbers} from '../types/Numbers.js';
import {Label} from '../Label.js';

export class ASTBuilder {

    form(data, location) {
        return new Form(data[2][0], _.flattenDeep(data[6]), location);
    }

    question(data, location) {
        return new Question(new Label(data[3].trim()), data[6], data[9], location);
    }

    ifStatement(data, location) {
        if (data[3] instanceof Expression){
            return new IfStatement(data[3], _.flattenDeep(data[5][3]), location);
        } else {
            return new IfStatement(_.flattenDeep(data[3])[0], _.flattenDeep(data[5][3]), location);
        }


    }

    ifElseStatement(data, location) {
        const ifStatement = data[0];
        return new IfElseStatement(ifStatement.getCondition(), ifStatement.getIfBody(), _.flattenDeep(data[1][1][3]), location);
    }

    answer(data, location) {
        return new Answer(data[3].trim(), data[6], location);
    }

    allocation(data, location) {
        return new Allocation(data[0], data[3], _.flattenDeep(data[7])[0], location);
    }

    expression(data, location) {
        return new Expression(_.flattenDeep(data[0])[0], data[2], _.flattenDeep(data[4])[0], location);
    }

    prefixExpression(data, location) {
        return new PrefixExpression(_.flattenDeep(data[0])[0], _.flattenDeep(data[1])[0], location);
    }

    deepExpression(data, location) {
        return new Expression(_.flattenDeep(data[0])[0], data[2][0], _.flattenDeep(data[4])[0], location);
    }

    // todo determine if this is deprecated
    factor(data) {
        if (data.length > 1) {
            return data[1];
        } else {
            return data;
        }
    }

    plusMinExpression(data, location) {
        return new Expression(_.flattenDeep(data[0])[0], _.flattenDeep(data[1])[0], _.flattenDeep(data[2])[0], location);
    }

    booleanExpression(data) {
        return data;
    }

    operator(data) {
        return data;
    }

    money(location) {
        return new QLMoney(location);
    }

    string(location) {
        return new QLString(location);
    }

    number(location) {
        return new QLNumber(location);
    }

    date(location) {
        return new QLDate(location);
    }

    boolean(location) {
        return new QLBoolean(location);
    }

    property(data, location) {
        return new Property(_.flattenDeep(data[0]).join(""), location);
    }

    numbers(data, location) {
        return new Numbers(data, location);
    }
}
