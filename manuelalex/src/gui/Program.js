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
import {Router}         from 'arva-js/core/Router.js';

export class Program {
    constructor() {

        /* BoilerPlate to get Arva working without an App.js */
        this.application = ArvaApp;
        window.cordova = {};

        this.application.defaultDataSource = (path = '/', options = {}) => {
            return new DataSource(path, options);
        };

        let controller = this.createController();
        this.application.controllers = [controller];

    }

    createController() {
        let qlController = QLController;
        return qlController;
    }

    createView() {
        return new (class QLView extends View {});
    }

    start() {
        return new Promise((resolve, reject) => {

            let view = this.createView();
            let controller = this.getControllers()[0];

            controller.createMethod('Index', view);
            this.setDefaultRoute('QL', 'Index');

            this.application.start();
            this.application.done = () => resolve;
        });
    }

    setDefaultRoute(controller = 'QL', method = 'Index') {
        const router = Injection.get(Router);
        router.setDefault(controller, method);
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
}


class QLController extends Controller {

    constructor() {
        super(...arguments);
        this.views = {};
    }

    getViews() {
        return this.views;
    }

    getViewForMethod(method = '') {
        return this.views[method];
    }

    createMethod(methodName = '', view) {

        this[methodName] = () => {
            return this.views[methodName];
        };

        if (view) {
            this.setViewForMethod(methodName, view);
        }
    }

    setViewForMethod(methodName = '', view = {}) {
        this.views[methodName] = view;
    }

}