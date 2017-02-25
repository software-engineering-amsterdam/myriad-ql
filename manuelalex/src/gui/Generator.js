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

import {Question}       from '../statements/Question.js';
import {QuestionView}   from './views/QuestionView.js';

import './famous.css!';

export class Generator {

    application = null;
    views = {};
    propertyNames = {};

    constructor(ast = {}) {

        this.ast = ast;
        this.generateGUI(ast);
    }

    generateGUI(ast) {
        console.log('generateGUI');

        this.application = ArvaApp;
        window.cordova = {};
        this.application.defaultDataSource = (path = '/', options = {}) => {
            return new DataSource(path, options);
        };

        this.application.done = () => {
            this._addStatements(ast)
        };

        let firstController = HomeController;
        this.application.controllers = [firstController];

        this.startApplication();
    }

    _addStatements(ast) {
        let view = Injection.get(HomeController).getViews();
        window.x = this;
        for (let index in ast.program.statements) {
            let statement = ast.program.statements[index];
            if (statement instanceof Question) {
                let renderable = new QuestionView({
                    label: statement.name
                });
                this.propertyNames[statement.propertyName] = '';
                renderable.on('message', (message) => {
                    this.propertyNames[statement.propertyName] = message;
                });
                view.addRenderable(renderable, `Statement${index}`, layout.dock.top(~88, 10, 10));
            }
        }
    }

    startApplication() {
        this.application.start();
    }
}

class HomeController extends Controller {

    constructor() {
        super(...arguments);
        this.indexView = new HomeView();
    }

    Index() {
        return this.indexView;
    }

    getViews() {
        return this.indexView;
    }
}

class HomeView extends View {


    getSize() {
        return [window.innerWidth, window.innerHeight];
    }
}