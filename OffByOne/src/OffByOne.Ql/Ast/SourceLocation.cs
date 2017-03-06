namespace OffByOne.Ql.Ast
{
    public class SourceLocation
    {
        public SourceLocation(string text, int line, int column)
        {
            this.Text = text;
            this.Line = line;
            this.Column = column;
        }

        public string Text { get; }

        public int Line { get; }

        public int Column { get; }

        public override string ToString()
        {
            return $"({this.Line}|{this.Column}) : {RemoveEmptyLines(this.Text)}";
        }

        private static string RemoveEmptyLines(string input)
        {
            return input
                .Replace("\r\n", string.Empty)
                .Replace("\n", string.Empty)
                .Replace("\r", string.Empty);
        }
    }
}
