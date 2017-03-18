package org.util.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.ql.ast.Node;
import org.ql.ast.SourceLocation;

public class SourceLocationHydrator {
    public static <N extends Node> N hydrateSourceLocation(N astNode, ParserRuleContext context) {
        astNode.setSourceLocation(new SourceLocation(context.start.getLine(), context.start.getCharPositionInLine()));

        return astNode;
    }
}
