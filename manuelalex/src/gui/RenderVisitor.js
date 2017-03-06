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

    constructor(memoryState){
        this.memoryState = memoryState;
    }

    async visitProgram(program, view){
        view = this._addMarginsToView(view);

        program.renderTitle(this, view);

       this.visitStatements(program.getStatements(), view);
    }

    visitStatements(statements = [], view = {}){
        for(let statement of statements){
            statement.render && statement.render(this, view);
        }
    }

    renderProgamTitle(title = '', view){
        let titleRenderable = new Surface({
            content: title
        });

        view.addRenderable(titleRenderable, 'programTitle', layout.dock.top(~20, 0, 10))
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

    renderBooleanInput(qlBoolean){
        let element = this.memoryState.getElement(qlBoolean);
        let renderable = new Checkbox({
            state: element.value || false,
            enabled: true
        });
        renderable.on('unchecked', ()=>{
            element.setValue(false);
        });

        renderable.on('checked', ()=>{
            element.setValue(true)
        });
        return renderable;
    }

    renderStringInput(qlString){
        let element = this.memoryState.getElement(qlString);
        let renderable = new SingleLineTextInput({});
        renderable.on('message', (message)=>{
            element.setValue(message);
        });
        return renderable;
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

    // todo
    _addMarginsToView(view){
        return view;
    }

    _showError(error){
        console.error(error.toString());
    }
}