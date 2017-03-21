namespace Tests.QL.Value
{
    class OperationTestInput
    {
        public OperationTestInput(object leftHandSideOperand, object expectedResult)
        {
            LeftHandSideOperand = leftHandSideOperand;
            ExpectedResult = expectedResult;
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
