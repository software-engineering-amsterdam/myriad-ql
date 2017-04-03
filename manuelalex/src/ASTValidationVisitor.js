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
        this.memoryState.set(question.propertyName.name, question.propertyType);


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

    visitIfElseStatement(ifelsestatement) {
        ifelsestatement.condition.accept(this);
    }


    visitPrefixExpression(prefixcondition){
        if(!(prefixcondition.expression.accept(this) instanceof QLBoolean)){
            this.errors.push(`Invalid expression. The prefix operator ${prefixcondition.prefix} can not be applied to ${prefixcondition.expression.name}, because it is not a boolean`);
        }
        condition.expression.accept(this);
    }

    visitExpression(expression) {
        const leftHandType = expression.leftHand.accept(this);
        const rightHandType = expression.rightHand.accept(this);
        const operator = expression.operator;

        console.log(leftHandType);
        if(leftHandType.getType() !== rightHandType.getType()){
            this.errors.push(`Invalid expression. The operator ${operator} can not be applied 
                            to ${expression.leftHand.toString()} [type: ${leftHandType.toString()}] 
                            and ${expression.rightHand.toString()}[type: ${rightHandType.toString()}]`);
        }

        // this.validateOperator(leftHand, rightHand, operator, ['||', '&&', '=='], QLBoolean);
        // this.validateOperator(leftHand, rightHand, operator, ['<', '>', '>=', '<=', '!=', '==', '*', '/', '+', '-'], QLMoney);
        // this.validateOperator(leftHand, rightHand, operator, ['<', '>', '>=', '<=', '!=', '=='], QLString);
        // this.validateOperator(leftHand, rightHand, operator, ['<', '>', '>=', '<=', '!=', '==', '*', '/', '+', '-'], QLNumber);
        // this.validateOperator(leftHand, rightHand, operator, ['<', '>', '>=', '<=', '!=', '=='], QLDate);

        // if(operator.includes('<', '>', '>=', '<=', '!=', '==', '&&', '||')){
        //     return new QLBoolean();
        // } else {
        //     return leftHandType;
        // }
        return expression.getType();
    }

    visitProperty(property) {
        return this.memoryState.getType(property.name);
    }

    validateOperator(leftHand, rightHand, operator, validOperators, validType) {


        if (validOperators.includes(operator)) {
            if (!(leftHand instanceof validType) || !(rightHand instanceof validType)) {
                this.errors.push(`Invalid expression. The operator ${operator} can not be applied to ${leftHand.name} [type: ${leftHand.name}] and ${rightHand.name}[type: ${rightHand.name}]`);
            }
        }
    }

    hasDetectedErrors(){
        return this.errors.length > 0;
    }
}