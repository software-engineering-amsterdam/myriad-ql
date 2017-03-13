﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Questionnaires.SemanticAnalysis;
using Questionnaires.Compilation;
using Questionnaires.Types;

namespace Questionnaires.QL.AST.Operators
{
    public class Multiply : Binary
    {
        public Multiply(IExpression lhs, IExpression rhs) : base(lhs, rhs)
        {
        }        

        public override IType GetResultType(QLContext context)
        {
            return Lhs.GetResultType(context).Multiply(Rhs.GetResultType(context));
        }
    }
}
