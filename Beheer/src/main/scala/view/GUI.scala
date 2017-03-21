package view

import ast._
import checker.Issue.Issues
import checker.{ Error, Warning }
import model.{ DisplayCondition, DisplayQuestion }
import values.{ Evaluator, Value }
import view.questions.{ BooleanQuestion, DateQuestion, NumericQuestion, StringQuestion }

import scalafx.application.JFXApp
import scalafx.beans.binding.{ Bindings, BooleanBinding, ObjectBinding }
import scalafx.collections.ObservableMap
import scalafx.geometry.Insets
import scalafx.scene.layout.{ TilePane, VBox }
import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.scene.{ Node, Scene }

trait GUI extends JFXApp.PrimaryStage {
  val issues: Issues
  val env: ObservableMap[String, Value] = ObservableMap()

  def displayBoxes: Seq[Node]

  protected def renderQuestion(question: DisplayQuestion, style: Option[QuestionStyle] = None): VBox = {
    val questionEnvUpdater = updateEnv(question.identifier)(_)
    val questionDisplay = question.`type` match {
      case BooleanType => new BooleanQuestion(question, style, questionEnvUpdater)
      case DateType => new DateQuestion(question, style, questionEnvUpdater)
      case StringType => new StringQuestion(question, style, questionEnvUpdater)
      case _: NumericType => new NumericQuestion(question, style, questionEnvUpdater)
    }
    questionDisplay.subscribeToValueBinding(valueBindingGenerator)
    questionDisplay.displayBox.visible <== visibilityBinding(question.displayConditions)
    questionDisplay.displayBox
  }

  def updateEnv(identifier: String)(value: Value) = {
    env += (identifier -> value)
    println(env.toMap)
  }

  private def valueBindingGenerator: ExpressionNode => ObjectBinding[Value] =
    (valueExpression: ExpressionNode) =>
      Bindings.createObjectBinding[Value](() => Evaluator(env.toMap).calculate(valueExpression), env)

  private def visibilityBinding(displayConditions: Seq[DisplayCondition]): BooleanBinding =
    Bindings.createBooleanBinding(() => Evaluator(env.toMap).display(displayConditions), env)

  private def issueBox = issues.map {
    case Error(message) =>
      new Text {
        text = s"[Error] $message"
        fill = Color.Red
      }
    case Warning(message) =>
      new Text {
        text = s"[Warning] $message"
        fill = Color.Yellow
      }
  }

  title.value = "Beheer QL Form"
  width = 600
  height = 800
  scene = new Scene {
    content = new TilePane {
      hgap = 10
      vgap = 10
      padding = Insets(10)
      prefColumns = 1
      children = issueBox ++ displayBoxes
    }
  }
}