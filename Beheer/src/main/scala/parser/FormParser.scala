package parser

import java.io.{ Reader, StringReader }

import ast._
import model.FormModel

class FormParser extends QLParser with ExpressionParser {
  def parseForm(input: Reader): Form = {
    parseAll(form, input) match {
      case Success(result, _) => result
      case failure: NoSuccess => scala.sys.error(failure.msg)
    }
  }

  private def question: Parser[Question] =
    ident ~ ":" ~ label ~ typeName ~ opt(parentheses(expression)) ^^ {
      case identifier ~ _ ~ label ~ typeName ~ expr =>
        Question(identifier, label, typeName, expr)
    }

  private def conditional: Parser[Conditional] =
    "if" ~> parentheses(expression) ~ statements ^^ {
      case expression ~ block => Conditional(expression, block)
    }

  private def statements: Parser[Seq[Statement]] = curlyBrackets(rep(conditional | question))

  private def form: Parser[Form] =
    "form" ~> ident ~ statements ^^ {
      case ident ~ statements => Form(ident, statements)
    }
}

object FormParser {
  def apply(input: String): FormModel = apply(new StringReader(input))

  def apply(input: Reader): FormModel = new FormModel(new FormParser().parseForm(input))
}
