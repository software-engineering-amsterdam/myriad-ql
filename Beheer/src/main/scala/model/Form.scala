package model

import parser.ast.{ ExpressionNode, TypeDeclaration }

case class Form(questions: Seq[Question])

case class Question(identifier: String, label: String, show: Seq[ExpressionNode], `type`: TypeDeclaration)
