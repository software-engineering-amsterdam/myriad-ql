/**
 * Created by Manuel on 25/02/2017.
 */

import {Router} from 'arva-js/core/Router.js';
import {Injection} from 'arva-js/utils/Injection.js';
import {Program} from './Program.js';
import {RenderVisitor} from './visitors/RenderVisitor.js';
import {EvaluationVisitor} from './visitors/EvaluationVisitor.js';
import {RenderInputVisitor} from './visitors/types/RenderInputVisitor.js';
import {RenderValueVisitor} from './visitors/types/RenderValueVisitor.js';

export class GUI {

    ast;

    constructor(ast = {}, memoryState = {}) {
        this.ast = ast;
        this.memoryState = memoryState;
    }

    async createGUI(ast = this.ast) {
        this.application = new Program(ast.getProgram());
        await this.application.start();

        this.renderGUI(this.application, this.ast, this.memoryState);
        this.memoryState.on('set', this.renderGUI.bind(this, this.application, this.ast, this.memoryState));
    }

    renderGUI(program = {}, ast = {}, memoryState = {}) {

        const view = program.createView();
        program.setViewForControllerMethod('QL', 'Index', view);

        const evaluationVisitor = new EvaluationVisitor();
        const renderInputVisitor = new RenderInputVisitor();
        const renderValueVisitor = new RenderValueVisitor();
        const renderVisitor = new RenderVisitor(memoryState, evaluationVisitor, renderValueVisitor, renderInputVisitor);
        renderVisitor.visitProgram(ast.getProgram(), view);

        const router = Injection.get(Router);
        router.go('QL', 'Index');
    }

    showValidationErrors(errors) {
        let innerHTML = document.body.innerHTML;
        innerHTML = innerHTML + `The following errors have occurred during validation:`;

        innerHTML = innerHTML + '<ul>';
        for (const errorStatement of errors) {
            innerHTML = innerHTML + '<li>' + errorStatement + '</li>';
        }
        innerHTML = innerHTML + '</ul>';
        document.body.innerHTML = innerHTML;

    }

    showValidationWarnings(warnings) {
        let innerHTML = document.body.innerHTML;
        innerHTML = innerHTML + `The following warnings have occurred during validation:`;

        innerHTML = innerHTML + '<ul>';
        for (const warningStatement of warnings) {
            innerHTML = innerHTML + '<li>' + warningStatement + '</li>';
        }
        innerHTML = innerHTML + '</ul>';
        document.body.innerHTML = innerHTML;
    }


    showParserErrors(parseString, errors) {
        let innerHTML = document.body.innerHTML;
        innerHTML = innerHTML + `The following errors have occurred during parsing:`;

        parseString = parseString.replace(/\n/g, '<br>');
        for (const error of errors) {
            innerHTML = innerHTML + `<br> <div ="error"> ${error}</div>`;
        }
        innerHTML = innerHTML + `<br> <div>${parseString}</div>`;
        document.body.innerHTML = innerHTML;
    }
}
