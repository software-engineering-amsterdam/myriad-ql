/**
 * Created by Manuel on 13/02/2017.
 */
module.exports = class Question {
    constructor(options = {}){
        this.name = options.name;
        this.propertyName = options.propertyName;
        this.type = options.type;
    }
};