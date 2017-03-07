package checker

import ast._
import checker.Issue.Issues
import model.StyleModel

class StyleChecker(style: StyleModel, definedQuestionsWithType: Map[String, Type]) {

  private val doubleReferences: Issues =
    (style.referencedQuestions diff style.referencedQuestions.distinct).map(q => Error(s"Multiple references in style to question: $q"))
  private val undefinedQuestions: Issues =
    (style.referencedQuestions.toSet -- definedQuestionsWithType.keySet).map(q => Error(s"Style references undefined question: $q")).toSeq
  private val unreferencedQuestions: Issues =
    (definedQuestionsWithType.keySet -- style.referencedQuestions.toSet).map(q => Warning(s"Question not placed in stylesheet: $q")).toSeq
  private val incompatibleStyles: Issues = Nil

  def check: Issues = doubleReferences ++ undefinedQuestions ++ incompatibleStyles ++ unreferencedQuestions

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

}
