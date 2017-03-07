package model

import ast.Stylesheet.{ Blocks, DefaultStyles, QuestionStyles }
import ast._

class StyleModel(stylesheet: Stylesheet) {
  val defaultStyles: DefaultStyles = stylesheet.pages.flatMap(extractDefaultStyles)
  val questionStyles: QuestionStyles = extractQuestionStyles(stylesheet.pages.flatMap(_.sections))
  val referencedQuestions: Seq[String] = questionStyles.map(_.identifier)

  //TODO: Flatten hierarchy into list of 'blocks' with associated style & widget.

  private def extractDefaultStyles(page: Page): DefaultStyles = page match {
    case Page(_, sections, default) => default ++ extractDefaultStyles(sections)
  }

  private def extractDefaultStyles(blocks: Blocks): DefaultStyles =
    blocks.flatMap(b => extractDefaultStyles(b))

  private def extractDefaultStyles(block: Block): DefaultStyles = block match {
    case Section(_, sections, default) => default ++ extractDefaultStyles(sections)
    case _: QuestionStyle => Nil
  }

  private def extractQuestionStyles(blocks: Blocks): QuestionStyles =
    blocks.flatMap(b => extractQuestionStyles(b))

  private def extractQuestionStyles(block: Block): QuestionStyles = block match {
    case Section(_, blocks, _) => extractQuestionStyles(blocks)
    case q: QuestionStyle => Seq(q)
  }
}
