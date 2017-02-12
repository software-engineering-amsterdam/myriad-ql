using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QL.DSL
{
    class LexerTestHarness
    {
        public static void TokenTypes(string inputString, List<string> expectdeTokenTypes)
        {
            AntlrInputStream input = new AntlrInputStream(inputString);
            GrammarLexer lexer = new GrammarLexer(input);

            string tokenType;
            List<string> tokenTypes = new List<string>();
            do
            {
                IToken token = lexer.NextToken();
                tokenType = lexer.Vocabulary.GetSymbolicName(token.Type);
                if (tokenType != "EOF")
                    tokenTypes.Add(tokenType);
            }
            while (tokenType != "EOF");

            CollectionAssert.AreEqual(expectdeTokenTypes, tokenTypes);
        }
    }
}
