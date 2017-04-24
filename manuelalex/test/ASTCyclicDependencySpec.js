/**
 * Created by alexvanmanen on 06-02-17.
 */
import chai from 'chai';
import JspmImport       from './JspmImport.js';
const expect = chai.expect;

describe('AST AST Cyclic Dependency Visitor', () => {

    let imports = {};

    beforeEach(function () {
        return Promise.all([JspmImport('./src/ast/visitors/ASTCyclicDependencyVisitor.js'), JspmImport('./src/Form.js'), JspmImport('./src/statements/IFStatement.js'), JspmImport('./src/statements/Question.js'), JspmImport('./src/expressions/Expression.js'), JspmImport('./src/types/Property.js'), JspmImport('./src/memory/MemoryState.js'), JspmImport('./src/types/Types.js')]).then(([{ ASTCyclicDependencyVisitor }, { Form }, { IfStatement }, { Question }, { Expression, PrefixExpression }, { Property }, { MemoryState }, { QLMoney, QLString, QLBoolean, QLNumber, QLDate }]) => {
            imports.ASTCyclicDependencyVisitor = ASTCyclicDependencyVisitor;
            imports.Form = Form;
            imports.IfStatement = IfStatement;
            imports.Question = Question;
            imports.Expression = Expression;
            imports.PrefixExpression = PrefixExpression;
            imports.Property = Property;
            imports.MemoryState = MemoryState;
            imports.QLMoney = QLMoney;
            imports.QLString = QLString;
            imports.QLBoolean = QLBoolean;
            imports.QLNumber = QLNumber;
            imports.QLDate = QLDate;


        });
    });


    /**
     * testing the following case
     * if (x) { a: "a?" boolean }
     * if (y) { b: "b?" boolean }
     **/
    describe('Test cyclic dependencies between questions', () => {
        it('if (x) { a: "a?" boolean } \n f (y) { b: "b?" boolean }', (done) => {
            let form = new imports.Form();
            let x = new imports.Property("x", 2);
            let y = new imports.Property("y", 4);

            let expressionX = new imports.PrefixExpression();
            expressionX.expression = x;
            let expressionY = new imports.PrefixExpression();
            expressionY.expression = y;
            let questionA = new imports.Question("A?", "a", "QLBoolean", 3);
            let questionB = new imports.Question("B?", "b", "QLBoolean", 5);

            let ifstatement1 = new imports.IfStatement(expressionX, [questionA], 2);
            let ifstatement2 = new imports.IfStatement(expressionY, [questionB], 4);


            let astDependencyVisitor = new imports.ASTCyclicDependencyVisitor();
            astDependencyVisitor.visitStatements([ifstatement1, ifstatement2]);


            expect(astDependencyVisitor.hasDetectedErrors()).to.equal(false);
            done();
        })
    });



    /**
     * testing the following case
     * if (x) { y: "Y?" boolean }
     * if (y) { x: "X?" boolean }
     **/
    describe('Test cyclic dependencies between questions', () => {
        it('if (x) { y: "Y?" boolean } \n if (y) { x: "X?" boolean }', (done) => {
            let form = new imports.Form();
            let x = new imports.Property("x", 2);
            let y = new imports.Property("y", 4);

            let expressionX = new imports.PrefixExpression();
            expressionX.expression = x;
            let expressionY = new imports.PrefixExpression();
            expressionY.expression = y;
            let questionY = new imports.Question("Y?", "y", "QLBoolean", 3);
            let questionX = new imports.Question("X?", "x", "QLBoolean", 5);

            let ifstatement1 = new imports.IfStatement(expressionX, [questionY], 2);
            let ifstatement2 = new imports.IfStatement(expressionY, [questionX], 4);


            let astDependencyVisitor = new imports.ASTCyclicDependencyVisitor();
            astDependencyVisitor.visitStatements([ifstatement1, ifstatement2]);


            expect(astDependencyVisitor.hasDetectedErrors()).to.equal(true);
            done();
        })
    });


    /**
     * testing the following case
     * if (x) {
     *      if(y) {
     *          z: "Z?" boolean
     *      }
     * }
     * if (z) {
     *      if(y) {
     *          x: "X?" boolean
     *      }
     * }
     **/
    describe('Test cyclic dependencies between questions', () => {
        it('if (x) { if(y) { z: "Z?" boolean}} \n if (z) { if(y) { x: "X?" boolean }}', (done) => {
            let form = new imports.Form();
            let x = new imports.Property("x", 2);
            let y = new imports.Property("y", 4);
            let z = new imports.Property("z", 6);

            let expressionX = new imports.PrefixExpression();
            expressionX.expression = x;
            let expressionY = new imports.PrefixExpression();
            expressionY.expression = y;
            let expressionZ = new imports.PrefixExpression();
            expressionZ.expression = z;


            let questionZ = new imports.Question("Z?", "z", "QLBoolean", 3);
            let questionX = new imports.Question("X?", "x", "QLBoolean", 5);

            let ifstatementY1 = new imports.IfStatement(expressionX, [questionZ], 2);
            let ifstatementX = new imports.IfStatement(expressionX, [ifstatementY1], 2);

            let ifstatementY2 = new imports.IfStatement(expressionX, [ifstatementY2], 2);
            let ifstatementZ = new imports.IfStatement(expressionX, [questionX], 2);


            let astDependencyVisitor = new imports.ASTCyclicDependencyVisitor();
            astDependencyVisitor.visitStatements([ifstatementX, ifstatementZ]);


            expect(astDependencyVisitor.hasDetectedErrors()).to.equal(true);
            done();
        })
    });

    /**
     * testing the following case
     * if (x) { a: "A?" boolean }
     * if (!x) { a: "A?" boolean }
     **/
    describe('Test cyclic dependencies between questions', () => {


        it('if (x) { a: "A?" boolean } \n if (!x) { a: "A?" boolean }', (done) => {
            let form = new imports.Form();
            let x = new imports.Property("x", 2);

            let expressionX = new imports.PrefixExpression();
            expressionX.expression = x;
            let expressionNotX = new imports.PrefixExpression("!", x, 4);


            let questionA1 = new imports.Question("A?", "a", "QLBoolean", 3);
            let questionA2 = new imports.Question("A?", "a", "QLBoolean", 5);

            let ifstatement1 = new imports.IfStatement(expressionX, [questionA1], 2);
            let ifstatement2 = new imports.IfStatement(expressionNotX, [questionA2], 4);
            form.statements = [ifstatement1, ifstatement2];

            let astDependencyVisitor = new imports.ASTCyclicDependencyVisitor();
            astDependencyVisitor.visitStatements([ifstatement1, ifstatement2]);
            console.log(astDependencyVisitor.innerGraph);
            console.log(astDependencyVisitor.graph);


            expect(astDependencyVisitor.hasDetectedErrors()).to.equal(true);
            console.log("errors: " + astDependencyVisitor.errors);
            done();
        })
    });

});