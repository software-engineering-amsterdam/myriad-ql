package ast

case class Stylesheet(identifier: String, pages: Seq[Page])

case class Page(identifier: String, sections: Seq[Section], defaults: Seq[DefaultStyle])

sealed trait Block

case class Section(label: String, blocks: Seq[Block], defaults: Seq[DefaultStyle]) extends Block

case class QuestionStyle(identifier: String, styling: Map[String, Style], widget: Option[Widget]) extends Block

case class DefaultStyle(typeName: Type, styling: Map[String, Style], widget: Option[Widget])

case class Widget(widgetType: WidgetType)

sealed trait WidgetType

case object Checkbox extends WidgetType

case object Spinbox extends WidgetType

case object DatePicker extends WidgetType

case class Radio(trueText: String, falseText: String) extends WidgetType

case class Dropdown(elements: Seq[String]) extends WidgetType

sealed trait Style

case class NumericStyle(value: BigDecimal) extends Style

case class ColorStyle(value: String) extends Style

case class StringStyle(value: String) extends Style