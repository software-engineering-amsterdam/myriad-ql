/**
 * Created by Manuel on 24/04/2017.
 */

import Surface from 'famous/core/Surface.js';

export class RenderValueVisitor {

    renderMoney() {
        return new Surface();
    }

    renderString(){
        return new Surface();
    }

    renderBoolean(){
        return new Surface();
    }

    renderNumber(){
        return new Surface();
    }

    renderDate(){
        return new Surface();
    }
}