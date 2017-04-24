/**
 * Created by Manuel on 24/04/2017.
 */

import Surface from 'famous/core/Surface.js';

export class RenderValueVisitor {

    visitMoney() {
        return new Surface();
    }

    visitString(){
        return new Surface();
    }

    visitBoolean(){
        return new Surface();
    }

    visitNumber(){
        return new Surface();
    }

    visitDate(){
        return new Surface();
    }

    visitReservedBooleanWords(){
        return new Surface();
    }


}