/**
 * Created by alexvanmanen on 27-02-17.
 */

import eventemitter3   from 'eventemitter3';

export class MemoryElement extends eventemitter3 {

    constructor(type, value) {
        super();

        this.type = type;
        this.value = value;
    }

    getValue(){
        return this.value;
    }

    getType(){
        return this.type;
    }

    setType(type) {
        this.type = type;
    }

    setValue(value) {
        this.value = value;

        this._onSetterTriggered(this.value);
    }

    _onSetterTriggered(value) {
        this.emit('set', value);
    }

}