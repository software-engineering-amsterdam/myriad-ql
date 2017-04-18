using Antlr4.Runtime;
using Questionnaires.ErrorHandling;

namespace Questionnaires.QL.AST
{
    class CSTBuilder
    {
        private Result Messages;

        public CSTBuilder(Result result)
        {
            Messages = result;
        }

        public QLParser.FormContext BuildForm(string input)
        {
            var parser = CreateParser(input);
            return parser.form();
        }

        public QLParser.ExpressionContext BuildExpression(string input)
        {
            var parser = CreateParser(input);
            return parser.expression();
        }

        public QLParser.ComputedQuestionContext BuildComputedQuestion(string input)
        {
            var parser = CreateParser(input);
            return parser.computedQuestion();
        }

        private QLParser CreateParser(string input)
        {
            var inputStream = new AntlrInputStream(input);
            var lexer = new QLLexer(inputStream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new QLParser(tokens);
            AddErrorListener(parser);
            return parser;
        }

        private void AddErrorListener(QLParser parser)
        {
            parser.RemoveErrorListeners();
            parser.AddErrorListener(new AntlrErrorListener(Messages));
        }
    }
}
