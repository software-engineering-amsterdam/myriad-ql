/**  * Created by Manuel on 27/02/2017.  */

import find             from 'lodash/find';

import {App as ArvaApp} from 'arva-js/core/App.js';
import {Injection}      from 'arva-js/utils/Injection.js';
import {DataSource}     from 'arva-js/data/DataSource.js';
import {View}           from 'arva-js/core/View.js';
import {Router}         from 'arva-js/core/Router.js';

import {QLController}   from './controllers/QLController.js';

import './famous.css!';

export class Program {
    constructor() {

        /* BoilerPlate to get Arva working without an App.js */
        this.application = ArvaApp;
        window.cordova = {};
    }

    createView() {
        return new (class QLView extends View {});
    }

    async start() {
        this.application.defaultDataSource = (path = '/', options = {}) => new DataSource(path, options);
        this.application.controllers = [QLController];
        await this._initialize();
    }

    loaded() {
        const view = this.createView();
        const [controller] = this.getControllers();
        controller.createMethod('Index', view);
        this.setDefaultRoute('QL', 'Index');
    }

    setDefaultRoute(controller = 'QL', method = 'Index') {
        const router = Injection.get(Router);
        router.setDefault(controller, method);
    }

    getControllers() {
        return Injection.getAll(...this.application.controllers);
    }

    getController(controllerName){
        const controllers = this.getControllers();
        const currentController = find(controllers, (controller) => {
            let currentControllerName = Object.getPrototypeOf(controller).constructor.name;
            currentControllerName = currentControllerName.replace('Controller', '');
            return controllerName === currentControllerName;
        });
        return currentController;
    }

    getViews() {
        const controllers = this.getControllers();
        return controllers.map((controller) => controller.getViews());
    }

    getView(controllerName, method){
        const currentController = this.getController(controllerName);
        if(currentController){
            return currentController.getView(method);
        } else {
            throw new Error(`No controller found with controller name ${controllerName}`);
        }
    }

    setViewForControllerMethod(controllerName, method, view){
        const currentController = this.getController(controllerName);
        currentController.setViewForMethod(method, view);
    }

    _initialize() {
        return new Promise((resolve) => {
            this.application.done = () => resolve();
            this.application.loaded = this.loaded.bind(this);
            this.application.start();
        });
    }
}
