/**
 * Created by alexvanmanen on 24-02-17.
 */

import find     from 'lodash/find';
import {AbstractVisitor} from '../../AbstractVisitor.js';
import {Expression} from '../../expressions/Expression.js';
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from '../../types/Types.js';

export class ASTDependencyVisitor extends AbstractVisitor {

    constructor() {
        super();
        this.graph = [];
        this.innerGraph = [];
        this.errors = [];
    }

    visitAST(ast) {
        this.visitStatements(ast.getStatements());

    }

    visitStatements(statements) {
        for (let statement of statements) {
            statement.accept(this);
        }
        this.checkForCyclicDependencies();
    }

    visitQuestion(question) {
        for (let node of this.innerGraph) {
            this.graph.push([node,question.getProperty()]);
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
        for (let node of this.graph) {
            console.log('node: ' + node);
            for (let innernode of this.graph) {
                if (node[0] === innernode[1] && node[1] === innernode[0]) {
                    this.errors.push(`Cyclic dependency detected between ${node[0]} and ${node[1]}`);
                }
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