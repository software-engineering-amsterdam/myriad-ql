package org.uva.hatt.taxform;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.uva.hatt.taxform.antlr.QLLexer;
import org.uva.hatt.taxform.antlr.QLParser;

public class Main {

    public static void main(String[] args) {
        ANTLRStringStream in = new ANTLRStringStream("12*(5-6)");
        QLLexer lexer = new QLLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
    }

}
