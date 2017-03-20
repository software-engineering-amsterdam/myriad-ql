/**
 * Created by Manuel on 27/02/2017.
 */

import Surface from 'famous/core/Surface.js';

import {View} from 'arva-js/core/View.js';
import {layout} from 'arva-js/layout/Decorators.js';
import {Checkbox} from 'arva-kit/components/Checkbox.js';
import {SingleLineTextInput} from 'arva-kit/input/SingleLineTextInput.js';

export class RenderVisitor {

    _viewCount = 0;
    memoryState;

    constructor(memoryState, evaluationVisitor) {
        this.memoryState = memoryState;
        this.evaluationVisitor = evaluationVisitor;
    }

    visitProgram(program, view) {
        program.renderTitle(this, view);
        this.visitStatements(program.getStatements(), view);
    }

    visitStatements(statements = [], view = {}) {
        for (const statement of statements) {
            statement.render && statement.render(this, view);
        }
    }

    renderProgamTitle(title = '', view) {
        const titleRenderable = new Surface({
            content: title
        });

        view.addRenderable(titleRenderable, 'programTitle', layout.dock.top(~20, 0, 10));
    }

    renderQuestion(question = {}, view = {}) {
        const type = question.getPropertyType();
        const label = question.getLabel();
        const propertyName = question.getPropertyName();
        const propertyElement = this.memoryState.getElement(propertyName.getName());

        const typeRenderable = type.render(this);
        const labelRenderable = label.render(this);

        typeRenderable.setState(propertyElement.getValue() || undefined);
        typeRenderable.on('state', ({ value }) => {
            propertyElement.setValue(value);
        });

        const subView = new View();
        subView.getSize = () => [undefined, 88];

        subView.addRenderable(labelRenderable, 'label', layout.dock.top(44, 0, 10), layout.stick.left());
        subView.addRenderable(typeRenderable, 'type', layout.dock.top(44, 0, 10), layout.stick.left());
        view.addRenderable(subView, `subView${this._viewCount++}`, layout.dock.top(88, 0, 10));

    }

    renderAnswer(answer = {}, view = {}) {

        const allocation = answer.getAllocation();
        const expression = allocation.getExpression();
        const type = allocation.getType();

        const evaluation = expression.evaluate(this.evaluationVisitor, this.memoryState);

        /* TODO: Add a better condition so that 0 values are not ignored */
        if (evaluation) {
            const label = answer.getLabel();
            const labelRenderable = label.render(this);
            const typeRenderable = type.renderValue(this);

            typeRenderable.setContent(evaluation);

            const subView = new View();
            subView.getSize = () => [undefined, 88];

            subView.addRenderable(labelRenderable, 'label', layout.dock.left(~120, 0, 10), layout.stick.center());
            subView.addRenderable(typeRenderable, 'type', layout.dock.right(~120, 0, 10));
            view.addRenderable(subView, `subView${this._viewCount++}`, layout.dock.top(44, 0, 10));
        }

    }

    renderLabel(label) {
        return new Surface({
            content: label.getValue()
        });
    }

    renderIfStatement(ifStatement, view) {
        const condition = ifStatement.getCondition();
        const ifBody = ifStatement.getIfBody();
        if (condition.evaluate(this.evaluationVisitor, this.memoryState)) {
            this.visitStatements(ifBody, view);
        }
    }

    renderIfElseStatement(ifElseStatement, view) {
        const condition = ifElseStatement.getCondition();
        const ifBody = ifElseStatement.getIfBody();
        const elseBody = ifElseStatement.getElseBody();

        if (condition.evaluate(this.evaluationVisitor, this.memoryState)) {
            this.visitStatements(ifBody, view);
        } else {
            this.visitStatements(elseBody, view);
        }
    }

    renderBooleanInput(qlBoolean) {
        const renderable = new Checkbox({
            state: false,
            enabled: true
        });

        renderable.on('unchecked', () => {
            renderable._eventOutput.emit('state', { value: false, type: qlBoolean });
        });
        renderable.on('checked', () => {
            renderable._eventOutput.emit('state', { value: true, type: qlBoolean });
        });
        renderable.setState = (state) => {
            state !== undefined && renderable.setChecked(state);
        };

        return renderable;
    }

    renderStringInput(qlString) {
        const renderable = new SingleLineTextInput({});
        renderable.on('message', (message) => {
            renderable._eventOutput.emit('state', { value: message, type: qlString });
        });
        renderable.setState = (state) => {
            state !== undefined && renderable.setValue(state);
        };
        return renderable;
    }

    renderDateInput(qlData) {
        const renderable = new SingleLineTextInput({
            inputOptions: { type: 'date' }
        });
        renderable.on('message', (message) => {
            renderable._eventOutput.emit('state', { value: message, type: qlData });
        });
        renderable.setState = (state) => {
            state !== undefined && renderable.setValue(state);
        };
        return renderable;
    }

    renderNumberInput(qlNumber) {
        const renderable = new SingleLineTextInput({
            inputOptions: { type: 'number' }
        });
        renderable.on('message', (message) => {
            renderable._eventOutput.emit('state', { value: message, type: qlNumber });
        });
        renderable.setState = (state) => {
            state !== undefined && renderable.setValue(state);
        };
        return renderable;
    }

    renderMoneyInput(qlMoney) {
        const renderable = new SingleLineTextInput({
            inputOptions: { type: 'number' }
        });
        renderable.on('message', (message) => {
            renderable._eventOutput.emit('state', { value: message, type: qlMoney });
        });
        renderable.setState = (state) => {
            state !== undefined && renderable.setValue(state);
        };
        return renderable;
    }

    renderMoneyValue() {
        return new Surface();
    }
}