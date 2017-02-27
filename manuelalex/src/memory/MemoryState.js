/**
 * Created by alexvanmanen on 27-02-17.
 */

import {MemoryElement}   from './MemoryElement.js';



export class MemoryState {

    memory;
    constructor(options = {}) {
        this.memory = new Map();
    }

    set(elementName, elementType, elementValue) {
        let element = new MemoryElement();
        element.type = elementType;
        element.value =  elementValue;
        this.memory.set(elementName, element);
    }


    getType(elementName){
        let element = this.memory.get(elementName);
        return element.type;
    }

}