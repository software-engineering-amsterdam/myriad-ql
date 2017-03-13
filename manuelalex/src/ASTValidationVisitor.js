/**
 * Created by alexvanmanen on 24-02-17.
 */

import find     from 'lodash/find';
import {Form} from './Form.js'
import {Question} from './Statements/Question.js'
import {Expression} from './expressions/Expression.js'
import {MemoryState} from './memory/MemoryState.js'
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from './types/Types.js';


export class ASTValidationVisitor {



    constructor(options = {}) {
        this.memoryState = new MemoryState();
        this.errors = [];
        this.warnings = [];
        this.labels = [];
    }

    getMemoryState(){
        return this.memoryState;
    }


    visitAST(ast){
        this.visitForm(ast.getProgram());
        this.visitStatements(ast.getStatements());

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
        this.memoryState.set(question.propertyName, question.propertyType);
        //TODO: duplicate question declarations with different types
        /*
         def a: "A?" boolean
         if (x) { a }
         if (!x) { a }
         */

        //TODO: cyclic dependencies between questions
        /*
         if (x) { y: "Y?" boolean }
         if (y) { x: "X?" boolean }


         if (x) { a: "A?" boolean }
         if (!x) { a: "A?" boolean }
         */


        this.checkDuplicateLabels(question);


    }

    checkDuplicateLabels(statement){
        let localLabel = statement.getLabel();
        let label = find(this.labels, (label)=>{
           return label.contains(localLabel);
        });

        if(label){
            this.warnings.push(`Label "${localLabel.getValue()}" is being used multiple times`);
        } else {
            this.labels.push(statement.getLabel());
        }

    }

    visitAnswer(answer) {
        //TODO: reference to undefined questions
        this.checkDuplicateLabels(answer);
    }

    visitIfStatement(ifstatement) {
        ifstatement.condition.accept(this);
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
            //Todo: add prefix operator !

            this.validateOperator(condition, ["||", "&&", "=="], QLBoolean.name);
            this.validateOperator(condition, ["<", ">", ">=", "<=", "!=", "==", "*", "/", "+", "-"], QLMoney.name);
            this.validateOperator(condition, ["<", ">", ">=", "<=", "!=", "=="], QLString.name);
            this.validateOperator(condition, ["<", ">", ">=", "<=", "!=", "==", "*", "/", "+", "-"], QLNumber.name);
            this.validateOperator(condition, ["<", ">", ">=", "<=", "!=", "=="], QLDate.name);
        }
    }



    validateOperator(condition, validOperators, validType) {
        let typeLeftHand = this.memoryState.getType(condition.leftHand);
        let typeRightHand = this.memoryState.getType(condition.rightHand);

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