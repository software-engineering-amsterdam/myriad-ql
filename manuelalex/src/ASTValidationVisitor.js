/**
 * Created by alexvanmanen on 24-02-17.
 */

import find     from 'lodash/find';
import {Expression} from './expressions/Expression.js';
import {MemoryState} from './memory/MemoryState.js';
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from './types/Types.js';

export class ASTValidationVisitor {

    constructor() {
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

    /* TODO, and fix visitor pattern */
    visitForm(form) {
        console.log(form.name);
    }

    visitStatements(statements) {
        for (const statement of statements) {
            statement.accept(this);
        }
    }

    visitQuestion(question) {
        this.memoryState.set(question.propertyName, question.propertyType);


        //TODO: cyclic dependencies between questions
        /*
         if (x) { y: "Y?" boolean }
         if (y) { x: "X?" boolean }


         if (x) { a: "A?" boolean }
         if (!x) { a: "A?" boolean }
         */
        this.checkDuplicateDeclarations(question);
        this.checkDuplicateLabels(question);

    }


    /**
     * Check duplicate question declarations with different types
     * @param question
     */
    checkDuplicateDeclarations(question){
        const memoryElement = this.memoryState.getElement(question.getPropertyName());
        const propertyInMemory = memoryElement !== undefined;
        if(propertyInMemory && question.getPropertyType() !== memoryElement.getType() ){
            this.warnings.push(`Property "${question.getPropertyType()}" is being used with multiple types`);
        }
    }

    checkDuplicateLabels(statement){
        const localLabel = statement.getLabel();
        const label = find(this.labels, (label) => label.contains(localLabel));

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

    /* TODO remove, bad code.  */
    findExpressionInArray(object){
        if(object instanceof Array){
            return this.findExpressionInArray(object[0]);
        } else if (object instanceof Expression) {
            return object;
        }
        return {};
    }

    visitPreExpression(condition){
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
            //this.visitExpression(condition.leftHand);
            const subExpression = this.findExpressionInArray(condition.leftHand);
            subExpression.accept(this);
        } else {
            //Todo: add prefix operator !

            this.validateOperator(condition, ['||', '&&', '=='], QLBoolean.name);
            this.validateOperator(condition, ['<', '>', '>=', '<=', '!=', '==', '*', '/', '+', '-'], QLMoney.name);
            this.validateOperator(condition, ['<', '>', '>=', '<=', '!=', '=='], QLString.name);
            this.validateOperator(condition, ['<', '>', '>=', '<=', '!=', '==', '*', '/', '+', '-'], QLNumber.name);
            this.validateOperator(condition, ['<', '>', '>=', '<=', '!=', '=='], QLDate.name);
        }
    }

    visitProperty(condition) {

    }

    validateOperator(condition, validOperators, validType) {
        const typeLeftHand = this.memoryState.getType(condition.leftHand);
        const typeRightHand = this.memoryState.getType(condition.rightHand);

        if (validOperators.includes(condition.operator)) {
            if (typeLeftHand.constructor.name !== validType || typeRightHand.constructor.name !== validType) {
                this.errors.push(`Invalid expression. The operator ${condition.operator} can not be applied to ${condition.leftHand} [type: ${typeLeftHand}] and ${condition.rightHand}[type:${typeRightHand}]`);
            }
        }
    }

    hasDetectedErrors(){
        return this.errors.length > 0;
    }
}