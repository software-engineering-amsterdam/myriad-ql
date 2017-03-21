package parser

import java.io.{ Reader, StringReader }

import ast.Form.Statements
import ast._

class FormParser extends QLParser with ExpressionParser {
  def parseForm(input: Reader): Form = {
    parseAll(form, input) match {
      case Success(result, _) => result
      case failure: NoSuccess => scala.sys.error(failure.msg)
    }
  }

  private def question: Parser[Question] =
    ident ~ ":" ~ quotedString ~ typeName ~ opt(parentheses(expression)) ^^ {
      case identifier ~ _ ~ label ~ typeName ~ expr =>
        Question(identifier, label, typeName, expr)
    }

  private def conditional: Parser[Conditional] =
    "if" ~> parentheses(expression) ~ statements ~ opt("else" ~> statements) ^^ {
      case expression ~ ifBlock ~ None => Conditional(expression, ifBlock, Nil)
      case expression ~ ifBlock ~ Some(elseBlock) => Conditional(expression, ifBlock, elseBlock)
    }

  private def statements: Parser[Statements] = curlyBrackets(rep(conditional | question))

  private def form: Parser[Form] =
    "form" ~> ident ~ statements ^^ {
      case ident ~ statements => Form(ident, statements)
    }
}

object FormParser {
  def apply(input: String): Form = apply(new StringReader(input))

  def apply(input: Reader): Form = new FormParser().parseForm(input)
}
