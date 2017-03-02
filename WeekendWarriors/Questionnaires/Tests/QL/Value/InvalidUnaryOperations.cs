using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Tests.QL.Value
{
    [TestClass]
    public class InvalidUnaryOperations
    {
        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Bang operation on string was inappropriately allowed")]
        public void StringBangOperation()
        {
            var result = ValueCreator.CreateValue("string").Bang();
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Bang operation on int was inappropriately allowed")]
        public void IntBangOperation()
        {
            var result = ValueCreator.CreateValue(12345).Bang();
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Bang operation on decimal was inappropriately allowed")]
        public void DecimalBangOperation()
        {
            var result = ValueCreator.CreateValue(12.345m).Bang();
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Unary plus operation on string was inappropriately allowed")]
        public void StringUnaryPlusOperation()
        {
            var result = ValueCreator.CreateValue("string").Positive();
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Unary plus operation on boolean was inappropriately allowed")]
        public void BoolUnaryPlusOperation()
        {
            var result = ValueCreator.CreateValue(true).Positive();
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Unary minus operation on string was inappropriately allowed")]
        public void StringUnaryMinusOperation()
        {
            var result = ValueCreator.CreateValue("string").Negative();
        }

        [TestMethod]
        [ExpectedException(typeof(NotSupportedException), "Unary minus operation on boolean was inappropriately allowed")]
        public void BoolUnaryMinusOperation()
        {
            var result = ValueCreator.CreateValue(true).Negative();
        }
    }
}
