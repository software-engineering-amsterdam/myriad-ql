package parser

import java.io.{ Reader, StringReader }

import ast._

class StylesheetParser extends QLParser {

  def parse(input: Reader): Stylesheet = {
    parseAll(stylesheet, input) match {
      case Success(result, _) => result
      case failure: NoSuccess => scala.sys.error(failure.msg)
    }
  }

  def stylesheet: Parser[Stylesheet] =
    "stylesheet" ~> ident ~ block(rep(page)) ^^ {
      case identifier ~ pages => Stylesheet(identifier, pages)
    }

  private def page: Parser[Page] =
    "page" ~> ident ~ block(rep(section) ~ rep(default)) ^^ {
      case identifier ~ (sections ~ default) => Page(identifier, sections, default)
    }

  private def section: Parser[Section] =
    "section" ~> label ~ block(rep(question) ~ rep(default)) ^^ {
      case label ~ (questions ~ default) => Section(label, questions, default)
    }

  private def question: Parser[QuestionStyle] =
    "question" ~> ident ~ opt(block(rep(style) ~ opt(widget))) ^^ {
      case identifier ~ Some(styling ~ widget) => QuestionStyle(identifier, styling.toMap, widget)
      case identifier ~ None => QuestionStyle(identifier, Map.empty, None)
    }

  private def default: Parser[DefaultStyle] =
    "default" ~> typeName ~ block(rep(style) ~ opt(widget)) ^^ {
      case typeName ~ (styling ~ widget) => DefaultStyle(typeName, styling.toMap, widget)
    }

  private def widget: Parser[Widget] =
    "widget" ~> widgetType ^^ (widgetType => Widget(widgetType))

  private def widgetType: Parser[WidgetType] = (
    "checkbox" ^^^ Checkbox
    | "spinbox" ^^^ Spinbox
    | "datepicker" ^^^ DatePicker
    | radio
    | dropdown
  )

  private def radio: Parser[Radio] =
    "radio" ~> parentheses(label ~ "," ~ label) ^^ {
      case trueText ~ _ ~ falseText => Radio(trueText, falseText)
    }

  private def dropdown: Parser[Dropdown] = "dropdown" ~> parentheses(repsep(label, ",")) ^^ (elements => Dropdown(elements))

  private def style: Parser[(String, Style)] =
    ident ~ ":" ~ (colorStyle | numericStyle | stringStyle) ^^ {
      case identifier ~ _ ~ style => identifier -> style
    }

  private def stringStyle: Parser[StringStyle] = label ^^ (s => StringStyle(s))

  private def colorStyle: Parser[ColorStyle] = """#\p{XDigit}{6}""".r ^^ (c => ColorStyle(c))

  private def numericStyle: Parser[NumericStyle] = wholeNumber ^^ (n => NumericStyle(BigDecimal(n).setScale(0)))

}

object StylesheetParser {
  def apply(input: String): Stylesheet = apply(new StringReader(input))

  def apply(input: Reader): Stylesheet = (new StylesheetParser).parse(input)
}