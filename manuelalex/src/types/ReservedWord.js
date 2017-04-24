/**
 * Created by Manuel on 13/03/2017.
 */

import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from './Types.js';

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

    accept(visitor) {
        return visitor.visitReservedWords(this);
    }

    toString() {
        return this.getName();
    }
}

export class ReservedBooleanWord extends ReservedWords {

    getValue(){
        return this.value;
    }

    getType(){
        return new QLBoolean();
    }

}