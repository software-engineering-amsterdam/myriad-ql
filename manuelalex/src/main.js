/**
 * Created by Manuel on 12/02/2017.
 */

import {Parser} from './Parser.js';
import {ASTValidationVisitor} from './ASTValidationVisitor.js';
import {ASTDependencyVisitor} from './ASTDependencyVisitor.js';

import {GUI} from './gui/Gui.js';
import {AST} from './ast/AST.js';

import {test1, test2, test3,
        test4, test5, test6,
        test7, test8} from './test/TestStrings.js';

let parser = new Parser();
let { result, errors, parseString } = parser.parse(test7);

if (errors.length) {
    let gui = new GUI(null, null);
    gui.showParserErrors(parseString, errors);
    throw new Error("Parser error: " + parser.error);
}

let ast = new AST(result[0]);
let dependencyVisitor = new ASTDependencyVisitor();
dependencyVisitor.visitAST(ast);

// todo determine if the dependencyvisitor should be called after the validation visitor
// todo (Maybe, not sure) Maybe make a new class for allocation the memory state
let visitor = new ASTValidationVisitor();
visitor.visitAST(ast);

if (visitor.hasDetectedErrors()) {
    let gui = new GUI(null, null);
    gui.showValidationErrors(visitor.errors);
} else {
    let memoryState = visitor.getMemoryState();
    let gui = new GUI(ast, memoryState);
    gui.createGUI();
}