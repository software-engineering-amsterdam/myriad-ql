/**
 * Created by alexvanmanen on 06-02-17.
 */
import chai from 'chai';
// import Parser from '../src/Parser.js';
import JspmImport       from './JspmImport.js';
import {
    loadDependencies,
    mockDependency
}                               from './Bootstrap.js';

const expect = chai.expect;

describe('Test ASTValidator', () => {

    let imports = {};

    beforeEach(function () {
        return Promise.all([JspmImport('./src/ASTValidationVisitor.js'), JspmImport('./src/Form.js'), JspmImport('./src/expressions/Expression.js'), JspmImport('./src/types/Property.js'), JspmImport('./src/memory/MemoryState.js'), JspmImport('./src/types/Types.js')]).then(([{ASTValidationVisitor}, {Form}, {Expression}, {Property}, {MemoryState}, {QLBoolean, QLMoney}]) => {
            imports.ASTValidationVisitor = ASTValidationVisitor;
            imports.Form = Form;
            imports.Expression = Expression;
            imports.Property = Property;
            imports.MemoryState = MemoryState;
            imports.QLBoolean = QLBoolean;
            imports.QLMoney = QLMoney;


        });
    });

    describe('General tests', () => {
        it('returns valid', (done) => {

            let astValidationVisitor = new imports.ASTValidationVisitor();
            let form = new imports.Form();
            astValidationVisitor.visitForm(form);
            done();

            // let form = new Form();
            // //let ast = new AST();
            // //ast.program = '{program: '+ form +' }';dd
            //
            //d
            // form.name = "taxOfficeExample";
            // //form.statements =
            // let ql = new QLMoney();
            // let question = new Question();

            //let jsonAST = '{"program":{"name":"taxOfficeExample","statements":[{"location":26,"label":{"value":"Did you sell a house in 2010?"},"propertyName":{"name":"hasSoldHouse","location":70},"propertyType":{"location":84}},{"location":95,"label":{"value":"Did you buy a house in 2010?"},"propertyName":{"name":"hasBoughtHouse","location":138},"propertyType":{"location":154}},{"location":163,"label":{"value":"Did you enter a loan?"},"propertyName":{"name":"hasMaintLoan","location":199},"propertyType":{"location":213}},{"location":223,"condition":{"leftHand":{"leftHand":{"name":"hasSoldHouse","location":228},"operator":"&&","rightHand":{"name":"hasSoldHouse","location":244},"location":228},"operator":"||","rightHand":{"leftHand":{"name":"hasBoughtHouse","location":262},"operator":"<","rightHand":{"name":"hasBoughtHouse","location":279},"location":262},"location":227},"ifBody":[{"location":305,"label":{"value":"What was the selling price?"},"propertyName":{"name":"sellingPrice","location":351},"propertyType":{"location":365}},{"location":378,"label":{"value":"What was the private debts for the sold house?"},"propertyName":{"name":"privateDebt","location":443},"propertyType":{"location":456}},{"location":468,"label":{"value":"Value residue:"},"allocation":{"propertyName":{"name":"valueResidue","location":499},"type":{"location":513},"expression":{"leftHand":{"name":"hasSoldHouse","location":522},"operator":"-","rightHand":{"name":"hasMaintLoan","location":535},"location":522},"location":499}}]}],"location":0}}';
            //let ast = JSON.parse(form);

            //astValidationVisitor.visitForm(form);
            // done();

        })
    });


    describe('Validate expressions', () => {
        it('QLBoolean && QLBoolean -> OK', (done) => {
            let astValidationVisitor = validate(new imports.QLBoolean(), new imports.QLBoolean(), "&&")
            expect(astValidationVisitor.hasDetectedErrors()).to.equal(false);
            done();
        })
    });

    describe('Check expressions', () => {
        it('QLBoolean && QLMoney -> Detects Error', (done) => {
            let astValidationVisitor = validate(new imports.QLBoolean(), new imports.QLMoney(), "&&")
            expect(astValidationVisitor.hasDetectedErrors()).to.equal(true);
            done();
        })
    });


    describe('Check expressions', () => {
        it('QLBoolean && QLBoolean || QLMoney < QLMoney -> OK', (done) => {
            let astValidationVisitor = validate(new imports.QLBoolean(), new imports.QLBoolean(), "&&", new imports.QLMoney(), new imports.QLMoney, "<", "||");
            expect(astValidationVisitor.hasDetectedErrors()).to.equal(false);
            done();
        })
    });


    describe('Check expressions', () => {
        it('QLBoolean && QLBoolean + QLNumber < QLNumber -> Detects Error', (done) => {
            let astValidationVisitor = validate(new imports.QLBoolean(), new imports.QLBoolean(), "&&", new imports.QLMoney(), new imports.QLMoney, "<", "+");
            expect(astValidationVisitor.hasDetectedErrors()).to.equal(true);
            done();
        })
    });


    function validate(left1, right1, operator1, left2, right2, operator2, operator){
        let astValidationVisitor = new imports.ASTValidationVisitor();
        let memoryState = new imports.MemoryState();

        let p1 = new imports.Property();
        p1.name = "p1";
        let p2 = new imports.Property();
        p2.name = "p2";
        let p3 = new imports.Property();
        p3.name = "p3";
        let p4 = new imports.Property();
        p4.name = "p4";
        memoryState.set(p1.name, left1);
        memoryState.set(p2.name, right1);
        memoryState.set(p3.name, left2);
        memoryState.set(p4.name, right2);

        let leftExpression = new imports.Expression();
        let rightExpression = new imports.Expression();


        let mainExpression = new imports.Expression();

        leftExpression.leftHand = p1;
        leftExpression.rightHand = p2;
        leftExpression.operator = operator1;

        rightExpression.leftHand = p3;
        rightExpression.rightHand = p4;
        rightExpression.operator = operator2;

        mainExpression.leftHand = leftExpression;
        mainExpression.rightHand = rightExpression;
        leftExpression.operator = operator;

        astValidationVisitor.memoryState = memoryState;
        astValidationVisitor.visitExpression(mainExpression);
    }


    function validate(propType1, propType2, operator) {
        var prop1 = {name: "p1", type: propType1};
        var prop2 = {name: "p2", type: propType2};

        var memoryState = new imports.MemoryState();
        memoryState.set(prop1.name, prop1.type);
        memoryState.set(prop2.name, prop2.type);
        var expression = getExpressionProperty(prop1, operator, prop2);

        let astValidationVisitor = new imports.ASTValidationVisitor();
        astValidationVisitor.memoryState = memoryState;
        astValidationVisitor.visitExpression(expression);
        return astValidationVisitor;
    }


    function getExpressionProperty(prop1, operator, prop2) {
        let p1 = new imports.Property();
        p1.name = prop1.name;
        let p2 = new imports.Property();
        p2.name = prop2.name;


        let expression = new imports.Expression();

        expression.leftHand = p1;
        expression.rightHand = p2;
        expression.operator = operator;


        return expression;
    }


});