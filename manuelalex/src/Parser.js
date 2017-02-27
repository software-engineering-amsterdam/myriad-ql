import nearley                                   from 'nearley';
import  './grammar.js';

import {test1, test2, test3, test4 ,test5}       from './test/TestStrings.js';
import {Form}                                    from './Form.js';

import {Question}                                from './statements/Question.js';
import {Answer}                                  from './statements/Answer.js';
import {Generator}                               from './gui/Generator.js';
import {AST}                                     from './ast/AST.js';
import {FormPostProcessor}                       from './processors/FormPostProcessor.js';
import {Visitor}                                 from './Visitor.js';


/**
 * To build the grammer: nearleyc grammar.ne -o grammar.js
 * (Building the grammar is not yet supported due to us requiring the PostProcessor inside the grammar, need to find a solution)
 * @type {Parser}
 */
export class Parser {
    constructor() {
       this._run();
    }

    _run() {
        window.FormPostProcessor = new FormPostProcessor();
        // grammar = window.grammar;
        // Create a Parser object from our grammar.
        const parser = new nearley.Parser(grammar.ParserRules, grammar.ParserStart);
        let result = [];
        let testString;
        try {
            testString = test1.replace(/(\r\n|\n|\r)/gm, "");
            console.log(`Testing: ${test1}`);
            result = parser.feed(testString).results;
        } catch (parseError) {
            console.log(`Error at character ${parseError.offset}`);
            console.log(`${parseError}`);
        }
        if (result.length > 1) {
            console.error('Ambigious parsing ' + result.length + ' options, chosing the first parsing');
            for (let i = 0; i < result.length; i++) {
                console.log(JSON.stringify(result[i]));
            }
            result = result[0];
        }
        console.log(`Result: ${JSON.stringify(result)}`);
        this.AST = this.makeAST(result[0]);
        this.evaluate();
        // let generator = new Generator(AST);

        return result;
    }

    evaluate(){

        let visitor = new Visitor();
        visitor.visitAST(this.AST);


        // for (let statement of this.AST.program.statements) {
        //     console.log(statement);
        //     statement.accept(visitor);
        //
        // }
    }

    makeAST(result = {}) {
        return new AST(result);
    }

    //TODO: parse function should be implemented using the result of-> "parser.feed(input).results"; Now it's test data
    parse(input, test = 2) {
        let f = new Form();
        if (test == 3) {
            let q1 = new Question();
            q1.name = "Q1";
            q1.propertyName = "sellingPrice";
            q1.type = "money";
            f.name = "taxOfficeExample";
            f.statements = [q1];
        } else if (test == 4) {
            let q1 = new Question();
            q1.name = "Q1";
            q1.propertyName = "sellingPrice";
            q1.type = "money";
            let q2 = new Question();
            q2.name = "Q2";
            q2.propertyName = "privateDebt";
            q2.type = "money";
            let a2 = new Answer();
            a2.name = "not defined";
            a2.type = "money";
            a2.propertyName = "A2";
            a2.expression = "(Q1 - Q2)";
            f.statements = [q1, q2, a2];
            f.name = "taxOfficeExample";
        } else {
            f.name = "y";
        }
        return f;
    }
};






