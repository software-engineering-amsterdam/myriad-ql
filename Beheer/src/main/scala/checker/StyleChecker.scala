package checker

import ast.Type
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

}
