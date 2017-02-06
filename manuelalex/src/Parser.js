/**
 * Created by alexvanmanen on 06-02-17.
 */

import Form             from './Form.js';

export default class Parser {

    /**
     * TODO
     * @param text
     * @returns {*}
     */
    parse(text){
        return new Form(text);
    }
}