/**
 * Created by Manuel on 25/02/2017.
 */

import Surface          from 'famous/core/Surface.js';

import {Injection}      from 'arva-js/utils/Injection.js';
import {layout}         from 'arva-js/layout/Decorators.js';
import {inject, provide} from 'arva-js/utils/di/Decorators.js';
import {DataSource}     from 'arva-js/data/DataSource.js';
import {Controller}     from 'arva-js/core/Controller.js';
import {View}           from 'arva-js/core/View.js';

import {Program}        from './Program.js';
import {Question}       from '../statements/Question.js';
import {QuestionView}   from './views/QuestionView.js';

import {RenderVisitor}  from './RenderVisitor.js';

import './famous.css!';

export class GUI {

    program;
    ast;

    constructor(ast = {}) {
        this.ast = ast;
    }

    async createGUI(ast = this.ast) {
        this.application = new Program(ast.getProgram());
        await this.application.start();

        this.renderGUI.bind(this, this.application, this.ast)
    }

    renderGUI(program = {}, ast = {}) {

        let views = program.getViews();
        let view = views[0]; // Until we support QLS, just use the first view

        let visitor = new RenderVisitor();
        visitor.visitProgram(ast.getProgram(), view);

    }
}
