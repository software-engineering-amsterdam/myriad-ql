using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis.SemenaticAnalysisEvents;

namespace Questionnaires.AST
{
    public class ComputedQuestion : INode
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

        public QLType? CheckOperandTypes(List<QLType> parameters, SemanticAnalysis.QLContext context, List<ISemenaticAnalysisEvent> events)
        {
            Trace.Assert(parameters.Count == 2);
            var leftHandSideType = parameters[0];
            var rightHandsSideType = parameters[1];

            // Computed question is like an assignment. Only valid when question type is
            // equal to the expression type (we do not support (implicit) casts
            if(leftHandSideType != rightHandsSideType)
            {
                events.Add(new SemanticAnalysisError(string.Format("Cannot assign expression with type {0} to question of type {1}", rightHandsSideType, leftHandSideType)));                
            }

            // Question is our end stop so there is no type to return
            return null;
        }
    }
}
