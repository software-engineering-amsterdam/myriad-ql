/**
 * Created by Manuel on 27/02/2017.
 */

import Surface from 'famous/core/Surface.js';

import {View} from 'arva-js/core/View.js';
import {layout} from 'arva-js/layout/Decorators.js';
import {Checkbox} from 'arva-kit/components/Checkbox.js';
import {SingleLineTextInput} from 'arva-kit/input/SingleLineTextInput.js';

import {AbstractVisitor} from '../../AbstractVisitor.js';

export class RenderVisitor extends AbstractVisitor {

    _viewCount = 0;
    memoryState;

    constructor(memoryState, evaluationVisitor, renderValueVisitor, renderInputVisitor) {
        super();
        this.memoryState = memoryState;
        this.evaluationVisitor = evaluationVisitor;
        this.renderValueVisitor = renderValueVisitor;
        this.renderInputVisitor = renderInputVisitor;
    }

    visitProgram(program, view) {
        program.accept(this, view);
        this.visitStatements(program.getStatements(), view);
    }

    visitStatements(statements = [], view = {}) {
        for (const statement of statements) {
            statement.accept && statement.accept(this, view);
        }
    }

    visitProgramTitle(title, view) {
        const titleRenderable = new Surface({
            content: title
        });

        view.addRenderable(titleRenderable, 'programTitle', layout.dock.top(~20, 0, 10));
    }

    visitQuestion(question = {}, view = {}) {
        const type = new (question.getPropertyType());
        const label = question.getLabel();
        const property = question.getProperty();
        const propertyElement = this.memoryState.getElement(property.getName());

        const typeRenderable = type.accept(this.renderInputVisitor);
        const labelRenderable = label.accept(this);

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

    visitAnswer(answer = {}, view = {}) {
        const allocation = answer.getAllocation();
        const expression = allocation.getExpression();
        const type = new (allocation.getType());

        const evaluation = expression.accept(this.evaluationVisitor, this.memoryState) || undefined;

        if (evaluation !== undefined && evaluation !== null) {
            const label = answer.getLabel();
            const labelRenderable = label.accept(this);
            const typeRenderable = type.accept(this.renderValueVisitor);

            typeRenderable.setContent(evaluation);

            const subView = new View();
            subView.getSize = () => [undefined, 88];

            subView.addRenderable(labelRenderable, 'label', layout.dock.left(~120, 0, 10), layout.stick.center());
            subView.addRenderable(typeRenderable, 'type', layout.dock.right(~120, 0, 10));
            view.addRenderable(subView, `subView${this._viewCount++}`, layout.dock.top(44, 0, 10));
        }

    }

    visitLabel(label) {
        return new Surface({
            content: label.getValue()
        });
    }

    visitIfStatement(ifStatement, view) {
        const condition = ifStatement.getCondition();
        const ifBody = ifStatement.getIfBody();

        if (condition.accept(this.evaluationVisitor, this.memoryState)) {
            this.visitStatements(ifBody, view);
        }
    }

    visitIfElseStatement(ifElseStatement, view) {
        const condition = ifElseStatement.getCondition();
        const ifBody = ifElseStatement.getIfBody();
        const elseBody = ifElseStatement.getElseBody();

        if (condition.accept(this.evaluationVisitor, this.memoryState)) {
            this.visitStatements(ifBody, view);
        } else {
            this.visitStatements(elseBody, view);
        }
    }

}