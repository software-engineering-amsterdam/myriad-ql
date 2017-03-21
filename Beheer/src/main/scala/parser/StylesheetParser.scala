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
    "stylesheet" ~> ident ~ curlyBrackets(rep(page)) ^^ {
      case identifier ~ pages => Stylesheet(identifier, pages)
    }

  private def page: Parser[Page] =
    "page" ~> ident ~ curlyBrackets(rep(section) ~ rep(default)) ^^ {
      case identifier ~ (sections ~ default) => Page(identifier, sections, default)
    }

  private def section: Parser[Section] = singleBlockSection | multiBlockSection

  private def singleBlockSection: Parser[Section] =
    "section" ~> quotedString ~ (question | section) ^^ {
      case label ~ block => Section(label, Seq(block), Nil)
    }

  private def multiBlockSection: Parser[Section] =
    "section" ~> quotedString ~ curlyBrackets(rep(question | section) ~ rep(default)) ^^ {
      case label ~ (blocks ~ default) => Section(label, blocks, default)
    }

  private def question: Parser[QuestionStyle] =
    "question" ~> ident ~ opt(curlyBrackets(rep(style) ~ opt(widget))) ^^ {
      case identifier ~ Some(styling ~ widget) => QuestionStyle(identifier, styling.toMap, widget)
      case identifier ~ None => QuestionStyle(identifier, Map.empty, None)
    }

  private def default: Parser[DefaultStyle] =
    "default" ~> typeName ~ curlyBrackets(rep(style) ~ opt(widget)) ^^ {
      case typeName ~ (styling ~ widget) => DefaultStyle(typeName, styling.toMap, widget)
    }

  private def widget: Parser[Widget] = "widget" ~> (
    "checkbox" ^^^ Checkbox
    | "spinbox" ^^^ Spinbox
    | "datepicker" ^^^ DatePicker
    | "textfield" ^^^ Textfield
    | radio
    | dropdown
    | slider
  )

  private def radio: Parser[Radio] =
    "radio" ~> parentheses(quotedString ~ "," ~ quotedString) ^^ {
      case trueText ~ _ ~ falseText => Radio(trueText, falseText)
    }

  private def dropdown: Parser[Dropdown] =
    "dropdown" ~> parentheses(quotedString ~ "," ~ quotedString) ^^ {
      case trueText ~ _ ~ falseText => Dropdown(trueText, falseText)
    }

  private def slider: Parser[Slider] =
    "slider" ~> parentheses(number ~ "," ~ number) ^^ {
      case min ~ _ ~ max => Slider(min, max)
    }

  private def style: Parser[(String, Style)] = (
    width
    | font
    | fontSize
    | color
  )

  private def width: Parser[(String, Width)] = "width" ~ ":" ~ number ^^ {
    case width ~ _ ~ number => width -> Width(number)
  }

  private def font: Parser[(String, Font)] = "font" ~ ":" ~ quotedString ^^ {
    case font ~ _ ~ string => font -> Font(string)
  }

  private def fontSize: Parser[(String, FontSize)] = "fontsize" ~ ":" ~ number ^^ {
    case fontSize ~ _ ~ number => fontSize -> FontSize(number)
  }

  private def color: Parser[(String, Color)] = "color" ~ ":" ~ """#\p{XDigit}{6}""".r ^^ {
    case color ~ _ ~ hex => color -> Color(hex)
  }
}

object StylesheetParser {
  def apply(input: String): Stylesheet = apply(new StringReader(input))

  def apply(input: Reader): Stylesheet = new StylesheetParser().parse(input)
}
