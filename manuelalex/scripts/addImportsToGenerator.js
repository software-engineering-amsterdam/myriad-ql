/**
 * Created by Manuel on 28/02/2017.
 */


let fs = require('fs');

const imports = `import {FormPostProcessor as FP} from './processors/FormPostProcessor.js'; var FormPostProcessor = new FP();\n`;
const fileString = './src/grammar.js';

fs.readFile(fileString, 'utf8', function (err,data) {
    if (err) {
        return console.error(err);
    }
    data = imports + data;
    fs.writeFile(fileString, data, function(err) {
        if(err) {
            return console.log(err);
        }

        console.log("Imports have been successfully appended");
    });
});