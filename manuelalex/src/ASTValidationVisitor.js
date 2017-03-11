/**
 * Created by alexvanmanen on 24-02-17.
 */

import {Form} from './Form.js'
import {Question} from './Statements/Question.js'
import {MemoryState} from './memory/MemoryState.js'


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

    visitExpression(condition) {
        if(condition.operator == undefined){
            this.visitExpression(condition.leftHand);
        } else {




        let typeLeftHand = this.memoryState.getType(condition.leftHand);
        let typeRightHand = this.memoryState.getType(condition.rightHand);

        console.log(typeLeftHand);


        if(["||", "&&"].includes(condition.operator) && (typeLeftHand != "QLBoolean" || typeRightHand != "QLBoolean")){

            let errorStatement = "Invalid expression. The operator "+condition.operator+" can not be applied to " + condition.leftHand +"[type:"+typeLeftHand+"] and "  + condition.rightHand+"[type:"+typeRightHand+"]";
            this.errors.push(errorStatement);
            //throw exception
        }

        }
    }

    hasDetectedErrors(){
        return this.errors.length > 0;
    }
}