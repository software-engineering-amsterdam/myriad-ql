/**
 * Created by Manuel on 13/02/2017.
 */



module.exports = class PostProcessor {

    toString(data, location, reject){
        return data.join().split(",").join("");
    }

    toNull(){
        return null;
    }

    updateObject(obj, children) {
        if (typeof obj == "object") {
            var result = obj.constructor();
            for (var key in obj) {
                result[key] = this.updateObject(obj[key], children);
            }
            return result;
        } else if (typeof obj == "number") {
            return children[obj];
        } else {
            return obj;
        }
    }

};