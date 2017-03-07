/**
 * Created by alexvanmanen on 24-02-17.
 */

import {Form} from './Form.js'
import {Question} from './Statements/Question.js'
import {MemoryState} from './memory/MemoryState.js'


export class ASTValidationVisitor {


    constructor(options = {}) {
        this.memoryState = new MemoryState();
        this.output = options.output;
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

        console.log(typeLeftHand)
        if(condition.operator =="||" && (typeLeftHand != "QLBoolean" || typeRightHand != "QLBoolean")){
            console.log("Expression invalid: " + condition.leftHand +"["+typeLeftHand+"]" + condition.operator + condition.rightHand+"["+typeRightHand+"]");
            //throw exception
        }

        }
    }


}