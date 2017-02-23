using Questionnaires.AST;
using Questionnaires.Question;
using Questionnaires.Renderer;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Questionnaires.Value;

namespace Questionnaires
{
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();            
        }

        private void ReportSemanticError(object sender, SemanticAnalysis.SemanticErrorArgs e)
        {
            textBlock.Text = textBlock.Text + "\n Semantic error: " + e.Message;
        }

        private void Interpret_Click(object sender, RoutedEventArgs e)
        {
            textBlock.Text = "";

            var formFactory = new AST.ASTFactory();
            var parser = formFactory.CreateParser(Input.Text);
            var form = formFactory.CreateQLObject(parser, ASTFactory.QLObjectType.Form);

            var semanticAnalyzer = new SemanticAnalysis.Analyzer();

            semanticAnalyzer.SemanticError += ReportSemanticError;
            semanticAnalyzer.Analyze(form);

            // Example renderer functionality
            Renderer.Renderer renderer = new Renderer.Renderer();
            
            QuestionWidgetInfo q1 = new QuestionWidgetInfo();
            q1.Body = "Do you like apples?";
            q1.Name = "Apple question";
            q1.Type = QuestionType.Bool;
            q1.Value = new BoolValue(true);
            q1.Visibility = Question.Visibility.Visible;

            QuestionWidgetInfo q2 = new QuestionWidgetInfo();
            q2.Body = "Favorite number?";
            q2.Name = "Number question";
            q2.Type = QuestionType.Number;
            q2.Value = new IntValue(230);
            q2.Visibility = Question.Visibility.Visible;

            renderer.AddQuestion(q1);
            renderer.AddQuestion(q2);

            renderer.SetVisibility("Number question", Question.Visibility.Hidden);
            renderer.SetValue("Apple question", new BoolValue(false));            

            //renderer.AddQuestion(new Question.IQuestion) ?
            // we may need a visitor here to visit the form again and provide the renderer with questions.
        }
    }
}
