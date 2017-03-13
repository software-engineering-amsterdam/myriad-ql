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
import {Router}         from 'arva-js/core/Router.js';

export class GUI {

    program;
    ast;

    constructor(ast = {}, memoryState = {}) {
        this.ast = ast;
        this.memoryState = memoryState;
    }

    async createGUI(ast = this.ast) {
        this.application = new Program(ast.getProgram());
        await this.application.start();

        this.renderGUI(this.application, this.ast, this.memoryState);
        this.memoryState.on('set', this.renderGUI.bind(this, this.application, this.ast, this.memoryState));
    }

    renderGUI(program = {}, ast = {}, memoryState = {}) {

        let view = program.createView();
        program.setViewForControllerMethod('QL', 'Index', view);

        let visitor = new RenderVisitor(memoryState);
        visitor.visitProgram(ast.getProgram(), view);

        let router = Injection.get(Router);
        router.go('QL', 'Index');
    }

    showValidationErrors(errors){
        document.body.innerHTML = `The following errors have occurred during validation:`;

        document.body.innerHTML = document.body.innerHTML + "<ul>";
        for (var errorStatement of errors) {
            document.body.innerHTML = document.body.innerHTML + "<li>" + errorStatement + "</li>";
        }
        document.body.innerHTML = document.body.innerHTML + "</ul>";

    }


    showParserErrors(parseString, errors){
        document.body.innerHTML = `The following errors have occurred during parsing:`;

        parseString = parseString.replace(/\n/g, "<br>");
        for(let error of errors){
            document.body.innerHTML = `${document.body.innerHTML} <br> <div ="error"> ${error}</div>`;
        }
        document.body.innerHTML = `${document.body.innerHTML} <br> <div>${parseString}</div>`;
    }
}
