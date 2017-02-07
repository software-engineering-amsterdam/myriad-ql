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
import org.antlr.v4.runtime.tree.Trees;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

public class ParseTest {
    @Test
    public void testExploratoryString() throws IOException {

        String simpleForm = "1+1";

        CharStream inputCharStream = new ANTLRInputStream(new StringReader(simpleForm));
        TokenSource tokenSource = new QLLexer(inputCharStream);
        TokenStream inputTokenStream = new CommonTokenStream(tokenSource);
        QLParser parser = new QLParser(inputTokenStream);

        //parser.addErrorListener(new TestErrorListener());

        QLParser.ExprContext context = parser.expr();
        System.out.println(context.toString());
    }

    public void testParseTree() throws IOException {

        // Create an input stream that receives text from the terminal
        ANTLRInputStream input = new ANTLRInputStream(System.in);

        // Create an Lexer that receives the char stream
        QLLexer lexer = new QLLexer(input);

        // Create a token stream from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that receives the token stream
        QLParser parser = new QLParser(tokens);

        // Create a parser tree starting from the first rule
        QLParser.FormContext tree = parser.form();

        List<String> ruleNames = Arrays.asList(QLParser.ruleNames);
        //Generates the GUI
        TreeViewer view = new TreeViewer(ruleNames, tree);
        view.open();
    }
}
