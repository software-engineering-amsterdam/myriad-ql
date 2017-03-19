using System;
using Antlr4.Runtime.Misc;
using System.Diagnostics;
using Questionnaires.ErrorHandling;
using Questionnaires.QL.AST.Types;

namespace Questionnaires.QLS.AST
{
    public class ASTBuilder : QLSBaseVisitor<INode>
    {
        private Result Messages;
        private CSTBuilder CSTBuilder;

        public ASTBuilder(Result result)
        {
            Messages = result;
            CSTBuilder = new CSTBuilder(Messages);
        }

        public StyleSheet BuildStylesheet(string input)
        {
            var CST = CSTBuilder.BuildStyleSheet(input);
            if (Messages.ContainsErrors())
                throw new ParseException();

            var stylesheet = Visit(CST);
            Debug.Assert(stylesheet.GetType() == typeof(StyleSheet));

            return stylesheet as StyleSheet;
        }

        public override INode VisitStylesheet([NotNull] QLSParser.StylesheetContext context)
        {
            var name = context.Identifier().GetText();
            var pages = context.page();
            var styleSheet = new StyleSheet(name);
            foreach (var page in pages)
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

            foreach (var section in sections)
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
            var section = new Section(RemoveDoubleQuotesAtStartAndEnd(context.StringLiteral().GetText()));
            foreach (var question in context.question())
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
            var widget = GetWidgetFromStyleContext(context);
            var type = GetTypeFromStyleContext(context);

            var defaultStyle = new DefaultStyle(type, (dynamic)widget);

            foreach (var setting in context.setting())
            {
                var keyValuePair = setting.Accept(this);
                defaultStyle.AddWidgetProperty((dynamic)keyValuePair);
            }

            return defaultStyle;
        }

        private static Widgets.Widget GetWidgetFromStyleContext(QLSParser.DefaultStyleContext context)
        {
            var widgetType = context.widget().Widget().GetText();
            Debug.Assert(widgetType == "spinbox" | widgetType == "slider" | widgetType == "text" | widgetType == "radio" | widgetType == "checkbox" | widgetType == "dropdown");
            switch (widgetType)
            {
                case "spinbox": return new Widgets.Spinbox(); 
                case "slider": return new Widgets.Slider(); 
                case "text": return new Widgets.Text(); 
                case "radio": return new Widgets.Radio(); 
                case "checkbox": return new Widgets.CheckBox(); 
                case "dropdown": return new Widgets.DropDown(); 
            }
            throw new InvalidProgramException();
        }

        private IType GetTypeFromStyleContext(QLSParser.DefaultStyleContext context)
        {
            var type = context.Type().GetText();
            Debug.Assert(type == "boolean" || type == "int" || type == "string" || type == "money");
            switch (type)
            {
                case "boolean": return new BooleanType(); 
                case "int": return new IntegerType();
                case "string": return new StringType(); 
                case "money": return new MoneyType();                     
            }
            throw new InvalidProgramException();
        }

        // TODO: this is just a copy of GetWidgetFromStyleContext
        public override INode VisitWidget([NotNull] QLSParser.WidgetContext context)
        {
            var widgetType = context.Widget().GetText();
            Debug.Assert(widgetType == "spinbox" | widgetType == "slider" | widgetType == "text" | widgetType == "radio" | widgetType == "checkbox" | widgetType == "dropdown");
            switch (widgetType)
            {
                case "spinbox": return new Widgets.Spinbox();
                case "slider": return new Widgets.Slider();
                case "text": return new Widgets.Text();
                case "radio": return new Widgets.Radio();
                case "checkbox": return new Widgets.CheckBox();
                case "dropdown": return new Widgets.DropDown();
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

        private string RemoveDoubleQuotesAtStartAndEnd(string unmodifiedString)
        {
            var modifiedString = unmodifiedString.Remove(0, 1);
            modifiedString = modifiedString.Remove(modifiedString.Length - 1, 1);
            return modifiedString;
        }
    }
}
