/**
 * Created by Manuel on 20/02/2017.
 */
export class Allocation {

    constructor(propertyName = {}, type = null, expression = null, location) {
        this.propertyName = propertyName;
        this.type = type;
        this.expression = expression;
        this.location = location;
    }

    getPropertyName(){
        return this.propertyName;
    }

    getType(){
        return this.type;
    }

    getExpression(){
        return this.expression;
    }

    getLocation(){
        return this.location;
    }

    validate() {

    }
}