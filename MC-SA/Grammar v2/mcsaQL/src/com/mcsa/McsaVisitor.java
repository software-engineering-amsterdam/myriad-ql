package com.mcsa;

import com.mcsa.antlr.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Created by matt on 14/02/2017.
 */
public class McsaVisitor implements QLVisitor {
    @Override
    public Object visitStart(QLParser.StartContext ctx) {
        //System.out.println(ctx.start.getLine());
        return null;
    }

    @Override
    public Object visitStatementContent(QLParser.StatementContentContext ctx) {
        System.out.println(ctx);
        return null;
    }

    @Override
    public Object visitCategorise(QLParser.CategoriseContext ctx) {
        System.out.println(ctx);
        return null;
    }

    @Override
    public Object visitIfCase(QLParser.IfCaseContext ctx) {
        System.out.println(ctx);
        return null;
    }

    @Override
    public Object visitIfCaseArgs(QLParser.IfCaseArgsContext ctx) {
        System.out.println(ctx);
        return null;
    }

    @Override
    public Object visitCaseNewInput(QLParser.CaseNewInputContext ctx) {
        System.out.println(ctx);
        return null;
    }

    @Override
    public Object visitType(QLParser.TypeContext ctx) {
        System.out.println(ctx);
        return null;
    }

    @Override
    public Object visitMathaction(QLParser.MathactionContext ctx) {
        System.out.println(ctx);
        return null;
    }

    @Override
    public Object visit(ParseTree parseTree) {
        System.out.println(parseTree);
        return null;
    }

    @Override
    public Object visitChildren(RuleNode ruleNode) {
        System.out.println(ruleNode);
        return null;
    }

    @Override
    public Object visitTerminal(TerminalNode terminalNode) {
        System.out.println(terminalNode);
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode errorNode) {
        System.out.println(errorNode);
        return null;
    }
}
