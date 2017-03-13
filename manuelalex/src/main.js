/**
 * Created by Manuel on 12/02/2017.
 */

import {Parser} from './Parser.js';
import {ASTValidationVisitor} from './ASTValidationVisitor.js';

import {GUI}                                     from './gui/Gui.js';
import {AST}                                     from './ast/AST.js';

import {test1, test2, test3, test4, test5, test6}       from './test/TestStrings.js';

let parser = new Parser();
let {result, errors} = parser.parse(test6);

if (errors) {
    let gui = new GUI(null, null);
    gui.showParserErrors(parser.getParseString(), parser.getErrorMessage());
    throw new Error("Parser error: " + parser.error);
}

let ast = new AST(result[0]);
let visitor = new ASTValidationVisitor();
visitor.visitAST(ast);


if (visitor.hasDetectedErrors()) {
    let gui = new GUI(null, null);
    gui.showValidationErrors(visitor.errors);
} else {
    /* Visitor has validated the AST */
    let memoryState = visitor.getMemoryState();
    let gui = new GUI(ast, memoryState);
    gui.createGUI();
}