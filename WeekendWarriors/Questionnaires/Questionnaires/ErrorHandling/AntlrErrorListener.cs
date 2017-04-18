using Antlr4.Runtime;

namespace Questionnaires.ErrorHandling
{
    class AntlrErrorListener : BaseErrorListener
    {
        private Result Result;

        public AntlrErrorListener(Result result)
        {
            Result = result;
        }

        public override void SyntaxError(IRecognizer recognizer, IToken offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
        {
            Result.AddEvent(new Error(msg));
        }
    }
}
