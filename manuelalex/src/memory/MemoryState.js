/**
 * Created by alexvanmanen on 27-02-17.
 */

import eventemitter3     from 'eventemitter3';
import {MemoryElement}   from './MemoryElement.js';

export class MemoryState extends eventemitter3 {
    memory;

    constructor() {
        super();
        this.memory = new Map();
    }

    set(elementName, elementType, elementValue) {
        let element = new MemoryElement(elementType, elementValue);
        this.memory.set(elementName, element);

        this._onStateChange(element, elementName);
        this._onSetterTriggered(elementName, {type: elementType, value: elementValue});
        return element;
    }

    getType(elementName){
        let element = this.memory.get(elementName);
        return element ? element.type : undefined;
    }

    getValue(elementName){
        let element = this.memory.get(elementName);
        return element ? element.value : undefined;
    }

    getElement(elementName){
        return this.memory.get(elementName);
    }

    _onStateChange(element, elementName){
        element.on('set', ()=> this._onSetterTriggered(elementName, {type: element.getType(), value: element.getValue()}));
    }

    _onSetterTriggered(elementName = '', values = {}){
        this.emit('set', {elementName, ...values});
    }

}