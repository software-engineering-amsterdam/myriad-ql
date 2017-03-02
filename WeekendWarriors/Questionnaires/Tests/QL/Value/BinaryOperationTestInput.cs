using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tests.QL.Value
{
    class BinaryOperationTestInput
    {
        public BinaryOperationTestInput(object leftHandSideOperand, object rightHandSideOperand, object expectedResult)
        {
            this.LeftHandSideOperand = leftHandSideOperand;
            this.RightHandSideOperand = rightHandSideOperand;
            this.ExpectedResult = expectedResult;
        }
        public object LeftHandSideOperand
        {
            get;
        }
        public object RightHandSideOperand
        {
            get;
        }
        public object ExpectedResult
        {
            get;
        }
    }
}
