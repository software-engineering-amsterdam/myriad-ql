using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tests.QL.Value
{
    class OperationTestInput
    {
        public OperationTestInput(object leftHandSideOperand, object expectedResult)
        {
            this.LeftHandSideOperand = leftHandSideOperand;
            this.ExpectedResult = expectedResult;
        }
        public object LeftHandSideOperand
        {
            get;
        }
        public object ExpectedResult
        {
            get;
        }
    }
}
