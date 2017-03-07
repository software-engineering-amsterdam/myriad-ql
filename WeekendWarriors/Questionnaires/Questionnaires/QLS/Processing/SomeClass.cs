using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.QLS.AST;

namespace Questionnaires.QLS.Processing
{   
    class SomeClass
    {
        Dictionary<string, QL.AST.Question> Questions;

        public SomeClass(Dictionary<string, QL.AST.Question> questions)
        {
            Questions = questions;
        }


        public void Process(StyleSheet styleSheet)
        {
            foreach(var page in styleSheet.Pages)
            {
                Visit((dynamic)page);                 
            }
        }

        void Visit(Page page)
        {
            foreach(var section in page.Sections)
            {
                Visit((dynamic)section);
            }
        }

        void Visit(Section section)
        {
            foreach(var question in section.Questions)
            {
                Visit((dynamic)question);
                // Option 1 a widget has been specified specifically for this question
                
                // Option 2 a widget has been specified for this type of question in this section

                // Option 3 a widget has been specified for this type of question in one of the upwards sections

                // Option 4 no widget has been specified for this type of question in QLS
            }
            
        }

        void Visit(QuestionWithWidget question)
        {
            this.Questions[question.Name].Widget = question.Widget.CreateWidget();
        }

        void Visit(Question question)
        {

        }
    }
}
