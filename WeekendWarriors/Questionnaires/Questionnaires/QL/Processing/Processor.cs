using Questionnaires.QL.AST;
using Questionnaires.RunTime.DocumentModel;
using Questionnaires.RunTime;
using Questionnaires.QL.AST.Types;
using System;
using System.Collections.Generic;

namespace Questionnaires.QL.Processing
{
    public class Processor
    {
        private List<RunTime.Question> Questions;
        private List<Action<ExpressionEvaluator>> Rules;
        private DocumentModel DocumentModel;

        public Processor(List<RunTime.Question> questions, List<Action<ExpressionEvaluator>> rules, DocumentModel documentModel)
        {
            Questions = questions;
            Rules = rules;
            DocumentModel = documentModel;
        }

        public void Process(Form form)
        {
            var defaultVisibilityFunction = new Func<ExpressionEvaluator, bool>((evaluator) => { return true; });

            foreach (var statement in form.Statements)
            {
                // Visit all of the child nodes. Pass a dummy function that always returns true as the visibility function
                Process((dynamic)statement, defaultVisibilityFunction);
            }

            CreateDocumentModel(form);
        }

        private void Process(ComputedQuestion node, Func<ExpressionEvaluator, bool> visibilityCondition)
        {
            Process(node.Question, visibilityCondition);
            var question = Questions.Find((q) => q.Identifier == node.Question.Identifier);

            Rules.Add(
                (evaluator) =>
                {
                    if (visibilityCondition(evaluator))
                    {
                        question.SetValue(evaluator.Evaluate(node.Expression));
                    }
                });
        }

        private void Process(AST.Question node, Func<ExpressionEvaluator, bool> visibilityCondition)
        {
            var runTimeQuestion = new RunTime.Question(node);
            // Add a rule to the rule container that sets the visibility for this question
            Rules.Add(
                (evaluator) =>
                {
                    runTimeQuestion.SetVisibility(visibilityCondition(evaluator));
                }
            );

            Questions.Add(runTimeQuestion);
        }

        private void Process(Conditional node, Func<ExpressionEvaluator, bool> visibilityCondition)
        {
            /* The conditional node. This is where we need to do some real work. We need to make function objects
             * That evaluate the condition and based on the outcome set the visibility of questions */

            Func<ExpressionEvaluator, bool> conditionFunctionThen =
                (evaluator) => { return visibilityCondition(evaluator) && (evaluator.Evaluate(node.Condition) as BooleanType).GetValue(); };

            Func<ExpressionEvaluator, bool> conditionFunctionElse = (evaluator) => { return !conditionFunctionThen(evaluator); };

            foreach (var thenStatement in node.ThenStatements)
                Process((dynamic)thenStatement, conditionFunctionThen);

            foreach (var elseStatement in node.ElseStatements)
                Process((dynamic)elseStatement, conditionFunctionElse);
        }

        private void CreateDocumentModel(Form form)
        {
            DocumentModel.Clear();
            var MainPage = new Page(form.Identifier);
            foreach (var question in Questions)
                MainPage.AddQuestion(question);
            DocumentModel.AddPage(MainPage);
        }
    }
}
