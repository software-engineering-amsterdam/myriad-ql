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

        //TODO: Refactor. this glues our memory to our gui. Now we need to change the memory if we change the GUI.
        this._onSetterTriggered(elementName, {type: elementType, value: elementValue});
        element.on('set', ()=> this._onSetterTriggered(elementName, {type: element.getType(), value: element.getValue()}));

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
        let element = this.memory.get(elementName);

        // todo This should be removed when the ASTValidator creates a valid MemoryState
        if(!element){
            return this.set(elementName, 'todoType', '');
        }

        return element;

    }

    _onSetterTriggered(elementName = '', values = {}){
        this.emit('set', {elementName, ...values});
    }

}