package view.style

import ast._

import scalafx.scene.paint.Color
import scalafx.scene.text.Font

class DisplayStyle(questionStyle: Option[QuestionStyle]) {
  val widget: Option[Widget] = questionStyle match {
    case Some(QuestionStyle(_, _, w)) => w
    case None => None
  }

  val width: Double = extractStyle({ case Width(w) => w.toDouble }).getOrElse(100.0)

  val fill: Color = extractStyle({ case ast.Color(c) => c })
    .map(color => scalafx.scene.paint.Color.web(color))
    .getOrElse(scalafx.scene.paint.Color.Black)

  val font: Font = {
    val font = extractStyle({ case ast.Font(f) => f })
    val size = extractStyle({ case FontSize(s) => s })

    (font, size) match {
      case (Some(f), Some(s)) => Font.font(f, s.toDouble)
      case (Some(f), None) => Font.font(f)
      case (None, Some(s)) => Font.font(s.toDouble)
      case (None, None) => new Font(Font.default)
    }
  }

  private def extractStyle[T](pf: PartialFunction[Style, T]): Option[T] = questionStyle match {
    case Some(QuestionStyle(_, styling, _)) => styling.values.collect(pf).lastOption
    case None => None
  }
}
