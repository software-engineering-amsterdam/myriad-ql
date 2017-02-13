package org.ql.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.ql.ast.Identifier;
import org.ql.ast.declaration.Declaration;
import org.ql.ast.declaration.Question;
import org.ql.ast.declaration.Statement;
import org.ql.ast.form.Form;
import org.ql.ast.literal.StringLiteral;
import org.ql.ast.type.Type;
import org.ql.grammar.QLParserParser;
import org.ql.grammar.QLParserParser.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ASTFactory {

    private final HashMap<Integer, Type> typesMap = new HashMap<Integer, Type>(){{
        put(QLParserParser.BOOLEAN, Type.BOOLEAN);
        put(QLParserParser.STRING, Type.STRING);
        put(QLParserParser.FLOAT, Type.FLOAT);
        put(QLParserParser.INTEGER, Type.INTEGER);
        put(QLParserParser.MONEY, Type.MONEY);
        put(QLParserParser.DATE, Type.DATE);
    }};

    public Form createAST(FormContext formContext) {
        return new Form(new Identifier(formContext.ID().getText()), createDeclarations(formContext.declaration()));
    }

    private ArrayList<Declaration> createDeclarations(List<DeclarationContext> declarationContexts) {
        ArrayList<Declaration> declarations = new ArrayList<Declaration>();

        for (DeclarationContext declarationContext : declarationContexts) {
            ParseTree declaration = declarationContext.getChild(0);

            if (declaration instanceof QuestionContext) {
                declarations.add(createQuestion((QuestionContext) declaration));
            } else if (declaration instanceof Statement) {
                // create stmt
            }
        }

        return declarations;
    }

    private Declaration createQuestion(QuestionContext questionContext) {
        return new Question(
            createIdentifier(questionContext.ID()),
            createStringLiteral(questionContext.STRING_LITERAL()),
            createType(questionContext.type())
        );
    }

    private Type createType(TypeContext type) {
        return typesMap.get(((TerminalNode) type.getChild(0)).getSymbol().getType());
    }

    private StringLiteral createStringLiteral(TerminalNode literal) {
        String literalText = literal.getText();
        return new StringLiteral(literalText.substring(1, literalText.length() - 1));
    }

    private Identifier createIdentifier(TerminalNode id) {
        return new Identifier(id.getText());
    }
}
