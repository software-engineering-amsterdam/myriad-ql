using Antlr4.Runtime;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.QLS.AST
{
    class CSTBuilder
    {
        private Compilation.Result Messages;

        public CSTBuilder(Compilation.Result result)
        {
            Messages = result;
        }

        public QLSParser.StylesheetContext BuildStyleSheet(string input)
        {
            var inputStream = new AntlrInputStream(input);
            var lexer = new QLSLexer(inputStream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new QLSParser(tokens);
            AddErrorListener(parser);
            return parser.stylesheet();
        }

        private void AddErrorListener(QLSParser parser)
        {
            parser.RemoveErrorListeners();
            parser.AddErrorListener(new AntlrErrorListener(Messages));
        }
    }
}
