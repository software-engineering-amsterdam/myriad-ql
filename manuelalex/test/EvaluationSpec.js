/**
 * Created by Manuel on 08/04/2017.
 */

import chai from 'chai';
import JspmImport       from './JspmImport.js';

const expect = chai.expect;

describe('Evaluation', () => {

    let imports = {};

    beforeEach(function () {
        return Promise.all([JspmImport('./src/gui/EvaluationVisitor.js'), JspmImport('./src/expressions/Expression.js'), JspmImport('./src/types/Property.js'), JspmImport('./src/memory/MemoryState.js'), JspmImport('./src/types/Types.js'), JspmImport('./src/types/Numbers.js')]).then(([{ EvaluationVisitor }, { Expression, PrefixExpression }, { Property }, { MemoryState }, { QLMoney, QLString, QLBoolean, QLNumber, QLDate }, {Numbers}]) => {
            imports.EvaluationVisitor = EvaluationVisitor;
            imports.Expression = Expression;
            imports.PrefixExpression = PrefixExpression;
            imports.Property = Property;
            imports.MemoryState = MemoryState;
            imports.QLMoney = QLMoney;
            imports.QLString = QLString;
            imports.QLBoolean = QLBoolean;
            imports.QLNumber = QLNumber;
            imports.QLDate = QLDate;
            imports.Numbers = Numbers;
        });
    });

    it('Evaluates true && true -> true', function () {

        let evaluationVisitor = new imports.EvaluationVisitor();
        let leftHandProperty = new imports.Property('leftHand');
        let rightHandProperty = new imports.Property('rightHand');
        let operator = '&&';

        let memoryState = new imports.MemoryState();
        let expression = new imports.Expression(leftHandProperty, operator, rightHandProperty);
        memoryState.set('leftHand', new imports.QLBoolean(), true);
        memoryState.set('rightHand', new imports.QLBoolean(), true);

        expect(expression.evaluate(evaluationVisitor, memoryState)).to.equal(true);
    });

    it('Evaluates true || false -> true', function () {

        let evaluationVisitor = new imports.EvaluationVisitor();
        let leftHandProperty = new imports.Property('leftHand');
        let rightHandProperty = new imports.Property('rightHand');
        let operator = '||';

        let memoryState = new imports.MemoryState();
        let expression = new imports.Expression(leftHandProperty, operator, rightHandProperty);
        memoryState.set('leftHand', new imports.QLBoolean(), true);
        memoryState.set('rightHand', new imports.QLBoolean(), false);

        expect(expression.evaluate(evaluationVisitor, memoryState)).to.equal(true);
    });

    it('Evaluates false || true -> false', function () {

        let evaluationVisitor = new imports.EvaluationVisitor();
        let leftHandProperty = new imports.Property('leftHand');
        let rightHandProperty = new imports.Property('rightHand');
        let operator = '||';

        let memoryState = new imports.MemoryState();
        let expression = new imports.Expression(leftHandProperty, operator, rightHandProperty);
        memoryState.set('leftHand', new imports.QLBoolean(), false);
        memoryState.set('rightHand', new imports.QLBoolean(), true);

        expect(expression.evaluate(evaluationVisitor, memoryState)).to.equal(true);
    });

    it('Evaluates 1 || false -> true', function () {

        let evaluationVisitor = new imports.EvaluationVisitor();
        let leftHandProperty = new imports.Numbers(1);
        let rightHandProperty = new imports.Property('rightHand');
        let operator = '||';

        let memoryState = new imports.MemoryState();
        let expression = new imports.Expression(leftHandProperty, operator, rightHandProperty);
        memoryState.set('rightHand', new imports.QLBoolean(), false);

        expect(expression.evaluate(evaluationVisitor, memoryState)).to.equal(true);
    });

    it('Evaluates 1 < 2 -> true', function () {

        let evaluationVisitor = new imports.EvaluationVisitor();
        let leftHandProperty = new imports.Numbers(1);
        let rightHandProperty = new imports.Numbers(2);
        let operator = '<';

        let memoryState = new imports.MemoryState();
        let expression = new imports.Expression(leftHandProperty, operator, rightHandProperty);

        expect(expression.evaluate(evaluationVisitor, memoryState)).to.equal(true);
    });

    it('Evaluates 10000 == 10000 -> true', function () {

        let evaluationVisitor = new imports.EvaluationVisitor();
        let leftHandProperty = new imports.Numbers(10000);
        let rightHandProperty = new imports.Numbers(10000);
        let operator = '==';

        let memoryState = new imports.MemoryState();
        let expression = new imports.Expression(leftHandProperty, operator, rightHandProperty);

        expect(expression.evaluate(evaluationVisitor, memoryState)).to.equal(true);
    });

    it('Evaluates -1 == -1 -> true', function () {

        let evaluationVisitor = new imports.EvaluationVisitor();
        let leftHandProperty = new imports.Numbers(-1);
        let rightHandProperty = new imports.Numbers(-1);
        let operator = '==';

        let memoryState = new imports.MemoryState();
        let expression = new imports.Expression(leftHandProperty, operator, rightHandProperty);

        expect(expression.evaluate(evaluationVisitor, memoryState)).to.equal(true);
    });

    it('Evaluates (true && true) && (true || false) -> true', function () {

        let leftHand1 = new imports.Property('leftHand1');
        let leftHand2 = new imports.Property('leftHand2');

        let rightHand1 = new imports.Property('rightHand1');
        let rightHand2 = new imports.Property('rightHand2');

        let memoryState = new imports.MemoryState();
        memoryState.set('leftHand1', new imports.QLBoolean, true);
        memoryState.set('leftHand2', new imports.QLBoolean, true);
        memoryState.set('rightHand1', new imports.QLBoolean, true);
        memoryState.set('rightHand2', new imports.QLBoolean, false);

        let leftExpression = new imports.Expression(leftHand1, '&&', rightHand1);
        let rightExpression = new imports.Expression(leftHand2, '||', rightHand2);
        let finalExpression = new imports.Expression(leftExpression, '&&', rightExpression);

        let evaluationVisitor = new imports.EvaluationVisitor();

        expect(finalExpression.evaluate(evaluationVisitor, memoryState)).to.equal(true);
    });

    it('Evaluates !(true && true) -> false', function () {

        let evaluationVisitor = new imports.EvaluationVisitor();
        let leftHandProperty = new imports.Property('leftHand');
        let rightHandProperty = new imports.Property('rightHand');
        let operator = '&&';

        let memoryState = new imports.MemoryState();
        let expression = new imports.Expression(leftHandProperty, operator, rightHandProperty);
        memoryState.set('leftHand', new imports.QLBoolean(), true);
        memoryState.set('rightHand', new imports.QLBoolean(), true);

        let prefixExpression = new imports.PrefixExpression('!', expression);
        expect(prefixExpression.evaluate(evaluationVisitor, memoryState)).to.equal(true);
    });

});