/*
 *
 *  Copyright 2012-2016 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;


public class ParseTest {
    @Test
    public void testExploratoryString() throws IOException {

        String simpleForm = "1+1";

        CharStream inputCharStream = new ANTLRInputStream(new StringReader(simpleForm));
        TokenSource tokenSource = new QLLexer(inputCharStream);
        TokenStream inputTokenStream = new CommonTokenStream(tokenSource);
        QLParser parser = new QLParser(inputTokenStream);

        QLParser.ExprContext context = parser.expr();
    }

    @Test
    public void testWalk() throws IOException {
        String simpleForm = "form naam {tmp : \"echt?\" boolean}";
        ANTLRInputStream input = new ANTLRInputStream(new StringReader(simpleForm));

        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);
        ParseTree tree = parser.form();

        ParseTreeWalker walker = new ParseTreeWalker();

        QLLoader loader = new QLLoader();

        walker.walk(loader, tree);
        //System.out.println(loader.names);
    }
//
//    @Test
//    public void printParse() throws IOException {
//        String simpleForm = "form bla {tmp : \"ja?\" boolean if (True) { tmpNest : \"nee?\" boolean}}";
//        // create a CharStream that reads from standard input
//        ANTLRInputStream input = new ANTLRInputStream(new StringReader(simpleForm));
//        // create a lexer that feeds off of input CharStream
//        QLLexer lexer = new QLLexer(input);
//        // create a buffer of tokens pulled from the lexer
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        // create a parser that feeds off the tokens buffer
//        QLParser parser = new QLParser(tokens);
//        ParseTree tree = parser.form(); // begin parsing at form rule
//        System.out.println(tree.toStringTree(parser));
//    }
}

