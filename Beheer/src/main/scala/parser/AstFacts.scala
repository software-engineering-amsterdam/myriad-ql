package parser

import parser.ast._

class AstFacts(form: Form) {
  lazy val questionsWithShowConditions: Seq[(ast.Question, Seq[ExpressionNode])] = form.block.statements.flatMap(flattenForm(_, Nil))

  lazy val expressions: Seq[(ast.ExpressionNode, Type)] = extractExpressions(form)

  lazy val questions: Seq[ast.Question] = questionsWithShowConditions.map { case (q, _) => q }

  lazy val questionLabels: Seq[String] = questions.map(_.label)

  lazy val definedIdentifiers: Seq[String] = questions.map(_.identifier)

  lazy val identifiersWithType: Seq[(String, Type)] = questions.map(q => (q.identifier, q.`type`))

  lazy val referencedIdentifiers: Set[String] = expressions.map { case (e, _) => extractIdentifiers(e) }.reduce(_ ++ _)

  lazy val questionsWithReferences: Map[String, Set[String]] = questionsWithShowConditions.map {
    case (Question(identifier, _, _, None), conditionals) => (identifier, extractIdentifiers(conditionals))
    case (Question(identifier, _, _, Some(expr)), conditionals) => (identifier, extractIdentifiers(expr) ++ extractIdentifiers(conditionals))
  }.toMap

  private def extractExpressions(parentNode: FormNode): Seq[(ExpressionNode, Type)] = parentNode match {
    case Question(_, _, questionType, Some(expr)) => Seq((expr, questionType))
    case Conditional(expr, block) => (expr, Boolean) +: extractExpressions(block)
    case Block(statements) => statements.flatMap(e => extractExpressions(e))
    case Form(_, block) => extractExpressions(block)
    case _ => Nil
  }

  private def extractIdentifiers(expressionNodes: Seq[ExpressionNode]): Set[String] = expressionNodes match {
    case Nil => Set.empty
    case e :: es => extractIdentifiers(e) ++ extractIdentifiers(es)
  }

  private def extractIdentifiers(expressionNode: ExpressionNode): Set[String] = expressionNode match {
    case Identifier(value) => Set(value)
    case i: InfixNode => extractIdentifiers(i.lhs) ++ extractIdentifiers(i.rhs)
    case p: PrefixNode => extractIdentifiers(p.rhs)
    case _ => Set.empty
  }

  private def flattenForm(statement: Statement, conditionals: Seq[ExpressionNode]): Seq[(ast.Question, Seq[ExpressionNode])] = statement match {
    case Conditional(condition, Block(statements)) => statements.flatMap(flattenForm(_, condition +: conditionals))
    case q: ast.Question => Seq((q, conditionals))
  }
}