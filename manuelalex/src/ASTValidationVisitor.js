/**
 * Created by alexvanmanen on 24-02-17.
 */

import {Form} from './Form.js'
import {Question} from './Statements/Question.js'
import {Expression} from './expressions/Expression.js'
import {MemoryState} from './memory/MemoryState.js'
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from './types/Types.js';


export class ASTValidationVisitor {



    constructor(options = {}) {
        this.memoryState = new MemoryState();
        this.errors = [];
    }

    getMemoryState(){
        return this.memoryState;
    }


    visitAST(ast){
        this.visitForm(ast.program.name);
        this.visitStatements(ast.program.statements);

    }

    /**
     * @param {Form} form
     */
    visitForm(form) {
        console.log(form.name);
    }

    visitStatements(statements) {
        for (let statement of statements) {
            console.log(statement);
            statement.accept(this);
        }
    }

    /**
     * @param {Question} question
     */
    visitQuestion(question) {

        console.log("The question is:" + question.name);
        console.log("The question type is :" + question.propertyType);
        this.memoryState.set(question.propertyName, question.propertyType);

    }

    visitAnswer(answer) {
        console.log("The question is:" + answer.name);
        console.log("The question type is :" + answer.propertyType);
    }

    visitIfStatement(ifstatement) {
        console.log("The question is:" + ifstatement.condition);
        console.log("The question type is :" + ifstatement.ifBody);

        ifstatement.condition.accept(this);
        // if(true){
        //     this.visitStatements(ifstatement.ifBody);
        // }
    }

    findExpressionInArray(object){
        if(object instanceof Array){
            return this.findExpressionInArray(object[0]);
        } else if (object instanceof Expression) {
            return object;
        }
    }

    visitExpression(condition) {
        if(condition.leftHand instanceof Expression){
            condition.leftHand.accept(this);
        }

        if(condition.rightHand instanceof Expression){
            condition.rightHand.accept(this);
        }

        if (condition.operator == undefined){
            //this.visitExpression(condition.leftHand);
            let subExpression = this.findExpressionInArray(condition.leftHand);
            subExpression.accept(this);
        } else {

            this.validateOperator(condition, ["||", "&&", "=="], "QLBoolean");
            this.validateOperator(condition, ["<", ">", ">=", "<=", "!=", "=="], "QLMoney");

        }
    }

    validateOperator(condition, validOperators, validType) {
        let typeLeftHand = this.memoryState.getType(condition.leftHand);
        let typeRightHand = this.memoryState.getType(condition.rightHand);

        console.log(condition);
        console.log(validType);
        if (validOperators.includes(condition.operator)) {
            if (typeLeftHand.constructor.name != validType || typeRightHand.constructor.name != validType) {
                let errorStatement = `Invalid expression. The operator ${condition.operator} can not be applied to ${condition.leftHand} [type: ${typeLeftHand}] and ${condition.rightHand}[type:${typeRightHand}]`;
                this.errors.push(errorStatement);
            }
        }
    }

    hasDetectedErrors(){
        return this.errors.length > 0;
    }
}