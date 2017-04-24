/**
 * Created by Manuel on 12/02/2017.
 */

import {Parser} from './Parser.js';
import {ASTValidationVisitor} from './ast/visitors/ASTValidationVisitor.js';
import {ASTDependencyVisitor} from './ast/visitors/ASTDependencyVisitor.js';
import {ASTMemoryAllocationVisitor} from './ast/visitors/ASTMemoryAllocationVisitor.js';
import {MemoryState} from './memory/MemoryState';

import {GUI} from './gui/Gui.js';
import {AST} from './ast/AST.js';

import {
    test1, test2, test3,
    test4, test5, test6,
    test7, test8
} from './test/TestStrings.js';


let parser = new Parser();
let { result, errors, parseString } = parser.parse(test6);

if (errors.length) {
    let gui = new GUI(null, null);
    gui.showParserErrors(parseString, errors);
} else {

    let ast = new AST(result[0]);

    let memoryState = new MemoryState();
    let memoryVisitor = new ASTMemoryAllocationVisitor(memoryState);
    memoryVisitor.visitAST(ast);

    let visitor = new ASTValidationVisitor(memoryState);
    visitor.visitAST(ast);

    let dependencyVisitor = new ASTDependencyVisitor();
    dependencyVisitor.visitAST(ast);

// todo (Maybe, not sure) Maybe make a new class for allocation the memory state, as it is currenlty done by validating the AST. this is needed by the GUI to properly render

// todo show warnings somewehere

    if (visitor.hasDetectedErrors() || dependencyVisitor.hasDetectedErrors()) {
        let gui = new GUI(null, null);
        gui.showValidationErrors(visitor.getErrors().concat(dependencyVisitor.getErrors()));
    } else {
        let memoryState = visitor.getMemoryState();
        let gui = new GUI(ast, memoryState);
        gui.createGUI();
    }
}
