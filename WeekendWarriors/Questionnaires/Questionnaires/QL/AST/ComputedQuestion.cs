using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis;
using Questionnaires.SemanticAnalysis.Messages;

namespace Questionnaires.QL.AST
{
    public class ComputedQuestion : IStatement
    {
        public ComputedQuestion(Question question, IExpression expression)
        {
            this.Question = question;
            this.Expression = expression;
        }

        public Question Question
        {
            get;
        }

        public IExpression Expression
        {
            get;
        }

        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            // Check both child nodes
            var questionSemanticallyValid = Question.CheckSemantics(context, messages);
            var expressionSemanticallyValid = Expression.CheckSemantics(context, messages);
            if (!questionSemanticallyValid || !expressionSemanticallyValid)
                return false;

            // Computed question is like an assignment. Only valid when question type is
            // equal to the expression type. We do not support (implicit) casts
            if(Question.Type.GetType() != Expression.GetResultType(context).GetType())
            {
                messages.Add(new Error(string.Format("Cannot assign expression with type {0} to question of type {1}", Question.Type, Expression.GetResultType(context))));
                return false;
            }

            return true;
        }
    }
}
