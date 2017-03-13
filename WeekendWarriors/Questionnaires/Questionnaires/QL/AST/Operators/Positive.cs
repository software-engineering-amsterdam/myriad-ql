﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.Compilation;
using System.Diagnostics;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Types;

namespace Questionnaires.QL.AST.Operators
{
    public class Positive : Unary
    {
        public Positive(IExpression operand) : base(operand)
        {

        }

        public override IType GetResultType(QLContext context)
        {
            return Operand.GetResultType(context).Positive();
        }
    }
}
