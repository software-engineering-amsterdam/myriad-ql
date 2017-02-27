/**
 * Created by Manuel on 27/02/2017.
 */

import Surface          from 'famous/core/Surface.js';

import {App as ArvaApp} from 'arva-js/core/App.js';
import {layout}         from 'arva-js/layout/Decorators.js';
import {Injection}      from 'arva-js/utils/Injection.js';
import {DataSource}     from 'arva-js/data/DataSource.js';
import {Controller}     from 'arva-js/core/Controller.js';
import {View}           from 'arva-js/core/View.js';

export class Program {

    constructor(program = {}) {

        this.application = ArvaApp;
        window.cordova = {};

        this.application.defaultDataSource = (path = '/', options = {}) => {
            return new DataSource(path, options);
        };

        let firstController = HomeController;
        this.application.controllers = [firstController];

    }

    start(callBack = () => {}) {
        this.application.done = callBack;
        this.application.start();
    }

    getControllers() {
        return Injection.getAll(...this.application.controllers);
    }

    getViews() {
        let controllers = this.getControllers();
        return controllers.map((controller) => {
            return controller.getViews();
        });
    }

    setTitle(title = ''){
        let view = this.getViews()[0]; // for now just the first view;
        view.setTitle(title);
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

    setTitle(title = '') {
        if (!this.titleRenderable) {
            this.addRenderable(new Surface({
                content: title
            }), 'titleRenderable', layout.dock.top(44, 0, 10))
        } else {
            this.titleRenderable.setContent(title);
        }
    }

    getSize() {
        return [window.innerWidth, window.innerHeight];
    }
}