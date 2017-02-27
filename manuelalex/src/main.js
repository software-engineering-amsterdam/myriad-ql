/**
 * Created by Manuel on 12/02/2017.
 */

import {Parser} from './Parser.js';
import {Visitor} from './Visitor.js';

let parser = new Parser();
let visitor = new Visitor();
visitor.visitAST(parser.AST);


// let form = new Parser().parse("something",4);
// console.log(form);
// let html = new CodeGenerator().generate(form);
//
// console.log(html);
//
// //TODO: Make function of this.
// fs = require('fs');
// fs.writeFile('qs.html', html, function (err) {
//     if (err) return console.log(err);
//     console.log(html +' > qs.html');
// });
//


