/**
 * Created by Manuel on 20/02/2017.
 */

export class Money {

    constructor(location = null) {
        this.location = location;
    }

    getLocation(){
        return this.location;
    }
}