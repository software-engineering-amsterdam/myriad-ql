using Questionnaires.RunTime;
using System;
using System.Collections.Generic;
using Questionnaires.ExpressionEvaluator;

namespace RunTime.RuleContainer
{
    using Rule = Action<Evaluator>;

    class RuleContainer
    {
        private List<Rule> Rules = new List<Rule>();
        private Evaluator ExpresionEvaluator;

        public RuleContainer(Evaluator expresionEvaluator, List<Rule> rules)
        {
            Rules = rules;
            ExpresionEvaluator = expresionEvaluator;
        }

        public void ApplyRules()
        {
            foreach (var rule in Rules)
            {
                rule(ExpresionEvaluator);
            }
        }
    }
}
