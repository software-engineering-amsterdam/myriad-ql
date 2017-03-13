using Antlr4.Runtime;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires 
{
    class AntlrErrorListener : BaseErrorListener
    {
        private Compilation.Result Result;

        public AntlrErrorListener(Compilation.Result result)
        {
            Result = result;
        }

        public override void SyntaxError(IRecognizer recognizer, IToken offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
        {
            Result.AddEvent(new Compilation.Error(msg));
        }
    }
}
