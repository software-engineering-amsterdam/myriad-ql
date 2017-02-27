/**
 * Created by alexvanmanen on 24-02-17.
 */

import {Form} from './Form.js'
import {Question} from './Statements/Question.js'
import {MemoryState} from './memory/MemoryState.js'


export class Visitor {


    constructor(options = {}) {
        this.memoryState = new MemoryState();
        this.output = options.output;
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
        this.visitCondition(ifstatement.condition);
        // if(true){
        //     this.visitStatements(ifstatement.ifBody);
        // }
    }

    visitCondition(condition) {

        condition = condition[0]; // fix ast

        let typeLeftHand = this.memoryState.getType(condition.leftHand);
        let typeRightHand = this.memoryState.getType(condition.rightHand);

        if(!typeLeftHand.constructor == Boolean){
            //throw exception
        }




    }
}