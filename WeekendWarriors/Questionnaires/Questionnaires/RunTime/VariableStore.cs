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

        public void RemoveValue(string name)
        {
            Questions.Remove(name);
        }

        public Questionnaires.Types.IType GetValue(string name)
        {
            return Questions[name].GetValue();
        }

        public event EventHandler VariableChanged;
        public void OnVariableChanged()
        {
            if (VariableChanged != null)
                VariableChanged(this, new EventArgs());
        }
    }
}
