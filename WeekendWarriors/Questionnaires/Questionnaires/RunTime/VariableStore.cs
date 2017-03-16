using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Questionnaires.RunTime
{
    public class VariableStore
    {
        private Dictionary<string, RunTime.Question> Questions = new Dictionary<string, RunTime.Question>();

        public void AddQuestion(RunTime.Question question)
        {
            Debug.Assert(!Questions.ContainsKey(question.Identifier));
            Questions.Add(question.Identifier, question);
        }

        public void SetValue(string name, IType value)
        {            
            bool changed = (!Questions.ContainsKey(name) || value.InequalTo((dynamic)Questions[name].Type).GetValue());
            Questions[name].Type = value;

            if (changed)
                OnVariableChanged(name, value);
        }

        public void RemoveValue(string name)
        {
            Questions.Remove(name);
        }

        public Questionnaires.Types.IType GetValue(string name)
        {
            return Questions[name].Type;
        }

        public delegate void VariableChangedEventHandler(object sender, VariableChangedEventArgs arg);
        public event VariableChangedEventHandler VariableChanged;
        public void OnVariableChanged(string name, IType value)
        {
            if (VariableChanged != null)
                VariableChanged(this, new VariableChangedEventArgs(name, value));
        }        
    }
}
