package model

import parser.AstFacts

class FormModel(db: AstFacts) {
  lazy val environment: Map[String, Any] = Map()
  lazy val form: Form = Form(db.questionsWithShowConditions.map{ case (q, conditions) => createModelQuestion(q, conditions)})

  private def createModelQuestion(question: parser.ast.Question, showCondition: Seq[parser.ast.ExpressionNode]): Question = question match {
    case parser.ast.Question(identifier, label, questionType, None) =>
      OpenQuestion(identifier, label, questionType, createShowConditions(showCondition))

    case parser.ast.Question(identifier, label, questionType, Some(expression)) =>
      ComputedQuestion(identifier, label, questionType, createShowConditions(showCondition), createModelExpression(expression)
  }


  private def createShowConditions(expressions: Seq[parser.ast.ExpressionNode]): Seq[BooleanValue] =
    expressions.map(e => createModelExpression(e) match {
      case e: BooleanValue => e
      case _ => sys.error("Non boolean expression in show condition.")
    })

  private def createModelExpression(expression: parser.ast.ExpressionNode): Expression = expression match {

  }
}

object FormModel {
  def apply(db: AstFacts) = new FormModel(db)
}