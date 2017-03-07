namespace OffByOne.Ql.Interpreter
{
    using OffByOne.Ql.Evaluator;
    using OffByOne.Ql.Values.Contracts;

    public class GuiChange
    {
        public GuiChange(string identifier, IValue value, GuiEnvironment env)
        {
            this.Identifier = identifier;
            this.Value = value;
            this.Environment = env;
        }

        public string Identifier { get; private set; }

        public IValue Value { get; private set; }

        public GuiEnvironment Environment { get; private set; }
    }
}
