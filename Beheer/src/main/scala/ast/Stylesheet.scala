package ast

import ast.Stylesheet._

case class Stylesheet(identifier: String, pages: Pages)

case class Page(identifier: String, sections: Sections, defaults: DefaultStyles)

sealed trait Block

case class Section(label: String, blocks: Blocks, defaults: DefaultStyles) extends Block

case class QuestionStyle(identifier: String, styling: Styling, widget: Option[Widget]) extends Block

case class DefaultStyle(typeName: Type, styling: Styling, widget: Option[Widget])

sealed trait Widget

case object Checkbox extends Widget

case object Spinbox extends Widget

case object DatePicker extends Widget

case object Textfield extends Widget

case class Radio(trueText: String, falseText: String) extends Widget

case class Dropdown(trueText: String, falseText: String) extends Widget

case class Slider(min: BigDecimal, max: BigDecimal) extends Widget

sealed trait Style

case class Width(value: BigDecimal) extends Style

case class Font(value: String) extends Style

case class FontSize(value: BigDecimal) extends Style

case class Color(value: String) extends Style

object Stylesheet {
  type Blocks = Seq[Block]
  type Styling = Map[String, Style]
  type DefaultStyles = Seq[DefaultStyle]
  type QuestionStyles = Seq[QuestionStyle]
  type Sections = Seq[Section]
  type Pages = Seq[Page]
}