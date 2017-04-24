/**
 * Created by alexvanmanen on 06-02-17.
 */
import chai from 'chai';
// import Parser from '../src/Parser.js';
import JspmImport       from './JspmImport.js';
const expect = chai.expect;

describe('Test Types', () => {

    let imports = {};

    beforeEach(function () {
        return Promise.all([JspmImport('./src/ast/visitors/ASTValidationVisitor.js'), JspmImport('./src/Form.js'), JspmImport('./src/expressions/Expression.js'), JspmImport('./src/types/Property.js'), JspmImport('./src/memory/MemoryState.js'), JspmImport('./src/types/Types.js')]).then(([{ASTValidationVisitor}, {Form}, {Expression}, {Property}, {MemoryState}, {QLMoney, QLString, QLBoolean, QLNumber, QLDate}]) => {
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


    describe('test isValidOperator ', () => {
        it('QLBoolean operators', (done) => {
            let boolean = new imports.QLBoolean();
            expect(boolean.isValidOperator("&&")).to.equal(true);
            expect(boolean.isValidOperator("||")).to.equal(true);
            expect(boolean.isValidOperator("!=")).to.equal(true);
            expect(boolean.isValidOperator("==")).to.equal(true);
            expect(boolean.isValidOperator("+")).to.equal(false);
            expect(boolean.isValidOperator("-")).to.equal(false);
            expect(boolean.isValidOperator("=")).to.equal(false);
            done();
        });
    });


    describe('test isValidOperator ', () => {
        it('QLNumber operators', (done) => {
            let number = new imports.QLNumber();
            expect(number.isValidOperator("&&")).to.equal(false);
            expect(number.isValidOperator("||")).to.equal(false);
            expect(number.isValidOperator("!=")).to.equal(true);
            expect(number.isValidOperator("==")).to.equal(true);
            expect(number.isValidOperator("+")).to.equal(true);
            expect(number.isValidOperator("-")).to.equal(true);
            expect(number.isValidOperator("=")).to.equal(false);
            done();
        });
    });




});