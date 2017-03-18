using System;
using System.Collections.Generic;
using System.Diagnostics;


namespace Questionnaires.RunTime
{
    public class VariableStore
    {
        private Dictionary<string, RunTime.Question> Questions = new Dictionary<string, RunTime.Question>();

        public void AddQuestion(RunTime.Question question)
        {
            Debug.Assert(!Questions.ContainsKey(question.Identifier));
            Questions.Add(question.Identifier, question);
            question.ValueChanged += (sender, args) => OnVariableChanged();
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
