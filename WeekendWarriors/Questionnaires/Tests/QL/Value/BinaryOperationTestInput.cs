namespace Tests.QL.Value
{
    class BinaryOperationTestInput : OperationTestInput
    {
        public BinaryOperationTestInput(object leftHandSideOperand, object rightHandSideOperand, object expectedResult) : base(leftHandSideOperand, expectedResult)
        {
            RightHandSideOperand = rightHandSideOperand;
        }
        public object RightHandSideOperand
        {
            get;
        }
    }
}
