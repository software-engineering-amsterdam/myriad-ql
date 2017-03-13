/**  * Created by Manuel on 27/02/2017.  */

import Surface          from 'famous/core/Surface.js';
import find             from 'lodash/find';

import {App as ArvaApp} from 'arva-js/core/App.js';
import {layout}         from 'arva-js/layout/Decorators.js';
import {Injection}      from 'arva-js/utils/Injection.js';
import {DataSource}     from 'arva-js/data/DataSource.js';
import {Controller}     from 'arva-js/core/Controller.js';
import {View}           from 'arva-js/core/View.js';
import {Router}         from 'arva-js/core/Router.js';

import {QLController}   from './controllers/QLController.js';

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
        this.application.defaultDataSource = (path = '/', options = {}) => {
            return new DataSource(path, options);
        };

        this.application.controllers = [QLController];
        await this._initialize();
    }

    loaded() {
        let view = this.createView();
        let controller = this.getControllers()[0];
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
        let controllers = this.getControllers();
        let currentController = find(controllers, (controller)=>{
            let currentControllerName = Object.getPrototypeOf(controller).constructor.name;
            currentControllerName = currentControllerName.replace('Controller', '');
            return controllerName === currentControllerName;
        });

        return currentController;
    }

    getViews() {
        let controllers = this.getControllers();
        return controllers.map((controller) => {
            return controller.getViews();
        });
    }

    getView(controllerName, method){
        let currentController = this.getController(controllerName);
        if(currentController){
            return currentController.getView(method);
        } else {
            throw new Error(`No controller found with controller name ${controllerName}`);
        }
    }

    setViewForControllerMethod(controllerName, method, view){
        let currentController = this.getController(controllerName);
        currentController.setViewForMethod(method, view);
    }

    _initialize() {
        return new Promise((resolve, reject) => {
            this.application.done = () => resolve();
            this.application.loaded = this.loaded.bind(this);
            this.application.start();
        });
    }
}
