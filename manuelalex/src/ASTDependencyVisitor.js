/**
 * Created by alexvanmanen on 24-02-17.
 */

import find     from 'lodash/find';
import {Expression} from './expressions/Expression.js';
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from './types/Types.js';

export class ASTDependencyVisitor {

    constructor() {
        this.graph = [];
        this.errors = [];
        this.depth = 0;
    }


    visitAST(ast){
   //     this.visitForm(ast.getProgram());
        this.visitStatements(ast.getStatements());

    }


    // visitForm(form) {
    //
    // }

    visitStatements(statements) {
        for (const statement of statements) {
            statement.accept(this);
        }
    }

    visitQuestion(question) {
        console.log(question.propertyName);
    }


    visitAnswer(answer) {

    }

    visitIfStatement(ifstatement) {
        this.depth++;
        ifstatement.condition.accept(this);
    }


    visitPrefixExpression(condition){
        condition.expression.accept(this);
    }

    visitExpression(condition) {
        if(condition.leftHand instanceof Expression){
            condition.leftHand.accept(this);
        }

        if(condition.rightHand instanceof Expression){
            condition.rightHand.accept(this);
        }

        if (condition.operator === undefined){
            condition.leftHand.accept(this);
        } else {
            //Todo: add prefix operator !
            condition.leftHand.accept(this);
            condition.rightHand.accept(this);
            // const typeLeftHand = this.memoryState.getType(condition.leftHand);
            // const typeRightHand = this.memoryState.getType(condition.rightHand);
            // console.log(`Invalid expression. The operator ${condition.operator} can not be applied to ${condition.leftHand} [type: ${typeLeftHand}] and ${condition.rightHand}[type:${typeRightHand}]`);
        }
    }

    visitProperty(property){
        this.graph.push(property.getName());
    }

    hasDetectedErrors(){
        return this.errors.length > 0;
    }
}