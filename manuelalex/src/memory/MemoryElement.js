/**
 * Created by alexvanmanen on 27-02-17.
 */

import eventemitter3   from 'eventemitter3';

export class MemoryElement extends eventemitter3{

    constructor(type, value) {
        super();

        this.type = type;
        this.value = value;
    }

}