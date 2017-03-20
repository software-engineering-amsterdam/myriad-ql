namespace OffByOne.Ql.Interpreter
{
    using OffByOne.Ql.Values.Contracts;

    public class AnswerInput
    {
        public AnswerInput(string identifier, IValue value, GuiEnvironment env)
        {
            this.Identifier = identifier;
            this.Value = value;
            this.Environment = env;
        }

        public string Identifier { get; }

        public IValue Value { get; }

        public GuiEnvironment Environment { get; }
    }
}
