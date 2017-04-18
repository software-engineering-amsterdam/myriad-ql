using Antlr4.Runtime;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;

namespace Tests.QL.DSL
{
    class LexerTestHarness
    {
        public static void TokenTypes(string inputString, List<string> expectdeTokenTypes)
        {
            AntlrInputStream input = new AntlrInputStream(inputString);
            QLLexer lexer = new QLLexer(input);

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
