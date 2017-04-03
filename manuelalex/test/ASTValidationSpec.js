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

    describe('General tests', ()=>{
       it('returns valid', (done)=>{
           Promise.all([JspmImport('./src/ASTValidationVisitor.js'),JspmImport('./src/Form.js')]).then(([{ASTValidationVisitor}, {Form}])=>{
               console.log(ASTValidationVisitor);
               let astValidationVisitor = new ASTValidationVisitor();
               let form = new Form();
               astValidationVisitor.visitForm(form);
               done();
           });
           // JspmImport('./src/ASTValidationVisitor.js').then(({ASTValidationVisitor})=>{

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
           // });
       })
    });


    describe('Check expressions', ()=>{
        it('QLBoolean && QLBoolean', (done)=>{
            Promise.all([JspmImport('./src/ASTValidationVisitor.js'),JspmImport('./src/expressions/Expression.js'), JspmImport('./src/types/Property.js'),JspmImport('./src/memory/MemoryState.js'),JspmImport('./src/types/Types.js')]).then(([{ASTValidationVisitor}, {Expression}, {Property}, {MemoryState}, {QLBoolean, QLMoney}])=>{
                console.log(ASTValidationVisitor);
                let astValidationVisitor = new ASTValidationVisitor();
                let expression = new Expression();


                let p1 = new Property();
                p1.name = "p1";
                let p2 = new Property();
                p2.name = "p2";
                expression.leftHand = p1;
                expression.rightHand = p2;
                expression.operator = "&&";
                let memoryState = new MemoryState();
                memoryState.set(p1.name, new QLBoolean());
                memoryState.set(p2.name, new QLBoolean());
                astValidationVisitor.memoryState = memoryState;

                astValidationVisitor.visitExpression(expression);

                expect(astValidationVisitor.hasDetectedErrors()).to.equal(false);
                done();
            });
        })
    });


});