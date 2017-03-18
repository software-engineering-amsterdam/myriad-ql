using System;
using System.Collections.Generic;
using System.Diagnostics;


namespace Questionnaires.RunTime
{
    public class QuestionStore
    {
        private Dictionary<string, RunTime.Question> Questions = new Dictionary<string, RunTime.Question>();

        public QuestionStore(IEnumerable<RunTime.Question> questions)
        {
            foreach(var question in questions)
            {
                Debug.Assert(!Questions.ContainsKey(question.Identifier));
                Questions.Add(question.Identifier, question);
                question.ValueChanged += (sender, args) => OnVariableChanged();
            }
        }

        public Questionnaires.Types.IType GetValue(string name)
        {
            Debug.Assert(Questions.ContainsKey(name));
            return Questions[name].GetValue();
        }

        public event EventHandler VariableChanged;
        private void OnVariableChanged()
        {
            if (VariableChanged != null)
                VariableChanged(this, new EventArgs());
        }
    }
}
