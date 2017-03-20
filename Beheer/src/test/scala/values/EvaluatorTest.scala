package values

import ast._
import org.scalacheck.Gen
import org.scalatest.prop.PropertyChecks
import org.scalatest.{ Matchers, PropSpec }
import values.Evaluator.Env

class EvaluatorTest extends PropSpec with Matchers with PropertyChecks with ExpressionAstGenerator {
  //So the generator knows the available identifiers:
  protected val numericIdentifiers = Seq("intval", "moneyval")
  protected val boolIdentifiers = Seq("boolval")
  private val runtimeEnv: Env = Map(
    "intval" -> IntegerValue(BigDecimal(1)),
    "moneyval" -> MoneyValue(BigDecimal(1.22)),
    "boolval" -> BooleanValue(true)
  )
  private val runTimeEnvEmpty: Env = Map.empty

  property("Commutative operators can have their operands swapped without changing function value.") {
    forAll(Gen.oneOf(genNumeric, genBoolean)) {
      expr => Evaluator(runtimeEnv).calculate(expr) == Evaluator(runtimeEnv).calculate(swapCommutativeOps(expr))
    }
  }
  property("Given empty environment, expressions referencing a variable should be UndefinedValue.") {
    forAll(Gen.oneOf(genNumeric, genBoolean) suchThat (e => containsIdentifier(e))) {
      expr => Evaluator(runTimeEnvEmpty).calculate(expr) == UndefinedValue
    }
  }
  property("Given empty environment, expressions not referencing a variable should never be UndefinedValue.") {
    forAll(Gen.oneOf(genNumeric, genBoolean) suchThat (e => !containsIdentifier(e))) {
      expr => Evaluator(runTimeEnvEmpty).calculate(expr) != UndefinedValue
    }
  }

  private def containsIdentifier(node: ExpressionNode): Boolean = node match {
    case i: InfixNode => containsIdentifier(i.lhs) || containsIdentifier(i.rhs)
    case p: PrefixNode => containsIdentifier(p.operand)
    case Identifier(_) => true
    case _ => false
  }

  private def swapCommutativeOps(node: ExpressionNode): ExpressionNode = node match {
    //Prefix: continue traversal
    case Not(op) => Not(swapCommutativeOps(op))
    case Neg(op) => Neg(swapCommutativeOps(op))
    //Commutative infix nodes (swap left & right);
    case And(lhs, rhs) => And(swapCommutativeOps(rhs), swapCommutativeOps(lhs))
    case Or(lhs, rhs) => Or(swapCommutativeOps(rhs), swapCommutativeOps(lhs))
    case Add(lhs, rhs) => Add(swapCommutativeOps(rhs), swapCommutativeOps(lhs))
    case Mul(lhs, rhs) => Mul(swapCommutativeOps(rhs), swapCommutativeOps(lhs))
    case Eq(lhs, rhs) => Eq(swapCommutativeOps(rhs), swapCommutativeOps(lhs))
    case Neq(lhs, rhs) => Neq(swapCommutativeOps(rhs), swapCommutativeOps(lhs))
    // Non commutative infix nodes:
    case Sub(lhs, rhs) => Sub(swapCommutativeOps(lhs), swapCommutativeOps(rhs))
    case Div(lhs, rhs) => Div(swapCommutativeOps(lhs), swapCommutativeOps(rhs))
    case Gt(lhs, rhs) => Gt(swapCommutativeOps(lhs), swapCommutativeOps(rhs))
    case Lt(lhs, rhs) => Lt(swapCommutativeOps(lhs), swapCommutativeOps(rhs))
    case Leq(lhs, rhs) => Leq(swapCommutativeOps(lhs), swapCommutativeOps(rhs))
    case Geq(lhs, rhs) => Geq(swapCommutativeOps(lhs), swapCommutativeOps(rhs))
    case i: InfixNode => sys.error(s"Incomplete traversal in swapCommutativeOps, found node: $i")
    case p: PrefixNode => sys.error(s"Incomplete traversal in swapCommutativeOps, found node: $p")
    //Should only be literals/nodes without children:
    case n => n
  }
}
