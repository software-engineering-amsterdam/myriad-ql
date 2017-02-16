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

        const parser = new nearley.Parser(grammar.ParserRules, grammar.ParserStart);
        let result;
        // Create a Parser object from our grammar.
        try{
            result = parser.feed(test1).results;
            console.log("Shit van Alex:")
            console.log(result);


            console.log("Eind van de shit van Alex")
        } catch (parseError){
            console.log(`Error at character ${parseError.offset}`);
        } finally{
            console.log(`Result: ${JSON.stringify(result)}`);
        }
    }

    parse(input, test=2){
        if(test==3){
            let q1 = new Question();
            q1.name = "Q1";
            q1.propertyName = "sellingPrice";
            q1.type = "money";
            let f = new Form([q1]);
            f.name="taxOfficeExample";
            return f;

        } else if (test==4){
            let q1 = new Question();
            q1.name = "Q1";
            q1.propertyName = "sellingPrice";
            q1.type = "money";
            let q2 = new Question();
            q2.name = "Q2";
            q2.propertyName = "privateDebt";
            q2.type = "money";

            let a2 = new Answer();
            a2.type= "money";
            a2.propertyName = "A2";
            a2.expression = "(Q1 - Q2)";

            let f = new Form([q1,q2,a2]);
            f.name="taxOfficeExample";
            return f;

        } else {
            let f = new Form();
            f.name="y";
            return f;
        }



    }
};






