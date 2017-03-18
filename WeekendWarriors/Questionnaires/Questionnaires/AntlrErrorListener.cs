using Antlr4.Runtime;

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
