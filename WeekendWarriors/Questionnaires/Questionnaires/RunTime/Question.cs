﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Questionnaires.RunTime
{
    public class Question
    {
        public Questionnaires.Renderer.Widgets.QuestionWidget Widget { get; set; }
        private QL.AST.Question QuestionASTNode; // TODO: come up with a better name

        public Question(QL.AST.Question questionASTNode)
        {
            QuestionASTNode = questionASTNode;
            Widget = questionASTNode.Type.GetWidget();
        }

        public string Identifier
        {
            get { return QuestionASTNode.Identifier; }
        }

        public string Body
        {
            get { return QuestionASTNode.Body; }
        }

        public void SetWidget(QLS.AST.Widgets.Widget widget)
        {
            Widget = widget.CreateWidget((dynamic)QuestionASTNode.Type);
        }

        public Types.IType Type // TODO: we need to get rid of this but for now we need it for the typeof we do in the QLS processor
        {
            get { return QuestionASTNode.Type; }
        }
    }
}
