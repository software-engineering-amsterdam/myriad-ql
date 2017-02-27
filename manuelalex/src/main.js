/**
 * Created by Manuel on 12/02/2017.
 */

import {Parser} from './Parser.js';
import {Visitor} from './Visitor.js';

import {Gui}                                     from './gui/Gui.js';
import {AST}                                     from './ast/AST.js';

import {test1, test2, test3, test4 ,test5}       from './test/TestStrings.js';

let parser = new Parser();
let result = parser.parse(test1);

let ast = new AST(result[0]);
let visitor = new Visitor();
visitor.visitAST(ast);
let gui = new Gui(ast);
