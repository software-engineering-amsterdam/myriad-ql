namespace OffByOne.LanguageCore.Ast
{
    using System.Text;

    using Antlr4.Runtime;

    public class SourceLocation
    {
        public SourceLocation(ParserRuleContext context)
        {
            var textOutput = new StringBuilder();
            for (var i = 0; i < context.ChildCount; i++)
            {
                textOutput.AppendLine(context.GetChild(i).GetText());
            }

            this.Text = textOutput.ToString();
            this.Line = context.Start.Line;
            this.Column = context.Start.Column;
        }

        public string Text { get; }

        public int Line { get; }

        public int Column { get; }

        public override string ToString()
        {
            return $"({this.Line}|{this.Column}) : {this.Text.Replace("\r\n", string.Empty).Replace("\n", string.Empty).Replace("\r", string.Empty)}";
        }
    }
}
