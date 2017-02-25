const jspmImport = require('./utils/JspmImport.js');


const Form = require('./Form.js');
const grammar = require('./grammar.js');
const Question = require('./statements/Question.js');
const Answer = require('./statements/Answer.js');
const TypeConverter = require('./utils/TypeConverter.js');
/**
 * To build the grammer: nearleyc grammar.ne -o qrammar.js
 * (Building the grammar is not yet supported due to us requiring the PostProcessor inside the grammar, need to find a solution)
 * @type {Parser}
 */

import nearly from 'nearley';
import {test1, test2} from './src/test/TestStrings.js';

export class CodeGenerator {

    constructor() {
       this._run();
    }

    _run() {
    }

    generate(form){
        let name = form.name;

        var html = "<html><div id='Form_"+name+"'>"+name+"</div>";

        if(form.statements != null){
            for(let statement of form.statements){
                let type = new TypeConverter().convertQLtoHTML(statement.type); //this line can be coded better
                html+= statement.getGeneratedCode(type);
            }
        }

        html += "</html>";
        return html;


    }
};






