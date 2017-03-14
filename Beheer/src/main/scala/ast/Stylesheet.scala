package ast

import ast.Stylesheet._

case class Stylesheet(identifier: String, pages: Pages)

case class Page(identifier: String, sections: Sections, defaults: DefaultStyles)

sealed trait Block

case class Section(label: String, blocks: Blocks, defaults: DefaultStyles) extends Block

case class QuestionStyle(identifier: String, styling: Styling, widget: Option[Widget]) extends Block

case class DefaultStyle(typeName: Type, styling: Styling, widget: Option[Widget])

case class Widget(widgetType: WidgetType)

sealed trait WidgetType

case object Checkbox extends WidgetType

case object Spinbox extends WidgetType

case object DatePicker extends WidgetType

case object Textfield extends WidgetType

case class Radio(trueText: String, falseText: String) extends WidgetType

case class Dropdown(trueText: String, falseText: String) extends WidgetType

case class Slider(min: BigDecimal, max: BigDecimal) extends WidgetType

sealed trait Style

case class NumericStyle(value: BigDecimal) extends Style

case class ColorStyle(value: String) extends Style

case class StringStyle(value: String) extends Style

object Stylesheet {
  type Blocks = Seq[Block]
  type Styling = Map[String, Style]
  type DefaultStyles = Seq[DefaultStyle]
  type QuestionStyles = Seq[QuestionStyle]
  type Sections = Seq[Section]
  type Pages = Seq[Page]
}