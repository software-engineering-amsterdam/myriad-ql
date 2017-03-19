package model

import ast.Stylesheet._
import ast._

import scala.annotation.tailrec

class StyleModel(stylesheet: Stylesheet, questionWithTypes: Map[String, Type]) {
  val defaultStyles: DefaultStyles = stylesheet.pages.flatMap(extractDefaultStyles)
  val questionStyles: QuestionStyles = extractQuestionStyles(stylesheet.pages.flatMap(_.sections))
  val referencedQuestions: Seq[String] = questionStyles.map(_.identifier)
  val processedStylesheet: Stylesheet = stylesheet.copy(pages = stylesheet.pages.map(p => flattenStyles(p)))

  private def flattenStyles(page: Page): Page =
    page.copy(sections = page.sections.map(s => flattenStyles(s, mergeDefaultStyles(page.defaults, Map.empty))))

  private def flattenStyles(section: Section, defaultStyles: Map[Type, DefaultStyle]): Section =
    section.copy(blocks = section.blocks.map(b => flattenStyles(b, mergeDefaultStyles(section.defaults, defaultStyles))))

  private def flattenStyles(block: Block, defaultStyles: Map[Type, DefaultStyle]): Block = block match {
    case s: Section => flattenStyles(s, defaultStyles)
    case q: QuestionStyle => flattenStyles(q, defaultStyles)
  }

  private def flattenStyles(questionStyle: QuestionStyle, defaultStyles: Map[Type, DefaultStyle]): QuestionStyle = {
    val questionType = questionWithTypes.get(questionStyle.identifier) match {
      case Some(qt) => qt
      case None => sys.error("Unable to determine question type for styling.")
    }
    defaultStyles.get(questionType) match {
      case None => questionStyle
      case Some(DefaultStyle(t, defaultStyling, defaultWidget)) if t == questionType =>
        val newStyling = defaultStyling ++ questionStyle.styling
        val newWidget = updateWidget(questionStyle.widget, defaultWidget)
        questionStyle.copy(styling = newStyling, widget = newWidget)
      case _ => sys.error("Type mismatch during flattening of style hierarchy")
    }
  }

  @tailrec
  private def mergeDefaultStyles(newStyles: DefaultStyles, knownStyles: Map[Type, DefaultStyle]): Map[Type, DefaultStyle] = newStyles match {
    case Nil => knownStyles
    case (d @ DefaultStyle(newType, newStyling, newWidget)) :: tail =>
      val mergedStyle = knownStyles.get(newType) match {
        case None => d
        case Some(DefaultStyle(knownType, knownStyling, knownWidget)) if newType == knownType =>
          DefaultStyle(newType, knownStyling ++ newStyling, updateWidget(newWidget, knownWidget))
        case _ => sys.error("Type mismatch during flattening of style hierarchy")
      }
      mergeDefaultStyles(tail, knownStyles + (newType -> mergedStyle))
  }

  private def updateWidget(newW: Option[Widget], oldW: Option[Widget]): Option[Widget] = newW match {
    case None => oldW
    case Some(w) => Some(w)
  }

  private def extractDefaultStyles(page: Page): DefaultStyles =
    page.defaults ++ extractDefaultStyles(page.sections)

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
