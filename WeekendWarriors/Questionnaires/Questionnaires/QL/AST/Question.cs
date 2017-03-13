﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Compilation;
using Questionnaires.Types;

namespace Questionnaires.QL.AST
{
    public class Question : IStatement
    {
        public Questionnaires.Renderer.Widgets.QuestionWidget Widget { get; set; }

        public Question(string identifier, string body, IType type)
        {
            this.Identifier = identifier;
            this.Body = body;
            this.Type = type;
            Widget = type.GetWidget();
        }

        public string Identifier
        {
            get;
        }

        public string Body
        {
            get;
        }

        public IType Type
        {
            get;
        }
        
        public bool CheckSemantics(QLContext context, List<Message> messages)
        {
            // Nothing to check for a qestion
            return true;
        }
    }
}
