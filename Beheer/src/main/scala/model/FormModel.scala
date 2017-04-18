package model

import ast.Form.Statements
import ast._
import model.DisplayQuestion.{ DisplayConditions, DisplayQuestions }

class FormModel(form: Form) {
  val questionsWithDisplayConditions: Seq[(Question, DisplayConditions)] = form.statements.flatMap(flattenForm(_, Nil))
  val expressions: Seq[(ExpressionNode, Type)] = extractExpressions(form.statements)
  val questions: Seq[Question] = questionsWithDisplayConditions.map { case (q, _) => q }
  val questionLabels: Seq[String] = questions.map(_.label)
  val definedIdentifiers: Seq[String] = questions.map(_.identifier)
  val identifiersWithType: Seq[(String, Type)] = questions.map(q => (q.identifier, q.`type`))

  val referencedIdentifiers: Set[String] = expressions.map {
    case (e, _) => extractIdentifiers(e)
  }.reduceOption(_ ++ _).getOrElse(Set.empty)

  val displayQuestions: DisplayQuestions = questionsWithDisplayConditions.map {
    case (Question(identifier, label, questionType, None), conditions) =>
      OpenQuestion(identifier, label, questionType, conditions)
    case (Question(identifier, label, questionType, Some(expr)), conditions) =>
      ComputedQuestion(identifier, label, questionType, conditions, expr)
  }

  val questionsWithReferences: Map[String, Set[String]] = questionsWithDisplayConditions.map {
    case (Question(identifier, _, _, None), conditions) =>
      (identifier, extractIdentifiers(conditions.map(_.condition)))
    case (Question(identifier, _, _, Some(expr)), conditions) =>
      (identifier, extractIdentifiers(expr) ++ extractIdentifiers(conditions.map(_.condition)))
  }.toMap

  private def extractExpressions(statements: Statements): Seq[(ExpressionNode, Type)] =
    statements.flatMap(e => extractExpressions(e))

  private def extractExpressions(statement: Statement): Seq[(ExpressionNode, Type)] = statement match {
    case Conditional(condition, ifStatements, elseStatements) =>
      (condition, BooleanType) +: (extractExpressions(ifStatements) ++ extractExpressions(elseStatements))
    case Question(_, _, questionType, Some(expr)) => Seq((expr, questionType))
    case _ => Nil
  }

  private def extractIdentifiers(expressionNodes: Seq[ExpressionNode]): Set[String] =
    expressionNodes.flatMap(e => extractIdentifiers(e)).toSet

  private def extractIdentifiers(expressionNode: ExpressionNode): Set[String] = expressionNode match {
    case Identifier(value) => Set(value)
    case i: InfixNode => extractIdentifiers(i.lhs) ++ extractIdentifiers(i.rhs)
    case p: PrefixNode => extractIdentifiers(p.operand)
    case _ => Set.empty
  }

  private def flattenForm(statement: Statement, conditionals: DisplayConditions): Seq[(Question, DisplayConditions)] = statement match {
    case Conditional(condition, ifStatements, elseStatements) =>
      val first = ifStatements.flatMap(flattenForm(_, DisplayCondition(condition, isElseCondition = false) +: conditionals))
      val second = elseStatements.flatMap(flattenForm(_, DisplayCondition(condition, isElseCondition = true) +: conditionals))
      first ++ second
    case q: Question => Seq((q, conditionals))
  }
}