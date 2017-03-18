using Questionnaires.RunTime;
using System;
using System.Collections.Generic;

namespace Questionnaires.RunTime { 
    using Rule = Action<ExpressionEvaluator>;

    class RuleContainer
    {
        private List<Rule> Rules = new List<Rule>();
        private ExpressionEvaluator ExpresionEvaluator;

        public RuleContainer(ExpressionEvaluator expresionEvaluator, List<Rule> rules)
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
