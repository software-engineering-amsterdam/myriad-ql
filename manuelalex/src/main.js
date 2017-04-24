/**
 * Created by Manuel on 12/02/2017.
 */

import {Parser} from './Parser.js';
import {ASTValidationVisitor} from './ast/visitors/ASTValidationVisitor.js';
import {ASTCyclicDependencyVisitor} from './ast/visitors/ASTCyclicDependencyVisitor.js';
import {ASTMemoryAllocationVisitor} from './ast/visitors/ASTMemoryAllocationVisitor.js';
import {MemoryState} from './memory/MemoryState';

import {GUI} from './gui/Gui.js';
import {AST} from './ast/AST.js';

import {
    test1, test2, test3,
    test4, test5, test6,
    test7, test8, test9
} from './test/TestStrings.js';


let parser = new Parser();
let { result, errors, parseString } = parser.parse(test9);

if (errors.length) {
    let gui = new GUI(null, null);
    gui.showParserErrors(parseString, errors);
} else {

    let ast = new AST(result[0]);

    let memoryState = new MemoryState();
    let memoryVisitor = new ASTMemoryAllocationVisitor(memoryState);
    memoryVisitor.visitAST(ast);

    let validationVisitor = new ASTValidationVisitor(memoryState);
    validationVisitor.visitAST(ast);

    let dependencyVisitor = new ASTCyclicDependencyVisitor();
    dependencyVisitor.visitAST(ast);

    let gui = new GUI(null, null);

    /* Show any detected warnings */
    if (validationVisitor.hasDetectedWarnings()) {
        let warnings = validationVisitor.getWarnings();
        gui.showValidationWarnings(warnings);
    }

    /* Show any detected errors */
    if (validationVisitor.hasDetectedErrors() || dependencyVisitor.hasDetectedErrors()) {
        gui.showValidationErrors(validationVisitor.getErrors().concat(dependencyVisitor.getErrors()));
    } else {
        let memoryState = validationVisitor.getMemoryState();
        let gui = new GUI(ast, memoryState);
        gui.createGUI();
    }
}
