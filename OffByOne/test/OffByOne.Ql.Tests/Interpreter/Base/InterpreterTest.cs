namespace OffByOne.Ql.Tests.Interpreter.Base
{
    using OffByOne.Ql.Interpreter.Widgets.Base;
    using OffByOne.Runner.Builder;

    using Xunit;

    public class InterpreterTest
    {
        protected Widget GetInterpretationFromInput(string input)
        {
            var builder = new QlLanguageBuilder(input);
            var ast = builder.BuildAst();
            var report = builder.CheckSyntax(ast);
            Assert.False(report.HasErrors());

            var form = builder.CreateFormWidget(ast);
            return form;
        }
    }
}
