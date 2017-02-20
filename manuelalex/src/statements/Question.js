/**
 * Created by Manuel on 13/02/2017.
 */

let Statement = require('./Statement.js');

module.exports = class Question extends Statement {
    constructor(options = {}) {
        super(options);

        this.name = options.name;
        this.propertyName = options.propertyName;
        this.propertyType = options.propertyType;

        this._location = options.location;
    }

    validate() {
        if(!this.name.length){
            this._throwError(`Name should be defined`);
        }
    }

    // todo probably remove this
    getGeneratedCode(type) {
        return "<div>" + this.name + "<input type='" + type + "' onchange='click" + this.propertyName + "()' id='" + this.propertyName + "'></div>";
    }
};