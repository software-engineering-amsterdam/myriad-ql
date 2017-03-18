using Questionnaires.QL.AST;
using Questionnaires.Renderer.Containers;
using Questionnaires.RunTime;
using Questionnaires.Types;
using System;
using System.Collections.Generic;

namespace Questionnaires.QL.Processing
{
    class Processor
    {
        private List<RunTime.Question> Questions;
        private List<Action<QuestionStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>> Rules;
        private DocumentModel DocumentModel;

        public Processor(List<RunTime.Question> questions, List<Action<QuestionStore, Renderer.Renderer, ExpressionEvaluator.Evaluator>> rules, DocumentModel documentModel)
        {
            Questions = questions;
            Rules = rules;
            DocumentModel = documentModel;
        }

        public void Process(Form form)
        {
            var defaultVisibilityFunction = new Func<ExpressionEvaluator.Evaluator, bool>((evaluator) => { return true; });

            foreach (var statement in form.Statements)
            {
                // Visit all of the child nodes. Pass a dummy function that always returns true as the visibility function
                Visit((dynamic)statement, defaultVisibilityFunction);
            }

            CreateDocumentModel(form);
        }

        private void Visit(ComputedQuestion node, Func<ExpressionEvaluator.Evaluator, bool> visibilityCondition)
        {
            Visit(node.Question, visibilityCondition);
            var question = Questions.Find((q) => q.Identifier == node.Question.Identifier);

            Rules.Add(
                (variableStore, renderer, evaluator) =>
                {
                    if (visibilityCondition(evaluator))
                    {
                        question.SetValue(evaluator.Evaluate(node.Expression));
                    }
                    else
                    {
                        // TODO: we are not quite ready for this since we don't handle values that are not present in the variableStore
                        //variableStore.RemoveValue(node.Question.Identifier);
                    }
                });
        }

        private void Visit(AST.Question node, Func<ExpressionEvaluator.Evaluator, bool> visibilityCondition)
        {
            var runTimeQuestion = new RunTime.Question(node);
            // Add a rule to the rule container that sets the visibility for this question
            Rules.Add(
                (variableStore, renderer, evalutor) =>
                {
                    runTimeQuestion.SetVisibility(visibilityCondition(evalutor));
                }
            );

            Questions.Add(runTimeQuestion);
        }

        private void Visit(Conditional node, Func<ExpressionEvaluator.Evaluator, bool> visibilityCondition)
        {
            /* The conditional node. This is where we need to do some real work. We need to make function objects
             * That evaluate the condition and based on the outcome set the visibility of questions */

            Func<ExpressionEvaluator.Evaluator, bool> conditionFunctionThen =
                (evaluator) => { return visibilityCondition(evaluator) && (evaluator.Evaluate(node.Condition) as BooleanType).GetValue(); };

            Func<ExpressionEvaluator.Evaluator, bool> conditionFunctionElse = (evaluator) => { return !conditionFunctionThen(evaluator); };

            foreach (var thenStatement in node.ThenStatements)
                Visit((dynamic)thenStatement, conditionFunctionThen);

            foreach (var elseStatement in node.ElseStatements)
                Visit((dynamic)elseStatement, conditionFunctionElse);
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
