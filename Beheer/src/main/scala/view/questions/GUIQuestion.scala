package view.questions

import ast.{ ExpressionNode, QuestionStyle, Widget }
import model.{ ComputedQuestion, DisplayQuestion, OpenQuestion }
import values.Value
import view.style.DisplayStyle
import view.widgets.QLWidget

import scalafx.beans.binding.{ ObjectBinding }
import scalafx.scene.layout.VBox
import scalafx.scene.text.Text

trait GUIQuestion {
  //Depends on potentially child-class overridden val, therefore must be lazy to delay instantiation.
  lazy val displayBox = new VBox {
    children = Seq(label, widget.displayNode)
    disable = isDisabled(question)
  }
  protected val question: DisplayQuestion
  protected val updateEnv: Value => Unit
  protected val questionStyle: Option[QuestionStyle]
  protected val widget: QLWidget
  protected val widgetStyle: Option[Widget] = questionStyle.flatMap(_.widget)
  protected val displayStyle: DisplayStyle = new DisplayStyle(questionStyle)

  protected val widgetChangeHandler: Option[Value => Unit] = question match {
    case _: OpenQuestion => Some(updateEnv)
    case _: ComputedQuestion => None
  }

  def subscribeToValueBinding(bindingGenerator: ExpressionNode => ObjectBinding[Value]): Unit = question match {
    case ComputedQuestion(_, _, _, _, valueExpression) => bindingGenerator(valueExpression).onChange {
      (newValue, _, _) =>
        {
          widget.setValue(newValue.value)
          updateEnv(newValue.value)
        }
    }
    case _: OpenQuestion => ()
  }

  private def label: Text = new Text {
    text = question.label
    font = displayStyle.font
    fill = displayStyle.fill
  }

  private def isDisabled(question: DisplayQuestion): Boolean = question match {
    case _: OpenQuestion => false
    case _: ComputedQuestion => true
  }

  //private def isVisible(question: DisplayQuestion): BooleanBinding =
  //  Bindings.createBooleanBinding(() => Evaluator(env.toMap).display(question.displayConditions), env)
}
