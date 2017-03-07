package model

import ast._

class StyleModel(stylesheet: Stylesheet) {
  val defaultStyles: Seq[DefaultStyle] = stylesheet.pages.flatMap(extractDefaultStyles)
  val questionStyles: Seq[QuestionStyle] = extractQuestionStyles(stylesheet.pages.flatMap(_.sections))
  val referencedQuestions: Seq[String] = questionStyles.map(_.identifier)

  private def extractDefaultStyles(page: Page): Seq[DefaultStyle] = page match {
    case Page(_, sections, default) => default ++ extractDefaultStyles(sections)
  }

  private def extractDefaultStyles(blocks: Seq[Block]): Seq[DefaultStyle] =
    blocks.flatMap(b => extractDefaultStyles(b))

  private def extractDefaultStyles(block: Block): Seq[DefaultStyle] = block match {
    case Section(_, sections, default) => default ++ extractDefaultStyles(sections)
    case _: QuestionStyle => Nil
  }

  private def extractQuestionStyles(blocks: Seq[Block]): Seq[QuestionStyle] =
    blocks.flatMap(b => extractQuestionStyles(b))

  private def extractQuestionStyles(block: Block): Seq[QuestionStyle] = block match {
    case Section(_, blocks, _) => extractQuestionStyles(blocks)
    case q: QuestionStyle => Seq(q)
  }
}
