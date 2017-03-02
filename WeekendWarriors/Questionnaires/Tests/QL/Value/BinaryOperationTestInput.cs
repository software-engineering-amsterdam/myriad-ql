using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tests.QL.Value
{
    class BinaryOperationTestInput : OperationTestInput
    {
        public BinaryOperationTestInput(object leftHandSideOperand, object rightHandSideOperand, object expectedResult) : base(leftHandSideOperand, expectedResult)
        {
            this.RightHandSideOperand = rightHandSideOperand;
        }
        public object RightHandSideOperand
        {
            get;
        }
    }
}
