using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OffByOne.Ql
{
    internal abstract class AstNode { }
 
    internal abstract class QuestionNode
    {
        String Question { get; set; }
        String Identifier { get; set; }
    }

    internal abstract class BranchNode
    {
        AstNode[] Block { get; set; }
    }

    internal abstract class InfixExpressionNode : AstNode
    {
        public AstNode Left { get; set; }
        public AstNode Right { get; set; }
    }
    internal abstract class UnaryExpressionNode : AstNode
    {
        public AstNode Child { get; set; }
    }

    internal class FormNode : AstNode
    {
        String Identifier { get; set; }
        AstNode[] Children { get; set; }
    }

    internal class IfBranchNode : BranchNode
    {
        AstNode Condition { get; set; }
    }

    internal class ElseBranchNode : BranchNode { }

    internal class BooleanQuestionNode : QuestionNode { }
    internal class NumericQuestionNode : QuestionNode { }
    internal class StringQuestionNode : QuestionNode { }

    internal class NegateNode : UnaryExpressionNode { }
    internal class AdditionNode : InfixExpressionNode { }
    internal class SubtractionNode : InfixExpressionNode { }
    internal class MultiplicationNode : InfixExpressionNode { }
    internal class DivisionNode : InfixExpressionNode { }
    internal class AndNode : InfixExpressionNode { }
    internal class OrNode : InfixExpressionNode { }
    internal class GreaterThanEqualNode : InfixExpressionNode { }
    internal class LesserThanEqualNode : InfixExpressionNode { }
    internal class GreaterThanNode : InfixExpressionNode { }
    internal class LesserThanNode : InfixExpressionNode { }
    internal class EqualNode : InfixExpressionNode { }

    internal class StringNode
    {
        String Value { get; set; }
    }

    internal class BooleanNode
    {
        Boolean Value { get; set; }
    }

    internal class IntegerNode
    {
        int Value { get; set; }
    }

    internal class DateNode
    {
        DateTime Value { get; set; }
    }

    internal class DecimalNode
    {
        Decimal Value { get; set; }
    }

    internal class MoneyNode
    {
        float Value { get; set; }
    }
}
