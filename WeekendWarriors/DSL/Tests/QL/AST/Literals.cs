using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using DSL.AST;

namespace Tests.QL.AST
{
    [TestClass]
    public class Literals
    {
        [TestMethod]
        public void StringLiteral()
        {
            Assert.AreEqual(ExpressionFactory.Create("\"This is a string\"").GetType(), typeof(QLString));
        }

        [TestMethod]
        public void BooleanLiteral()
        {
            Assert.AreEqual(ExpressionFactory.Create("true").GetType(), typeof(QLBoolean));
            Assert.AreEqual(ExpressionFactory.Create("false").GetType(), typeof(QLBoolean));
        }

        [TestMethod]
        public void NumberLiteral()
        {
            Assert.AreEqual(ExpressionFactory.Create("123456").GetType(), typeof(QLNumber));
            Assert.AreEqual(ExpressionFactory.Create("0").GetType(), typeof(QLNumber));
        }

        [TestMethod]
        public void MoneyLiteral()
        {
            Assert.AreEqual(ExpressionFactory.Create("123.456").GetType(), typeof(QLMoney));
            Assert.AreEqual(ExpressionFactory.Create("0.").GetType(), typeof(QLMoney));
            Assert.AreEqual(ExpressionFactory.Create(".123").GetType(), typeof(QLMoney));
        }
    }
}
