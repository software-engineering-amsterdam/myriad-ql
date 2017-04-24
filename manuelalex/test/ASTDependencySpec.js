/**
 * Created by alexvanmanen on 06-02-17.
 */
import chai from 'chai';
import JspmImport       from './JspmImport.js';
const expect = chai.expect;

describe('AST Dependency Visitor', () => {

    let imports = {};

    beforeEach(function () {
        return Promise.all([JspmImport('./src/ASTDependencyVisitor.js'), JspmImport('./src/Form.js'), JspmImport('./src/statements/IFStatement.js'), JspmImport('./src/statements/Question.js'), JspmImport('./src/expressions/Expression.js'), JspmImport('./src/types/Property.js'), JspmImport('./src/memory/MemoryState.js'), JspmImport('./src/types/Types.js')]).then(([{ ASTDependencyVisitor }, { Form }, { IfStatement }, { Question }, { Expression, PrefixExpression }, { Property }, { MemoryState }, { QLMoney, QLString, QLBoolean, QLNumber, QLDate }]) => {
            imports.ASTDependencyVisitor = ASTDependencyVisitor;
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
            form.statements = [ifstatement1, ifstatement2];

            let astDependencyVisitor = new imports.ASTDependencyVisitor();
            astDependencyVisitor.visitStatements([ifstatement1, ifstatement2]);


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

            let astDependencyVisitor = new imports.ASTDependencyVisitor();
            astDependencyVisitor.visitStatements([ifstatement1, ifstatement2]);
            console.log(astDependencyVisitor.innerGraph);
            console.log(astDependencyVisitor.graph);


            expect(astDependencyVisitor.hasDetectedErrors()).to.equal(true);
            console.log("errors: " + astDependencyVisitor.errors);
            done();
        })
    });

});