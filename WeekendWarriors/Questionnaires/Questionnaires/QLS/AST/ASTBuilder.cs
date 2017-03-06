using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime.Misc;
using System.Diagnostics;
using Antlr4.Runtime;

namespace Questionnaires.QLS.AST
{
    class ASTBuilder : QLSBaseVisitor<INode>
    {
        public INode Build(string input)
        {
            var inputStream = new AntlrInputStream(input);
            var lexer = new QLSLexer(inputStream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new QLSParser(tokens);
            return Visit(parser.stylesheet());
        }

        public override INode VisitStylesheet([NotNull] QLSParser.StylesheetContext context)
        {
            var name = context.Identifier().GetText();
            var pages = context.page();
            var styleSheet = new StyleSheet(name);
            foreach(var page in pages)
            {
                styleSheet.AddPage((dynamic)page.Accept(this));
            }

            return styleSheet;
        }

        public override INode VisitPage([NotNull] QLSParser.PageContext context)
        {
            var name = context.Identifier().GetText();
            var sections = context.section();
            var defaultStyles = context.defaultStyle();
            var page = new Page(name);
            
            foreach(var section in sections)
            {
                page.AddSection((dynamic)section.Accept(this));
            }

            foreach (var style in defaultStyles)
            {
                page.AddDefaultStyle((dynamic)style.Accept(this));
            }

            return page;
        }

        public override INode VisitSection([NotNull] QLSParser.SectionContext context)
        {
            var section = new Section(context.StringLiteral().GetText());
            foreach(var question in context.question())
            {
                section.AddChild((dynamic)question.Accept(this));
            }
            foreach (var childSection in context.section())
            {
                section.AddChild((dynamic)childSection.Accept(this));
            }
            foreach (var style in context.defaultStyle())
            {
                section.AddChild((dynamic)style.Accept(this));
            }
            return section;
        }

        public override INode VisitQuestion([NotNull] QLSParser.QuestionContext context)
        {
            var name = context.Identifier().GetText();
            if (context.widget() == null)
                return new Question(name);
            else
                return new QuestionWithWidget(name, (dynamic)context.widget().Accept(this));
        }

        public override INode VisitDefaultStyle([NotNull] QLSParser.DefaultStyleContext context)
        {
            var widget = context.widget().Accept(this);
            Questionnaires.Types.IType type; 
            switch(context.Type().GetText())
            {
                //boolean' | 'int' | 'string' | 'money
                case "boolean": type = new Questionnaires.Types.BooleanType(); break;
                case "int": type = new Questionnaires.Types.IntegerType(); break;
                case "string": type = new Questionnaires.Types.StringType(); break;
                case "money": type = new Questionnaires.Types.MoneyType(); break;
                default:
                    throw new InvalidProgramException();
            }

            var defaultStyle = new DefaultStyle(type, (dynamic)widget);

            foreach(var setting in context.setting())
            {
                var keyValuePair = setting.Accept(this);
                defaultStyle.AddWidgetProperty((dynamic)keyValuePair);
            }

            return defaultStyle;
        }

        public override INode VisitWidget([NotNull] QLSParser.WidgetContext context)
        {
            switch (context.Widget().GetText())
            {
                case "spinbox": return new Widgets.Spinbox();
                case "slider": return new Widgets.Slider();
                case "text": return new Widgets.Text();
                case "radio": return new Widgets.Radio();
                case "checkbox": return new Widgets.CheckBox();
                case "dropdown": return new Widgets.DropDown();
                default:
                    Debug.Assert(false);
                    break;
            }
            throw new InvalidProgramException();
        }

        public override INode VisitSetting([NotNull] QLSParser.SettingContext context)
        {
            // TODO: this is horrible!
            var key = context.Property().GetText();
            if (context.StringLiteral() != null)
                return new Setting(key, context.StringLiteral().GetText());
            if (context.NumberLiteral() != null)
                return new Setting(key, context.NumberLiteral().GetText());
            if (context.ColorLiteral() != null)
                return new Setting(key, context.ColorLiteral().GetText());

            throw new InvalidProgramException();
        }
    }
}
