/**
 * Created by Manuel on 13/02/2017.
 */



module.exports = class PostProcessor {

    static toString(data, location, reject){
        return data.join().split(",").join("");
    }

    static toNull(){
        return null;
    }

};