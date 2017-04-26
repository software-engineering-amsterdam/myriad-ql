/**
 * Created by Manuel on 13/03/2017.
 */

import {QLBoolean} from './Types.js';

export class ReservedWord {

    constructor(value, location) {
        this.value = value;
        this.location = location;
    }

    getValue() {
        throw new Error('getValue method should have been overwritten');
    }

    getType() {
        throw new Error('getValue method should have been overwritten');
    }

    getLocation() {
        return this.location;
    }

    accept() {
        throw new Error('Accept method should have been overwritten');
    }

    toString() {
        return this.getValue().toString();
    }
}

export class ReservedBooleanWord extends ReservedWord {

    getValue(){
        return this.value;
    }

    getType(){
        return QLBoolean;
    }

    accept(visitor, ...params){
        return visitor.visitReservedBooleanWord(this, ...params);
    }

    isBooleanType(){
        return true;
    };

}