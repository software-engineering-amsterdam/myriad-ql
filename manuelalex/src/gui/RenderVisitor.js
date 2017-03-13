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

    constructor(memoryState) {
        this.memoryState = memoryState;
    }

    async visitProgram(program, view) {
        view = this._addMarginsToView(view);

        program.renderTitle(this, view);

        this.visitStatements(program.getStatements(), view);
    }

    visitStatements(statements = [], view = {}) {
        for (let statement of statements) {
            statement.render && statement.render(this, view);
        }
    }

    renderProgamTitle(title = '', view) {
        let titleRenderable = new Surface({
            content: title
        });

        view.addRenderable(titleRenderable, 'programTitle', layout.dock.top(~20, 0, 10))
    }

    renderQuestion(question = {}, view = {}) {
        let type = question.getPropertyType();
        let label = question.getLabel();
        let propertyName = question.getPropertyName();
        let propertyElement = this.memoryState.getElement(propertyName.getName());

        let typeRenderable = type.render(this);
        let labelRenderable = label.render(this);

        typeRenderable.setState(propertyElement.getValue() || undefined);
        typeRenderable.on('state', ({ value }) => {
            propertyElement.setValue(value);
        });

        let subView = new View();
        subView.getSize = () => [undefined, 88];

        subView.addRenderable(labelRenderable, 'label', layout.dock.top(44, 0, 10), layout.stick.left());
        subView.addRenderable(typeRenderable, 'type', layout.dock.top(44, 0, 10), layout.stick.left());
        view.addRenderable(subView, `subView${this._viewCount++}`, layout.dock.top(88, 0, 10));

    }

    renderAnswer(answer = {}, view = {}) {

        let allocation = answer.getAllocation();
        let expression = allocation.getExpression();
        let type = allocation.getType();

        let evaluation = expression.evaluate(this.memoryState);

        /* Add a better condition so that 0 values are not ignored */
        if (evaluation) {
            let label = answer.getLabel();
            let labelRenderable = label.render(this);
            let typeRenderable = type.renderValue(this);

            typeRenderable.setContent(evaluation);

            let subView = new View();
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
        let condition = ifStatement.getCondition();
        let ifBody = ifStatement.getIfBody();
        if (condition.evaluate(this.memoryState)) {
            this.visitStatements(ifBody, view);
        }
    }

    renderIfElseStatement(ifElseStatement, view) {
        let condition = ifElseStatement.getCondition();
        let ifBody = ifElseStatement.getIfBody();
        let elseBody = ifElseStatement.getElseBody();

        if (condition.evaluate(this.memoryState)) {
            this.visitStatements(ifBody, view);
        } else {
            this.visitStatements(elseBody, view)
        }
    }

    renderBooleanInput(qlBoolean) {
        let renderable = new Checkbox({
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
        let renderable = new SingleLineTextInput({});
        renderable.on('message', (message) => {
            renderable._eventOutput.emit('state', { value: message, type: qlString });
        });
        renderable.setState = (state) => {
            state !== undefined && renderable.setValue(state);
        };
        return renderable;
    }

    renderDateInput(qlData) {
        let renderable = new SingleLineTextInput({
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
        let renderable = new SingleLineTextInput({
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
        let renderable = new SingleLineTextInput({
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

    renderMoneyValue(qlMoney) {
        return new Surface();
    }

    _addMarginsToView(view) {
        return view;
    }

    _showError(error) {
        console.error(error.toString());
    }
}