/**
 * Created by alexvanmanen on 24-02-17.
 */

import find     from 'lodash/find';
import {AbstractVisitor} from '../../AbstractVisitor.js';
import {Expression} from '../../expressions/Expression.js';
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from '../../types/Types.js';


/**
 * This class checks for cyclic dependencies
 **/
export class ASTCyclicDependencyVisitor extends AbstractVisitor {

    constructor() {
        super();
        this.graph = [];
        this.innerGraph = [];
        this.errors = [];
    }

    visitAST(ast) {
        this.visitStatements(ast.getStatements());
        this.checkForCyclicDependencies();
    }

    visitStatements(statements) {
        for (let statement of statements) {
            statement.accept(this);
        }
    }

    visitQuestion(question) {
        for (let node of this.innerGraph) {
            this.graph.push([node,question.getProperty().getName()]);
        }
    }

    // todo
    visitAnswer(answer) {}

    visitIfStatement(ifStatement) {
        const temporaryGraph = this.innerGraph.slice(0);

        const condition = ifStatement.getCondition();
        condition.accept(this);

        this.visitStatements(ifStatement.getIfBody());
        this.innerGraph = temporaryGraph;
    }

    // todo
    visitIfElseStatement(ifElseStatement) {

    }

    visitPrefixExpression(prefixExpression) {

        const expression = prefixExpression.getExpression();
        expression.accept(this);
    }

    visitExpression(expression) {
        expression.getLeftHand().accept(this);
        expression.getRightHand().accept(this);
    }

    visitProperty(property) {
        this.innerGraph.push(property.getName());
    }

    checkForCyclicDependencies() {
        console.log(this.graph);
        /**
         * Not yet optimized with the algorithm of Johnson, because Donald Knuth (Knuth, 1974) states that:
         * "We should forget about small efficiencies, say about 97% of the time: premature optimization
         * is the root of all evil. Yet we should not pass up our opportunities in that critical 3%".
         *
         * Knuth, D. E. (1974). Structured Programming with go to Statements. ACM Computing Surveys (CSUR), 6(4), 261-301.
         */
        let memory = new Map()
        for (let node of this.graph) {
            memory.set(node[0], node[1]);
        }

        for (let node of this.graph) {
            let element = memory.get(node[1]);
            if(element === node[0]){
                this.errors.push(`Cyclic dependency detected between ${node[0]} and ${node[1]}`);
                memory.delete(node[0]); // preventing the twin error to be shown.
            }
        }
    }

    getErrors(){
        return this.errors;
    }

    hasDetectedErrors() {
        return this.errors.length > 0;
    }
}