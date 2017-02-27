/**
 * Created by Manuel on 25/02/2017.
 */

import Surface          from 'famous/core/Surface.js';

import {Injection}      from 'arva-js/utils/Injection.js';
import {layout}         from 'arva-js/layout/Decorators.js';
import {App as ArvaApp} from 'arva-js/core/App.js';
import {DataSource}     from 'arva-js/data/DataSource.js';
import {Controller}     from 'arva-js/core/Controller.js';
import {View}           from 'arva-js/core/View.js';

import {Program}        from './Program.js';
import {Question}       from '../statements/Question.js';
import {QuestionView}   from './views/QuestionView.js';

import {RenderVisitor}  from './RenderVisitor.js';

import './famous.css!';

export class Gui {

    program = null;
    propertyNames = {};

    constructor(ast = {}) {

        this.ast = ast;


        this.createGUI(ast);


    }

    createGUI(ast = {}) {
        this.program = new Program(ast.program);
        this.program.start(this._addStatements.bind(this, this.program, this.ast));
    }

    _addStatements(program = {}, ast = {}) {
        this.program.setTitle(ast.program.name);

        let views = program.getViews();
        let view = views[0]; // for now, just use the first view

        let visitor = new RenderVisitor(ast, view);

        return;
        for (let index in ast.program.statements) {
            let statement = ast.program.statements[index];

            if (statement instanceof Question) {
                let renderable = new QuestionView({
                    label: statement.name,
                    type: statement.propertyType
                });
                this.propertyNames[statement.propertyName] = '';

                renderable.on('message', (message) => {
                    this.propertyNames[statement.propertyName] = message;
                });
                view.addRenderable(renderable, `Statement${index}`, layout.dock.top(~88, 10, 10));
            }
        }
    }
}
