const jspmImport = require('./utils/JspmImport.js');


const Form = require('./Form.js');
const grammar = require('./grammar.js');
const Question = require('./statements/Question.js');
const Answer = require('./statements/Answer.js');


let nearley;
let test1, test2;

/**
 * To build the grammer: nearleyc grammar.ne -o qrammar.js
 * (Building the grammar is not yet supported due to us requiring the PostProcessor inside the grammar, need to find a solution)
 * @type {Parser}
 */
module.exports = class Parser {

    constructor() {
        Promise.all([
            jspmImport('nearley'),
            jspmImport('./src/test/TestStrings.js')
        ]).then((imports) => {
            [nearley, {test1, test2}] = imports;
        }).then(this._run.bind(this)).catch((error) => {
            // todo handle import errors correctly
            console.log(error);
        });
    }

    _run() {
    }

    generate(form){
        let name = form.name;


        // for (let statement of form.statements) {
        //     console.log(statement);
        // }
        var html = "<html><div id='Form_"+name+"'>"+name+"</div>";

        if(form.statements != null){
            for(let statement of form.statements){
                var type = "unknown";
                if(statement.type == "money"){
                    type = "number";
                }

                if(statement instanceof Answer){
                     //html += "<div>"+statement.name+"<input type='"+type+"' id='"+statement.propertyName+"'></div>";
                    html+= statement.getGeneratedCode(type);
                } else {
                     html += "<div>"+statement.name+"<input type='"+type+"' onchange='click"+statement.propertyName+"()' id='"+statement.propertyName+"'></div>";
                }

            }
        }

        html += "</html>";
        return html;


    }
};






