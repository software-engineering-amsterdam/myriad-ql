using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;

namespace Tests.QL.DSL
{
    [TestClass]
    public class LexerTest
    {
        [TestMethod]
        public void Reservedwords()
        {
            LexerTestHarness.TokenTypes("boolean", new List<string> { "Type" });
            LexerTestHarness.TokenTypes("int", new List<string> { "Type" });
            LexerTestHarness.TokenTypes("string", new List<string> { "Type" });
            LexerTestHarness.TokenTypes("money", new List<string> { "Type" });
            LexerTestHarness.TokenTypes("decimal", new List<string> { "Type" });
        }

        [TestMethod]
        // Whitespace is skipped so the lexer should return nothing if we only put in whitespace
        public void Whitespace()
        {
            LexerTestHarness.TokenTypes(" ", new List<string> { });
            LexerTestHarness.TokenTypes("\t", new List<string> { });
            LexerTestHarness.TokenTypes("\r\n", new List<string> { });
        }

        [TestMethod]
        // The lexer should be greedy so keywords in an identifier should be no problem.
        public void IdentifiersWithKeywords()
        {
            LexerTestHarness.TokenTypes("boolean_value", new List<string> { "Identifier" });
            LexerTestHarness.TokenTypes("main_form", new List<string> { "Identifier" });
            LexerTestHarness.TokenTypes("my_string", new List<string> { "Identifier" });
            LexerTestHarness.TokenTypes("should_be_true", new List<string> { "Identifier" });
            LexerTestHarness.TokenTypes("should_be_false", new List<string> { "Identifier" });
        }

        [TestMethod]
        public void Identifiers()
        {
            LexerTestHarness.TokenTypes("thisIsAValidIdentifier", new List<string> { "Identifier" });
            LexerTestHarness.TokenTypes("_100", new List<string> { "Identifier" });
        }

        [TestMethod]
        public void Literals()
        {
            LexerTestHarness.TokenTypes("\"thisIsAValidIdentifier\"", new List<string> { "StringLiteral" });
            LexerTestHarness.TokenTypes("\"_100\"", new List<string> { "StringLiteral" });
            LexerTestHarness.TokenTypes("true", new List<string> { "BooleanLiteral" });
            LexerTestHarness.TokenTypes("false", new List<string> { "BooleanLiteral" });
            LexerTestHarness.TokenTypes("123456789", new List<string> { "NumberLiteral" });
            LexerTestHarness.TokenTypes("123.456", new List<string> { "MoneyLiteral" });
            /* We do not allow negative number/money literals in the lexer as it will
             * will lead to trouble at the parser end. For example, is 10-5 would then
             * be tokenized as <10>,<-5> while it could also be the expression 10-5
             * we choose to resolve this ambiguity after parsing */
        }
    }
}
