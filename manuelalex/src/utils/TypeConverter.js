/**
 * Created by alexvanmanen on 18-02-17.
 *
 */


module.exports = class TypeConverter {

    convertQLtoHTML(type){
        var result = type;
        if(type == "money"){
            result = "number";
        }
        return result;
    }
};