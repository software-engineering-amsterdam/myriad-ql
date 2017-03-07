package checker

import ast._
import checker.Issue.Issues
import model.StyleModel

class StyleChecker(model: StyleModel, definedQuestionsWithType: Map[String, Type]) {

  private val doubleReferences: Issues =
    (model.referencedQuestions diff model.referencedQuestions.distinct).map(q => Error(s"Multiple references in style to question: $q"))
  private val undefinedQuestions: Issues =
    (model.referencedQuestions.toSet -- definedQuestionsWithType.keySet).map(q => Error(s"Style references undefined question: $q")).toSeq
  private val unreferencedQuestions: Issues =
    (definedQuestionsWithType.keySet -- model.referencedQuestions.toSet).map(q => Error(s"Question not placed in stylesheet: $q")).toSeq
  private val incompatibleStyles: Issues =
    model.questionStyles.flatMap(q => checkWidgetType(q)) ++ model.defaultStyles.flatMap(d => checkWidgetType(d))

  def check: Issues = doubleReferences ++ undefinedQuestions ++ incompatibleStyles ++ unreferencedQuestions

  private def checkWidgetType(defaultStyle: DefaultStyle): Option[Error] = defaultStyle match {
    case DefaultStyle(_, _, None) => None
    case DefaultStyle(questionType, _, Some(widget)) => checkWidgetType(widget.widgetType, questionType)
  }

  private def checkWidgetType(widgetType: WidgetType, questionType: Type): Option[Error] =
    (widgetType, questionType) match {
      case (Textfield, _: NumericType) => None
      case (Textfield, StringType) => None
      case (Textfield, DateType) => None
      case (DatePicker, DateType) => None
      case (Checkbox, BooleanType) => None
      case (Spinbox, _: NumericType) => None
      case (_: Dropdown, BooleanType) => None
      case (_: Slider, _: NumericType) => None
      case (_: Radio, BooleanType) => None
      case (w, q) => Some(Error(s"Invalid widget $w for type $q"))
    }

  private def checkWidgetType(questionStyle: QuestionStyle): Option[Error] = questionStyle match {
    case QuestionStyle(_, _, None) => None
    case QuestionStyle(ident, _, Some(widget)) =>
      definedQuestionsWithType.get(ident) match {
        case Some(qt) => checkWidgetType(widget.widgetType, qt)
        case None => Some(Error(s"Unable to determine type of $ident for style type checking."))
      }
  }
}

object StyleChecker {
  def apply(model: StyleModel, definedQuestionsWithType: Map[String, Type]): Issues =
    new StyleChecker(model, definedQuestionsWithType).check
}