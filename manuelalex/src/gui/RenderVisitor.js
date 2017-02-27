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

    constructor(ast = {}, view = {}){
        let statements = ast.getStatements();
        let name = ast.getName();

        if(statements.length){
            this.visitStatements(statements, view);
        } else {
            this._showError('No statements provided in AST');
        }
    }

    visitStatements(statements = [], view = {}){
        for(let statement of statements){
            statement.render && statement.render(this, view);
        }
    }

    renderQuestion(question = {}, view = {}){
        let type = question.getPropertyType();
        let label = question.getLabel();
        let propertyName = question.getPropertyName();

        let typeRenderable = type.render(this);
        let labelRenderable = label.render(this);

        let subView = new View();
        subView.getSize = ()=> [undefined, 88];

        subView.addRenderable(labelRenderable, 'label', layout.dock.left(~120, 0, 10), layout.stick.center());
        subView.addRenderable(typeRenderable, 'type', layout.dock.right(~120, 0, 10));

        view.addRenderable(subView, `subView${this._viewCount++}`, layout.dock.top(44,0,10));

    }

    renderLabel(label){
        return new Surface({
            content: label.getValue()
        });
    }

    renderBooleanInput(type){
        return new Checkbox({
            state: false,
            enabled: true
        });
    }

    renderStringInput(type){
        return new SingleLineTextInput({});
    }

    renderDateInput(type){
        return new Surface({
            content: type.toString()
        });
    }

    renderNumberInput(type){
        return new Surface({
            content: type.toString()
        });
    }

    renderMoneyInput(type){
        return new Surface({
            content: type.toString()
        });
    }

    _showError(error){
        console.error(error.toString());
    }
}