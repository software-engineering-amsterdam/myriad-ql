package model

import ast.{Block, QuestionStyle, Section, Stylesheet}

class StyleModel(stylesheet: Stylesheet) {
  val questionStyles: Seq[QuestionStyle] = extractQuestionStyles(stylesheet.pages.flatMap(_.sections))
  val referencedQuestions: Seq[String] = questionStyles.map(_.identifier)

  private def extractQuestionStyles(blocks: Seq[Block]): Seq[QuestionStyle] =
    blocks.flatMap(b => extractQuestionStyles(b))

  private def extractQuestionStyles(block: Block): Seq[QuestionStyle] = block match {
    case Section(_, blocks, _) => extractQuestionStyles(blocks)
    case q: QuestionStyle => Seq(q)
  }

}
