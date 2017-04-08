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
        return Promise.all([JspmImport('./src/ASTValidationVisitor.js'), JspmImport('./src/Form.js'), JspmImport('./src/expressions/Expression.js'), JspmImport('./src/types/Property.js'), JspmImport('./src/memory/MemoryState.js'), JspmImport('./src/types/Types.js')]).then(([{ASTValidationVisitor}, {Form}, {Expression}, {Property}, {MemoryState}, {QLMoney, QLString, QLBoolean, QLNumber, QLDate}]) => {
            imports.ASTValidationVisitor = ASTValidationVisitor;
            imports.Form = Form;
            imports.Expression = Expression;
            imports.Property = Property;
            imports.MemoryState = MemoryState;
            imports.QLMoney = QLMoney;
            imports.QLString = QLString;
            imports.QLBoolean = QLBoolean;
            imports.QLNumber = QLNumber;
            imports.QLDate = QLDate;


        });
    });

    // describe('General tests', () => {
    //     it('returns valid', (done) => {
    //
    //         let astValidationVisitor = new imports.ASTValidationVisitor();
    //         let form = new imports.Form();
    //         astValidationVisitor.visitForm(form);
    //         done();
    //
    //         // let form = new Form();
    //         // //let ast = new AST();
    //         // //ast.program = '{program: '+ form +' }';dd
    //         //
    //         //d
    //         // form.name = "taxOfficeExample";
    //         // //form.statements =
    //         // let ql = new QLMoney();
    //         // let question = new Question();
    //
    //         //let jsonAST = '{"program":{"name":"taxOfficeExample","statements":[{"location":26,"label":{"value":"Did you sell a house in 2010?"},"propertyName":{"name":"hasSoldHouse","location":70},"propertyType":{"location":84}},{"location":95,"label":{"value":"Did you buy a house in 2010?"},"propertyName":{"name":"hasBoughtHouse","location":138},"propertyType":{"location":154}},{"location":163,"label":{"value":"Did you enter a loan?"},"propertyName":{"name":"hasMaintLoan","location":199},"propertyType":{"location":213}},{"location":223,"condition":{"leftHand":{"leftHand":{"name":"hasSoldHouse","location":228},"operator":"&&","rightHand":{"name":"hasSoldHouse","location":244},"location":228},"operator":"||","rightHand":{"leftHand":{"name":"hasBoughtHouse","location":262},"operator":"<","rightHand":{"name":"hasBoughtHouse","location":279},"location":262},"location":227},"ifBody":[{"location":305,"label":{"value":"What was the selling price?"},"propertyName":{"name":"sellingPrice","location":351},"propertyType":{"location":365}},{"location":378,"label":{"value":"What was the private debts for the sold house?"},"propertyName":{"name":"privateDebt","location":443},"propertyType":{"location":456}},{"location":468,"label":{"value":"Value residue:"},"allocation":{"propertyName":{"name":"valueResidue","location":499},"type":{"location":513},"expression":{"leftHand":{"name":"hasSoldHouse","location":522},"operator":"-","rightHand":{"name":"hasMaintLoan","location":535},"location":522},"location":499}}]}],"location":0}}';
    //         //let ast = JSON.parse(form);
    //
    //         //astValidationVisitor.visitForm(form);
    //         // done();
    //
    //     })
    // });


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
        it('QLBoolean + QLMoney -> Detects Error', (done) => {
            let astValidationVisitor = validate(new imports.QLBoolean(), new imports.QLMoney(), "+")
            expect(astValidationVisitor.hasDetectedErrors()).to.equal(true);
            done();
        })
    });

    describe('Check expressions', () => {
        it('QLBoolean - QLMoney -> Detects Error', (done) => {
            let astValidationVisitor = validate(new imports.QLBoolean(), new imports.QLMoney(), "-")
            expect(astValidationVisitor.hasDetectedErrors()).to.equal(true);
            done();
        })
    });


    describe('Check expressions', () => {
        it('QLMoney - QLMoney -> OK', (done) => {
            let astValidationVisitor = validate(new imports.QLMoney(), new imports.QLMoney(), "-")
            expect(astValidationVisitor.hasDetectedErrors()).to.equal(false);
            done();
        })
    });

    describe('Check expressions', () => {
        it('QLBoolean && QLBoolean || QLMoney < QLMoney -> OK', (done) => {
            let astValidationVisitor = validateExpression(new imports.QLBoolean(), new imports.QLBoolean(), "&&", new imports.QLMoney(), new imports.QLMoney, "<", "||");
            expect(astValidationVisitor.hasDetectedErrors()).to.equal(false);
            done();
        })
    });


    describe('Check expressions', () => {
        it('QLBoolean && QLBoolean * QLNumber < QLNumber -> Detects Error', (done) => {
            let b1 = new imports.QLBoolean();
            let b2 = new imports.QLBoolean();
            let m1 = new imports.QLMoney();
            let m2 = new imports.QLMoney;
            let astValidationVisitor = validateExpression(b1, b2, "&&", m1, m2, "<", "*");
            expect(astValidationVisitor.hasDetectedErrors()).to.equal(true);
            done();
        })
    });


    function validateExpression(propertyTypeLeft1, propertyTypeRight1, operator1, propertyTypeLeft2, propertyTypeRight2, operator2, operator) {
        let astValidationVisitor = new imports.ASTValidationVisitor();
        let memoryState = new imports.MemoryState();

        let property1 = new imports.Property("p1");
        let property2 = new imports.Property("p2");
        let property3 = new imports.Property("p3");
        let property4 = new imports.Property("p4");

        memoryState.set(property1.name, propertyTypeLeft1);
        memoryState.set(property2.name, propertyTypeRight1);
        memoryState.set(property3.name, propertyTypeLeft2);
        memoryState.set(property4.name, propertyTypeRight2);

        let leftExpression = new imports.Expression(property1, operator1, property2);
        let rightExpression = new imports.Expression(property3, operator2, property4);
        let mainExpression = new imports.Expression(leftExpression, operator, rightExpression);

        astValidationVisitor.memoryState = memoryState;
        astValidationVisitor.visitExpression(mainExpression);
        return astValidationVisitor;
    }


    function validate(propertyTypeLeft, propertyTypeRight, operator) {
        let property1 = new imports.Property("p1");
        let property2 = new imports.Property("p2");

        let memoryState = new imports.MemoryState();
        memoryState.set(property1.name, propertyTypeLeft);
        memoryState.set(property2.name, propertyTypeRight);

        let expression = new imports.Expression(property1, operator, property2);

        let astValidationVisitor = new imports.ASTValidationVisitor();
        astValidationVisitor.memoryState = memoryState;
        astValidationVisitor.visitExpression(expression);
        return astValidationVisitor;
    }
});