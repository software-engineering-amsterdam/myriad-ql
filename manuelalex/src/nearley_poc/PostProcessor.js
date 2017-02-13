/**
 * Created by Manuel on 13/02/2017.
 */

let Question = require('./Question.js');

module.exports = class PostProcessor {

    static question(data, location, reject){
        return new Question({name: data[0], propertyName: data[3][0], type: data[6][0]});
    }

    static toString(data, location, reject){
      return data.join().split(",").join("");
    }

    static toNull(){
        return null;
    }

};