/**
 * Created by alexvanmanen on 06-02-17.
 */

import Form from './Form.js';

export default class Parser {

    /**
     * TODO
     * @param text
     * @returns {*}
     */
    parse(text){
        return new Form(text);
    }


    lexer(text){
        let t = "int a = a + b;";
            for(token in t.split(" ")){
            console.log(token);
        }
    }
}

//Parser();
//p.lexer("ddd");