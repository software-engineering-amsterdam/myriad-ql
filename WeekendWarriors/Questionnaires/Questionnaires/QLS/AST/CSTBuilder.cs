using Antlr4.Runtime;
using Questionnaires.ErrorHandling;

namespace Questionnaires.QLS.AST
{
    public class CSTBuilder
    {
        private Result Messages;

        public CSTBuilder(Result result)
        {
            Messages = result;
        }

        public QLSParser.StylesheetContext BuildStyleSheet(string input)
        {
            var parser = CreateParser(input);
            return parser.stylesheet();
        }

        private void AddErrorListener(QLSParser parser)
        {
            parser.RemoveErrorListeners();
            parser.AddErrorListener(new AntlrErrorListener(Messages));
        }

        private QLSParser CreateParser(string input)
        {
            var inputStream = new AntlrInputStream(input);
            var lexer = new QLSLexer(inputStream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new QLSParser(tokens);
            AddErrorListener(parser);
            return parser;
        }
    }
}
