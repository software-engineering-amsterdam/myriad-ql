package parser

import parser.ast._

class TypeChecker {

  def checkIdentifiers(questions: Seq[model.Question]): Either[String, (model.Form, Seq[String])] = {
    val identifiers = questions.map(_.identifier)
    val duplicates = identifiers.diff(identifiers.distinct).distinct
    if (duplicates.nonEmpty) {
      Left(s"Duplicate identifier(s) found: ${duplicates.mkString("\n", "\n", "")}")
    } else {
      val labels = questions.map(_.label)
      val warnings = labels.diff(labels.distinct).distinct.map(l => s"Duplicate label: $l")
      Right((model.Form(questions), warnings))
    }
  }

  private def buildModel(statement: Statement, conditionals: Seq[ExpressionNode]): Seq[model.Question] = statement match {
    case Conditional(condition, Block(statements)) => statements.flatMap(buildModel(_, condition +: conditionals))
    case ast.Question(identifier, label, typeDeclaration) => Seq(model.Question(identifier, label, conditionals, typeDeclaration))
  }

  def checkReferences(questions: Seq[model.Question]) = {
    questions.flatMap {
      case model.Question(_, _, show, ValueType(_, expr)) => expr +: show
      case model.Question(_, _, show, _) => show
    }

  }

  private def extractIdentifiers(expressionNode: ExpressionNode): Seq[String] = expressionNode match {
    case Value(_) => Nil
    case Identifier(value) => Seq(value)
    case PrefixOperation(_, rhs) => extractIdentifiers(rhs)
    case InfixOperation(lhs, _, rhs) => extractIdentifiers(lhs) ++ extractIdentifiers(rhs)
  }

}

object TypeChecker {
  def apply(form: Form) = {
    val typeChecker = new TypeChecker
    val questions = form.block.statements.flatMap(typeChecker.buildModel(_, Nil))
    typeChecker.checkIdentifiers(questions)
  }
}