package parser

import model.{ BooleanLiteral, Expression }

class ExpressionChecker extends Checker[Expression] {

  def check: Either[Issues, (Expression, Warnings)] = {
    Right((BooleanLiteral(false), Seq(Warning("Expression Checking not yet implemented"))))
  }

}
