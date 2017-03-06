/**
 * Created by Manuel on 06/03/2017.
 */

import {Controller}         from 'arva-js/core/Controller.js';

export class QLController extends Controller {

    constructor() {
        super(...arguments);
        this.views = {};
    }

    getViews() {
        return this.views;
    }

    getView(method = ''){
        return this.views[method];
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
