from pyparsing import *
from qls.ast import *
from ql.datatypes import *
from gui.widgets import *

ParserElement.enablePackrat()

identifier = Word(alphas)
identifier.addCondition(lambda tokens: tokens[0] not in "stylesheet page "
                        "section question default widget ".split())

integer_datatype = Literal("integer").setParseAction(lambda _: IntegerDatatype())
decimal_datatype = Literal("decimal").setParseAction(lambda _: DecimalDatatype())
boolean_datatype = Literal("boolean").setParseAction(lambda _: BooleanDatatype())
string_datatype = Literal("string").setParseAction(lambda _: StringDatatype())
datatype = integer_datatype ^ decimal_datatype ^ boolean_datatype ^ string_datatype

""" pyparsing_common.integer does not support negative integers. """
integer = Regex("-?[0-9]+")
integer.addParseAction(lambda tokens: int(tokens[0]))

integer_arguments = Suppress("(") + integer + Suppress(",") + \
                    integer + Suppress(")")

string = QuotedString("\"")

string_arguments = Suppress("(") + string + Suppress(",") + string +\
                   Suppress(")")

widget_text = Suppress("text").setParseAction(
    lambda _: EntryWidget)

widget_whole_number = Suppress("whole-number").setParseAction(
    lambda _: IntegerEntryWidget)

widget_real_number = Suppress("real-number").setParseAction(
    lambda _: DecimalEntryWidget)

widget_checkbox = Suppress("checkbox").setParseAction(
    lambda _: CheckBoxWidget)

widget_spinbox = Suppress("spinbox") + Optional(integer_arguments)
widget_spinbox.setParseAction(
    lambda tokens: lambda question, app: SpinBoxWidget(question, app, *tokens))

widget_slider = Suppress("slider") + Optional(integer_arguments)
widget_slider.setParseAction(
    lambda tokens: lambda question, app: SliderWidget(question, app, *tokens))

widget_radio = Suppress("radio") + Optional(string_arguments)
widget_radio.setParseAction(
    lambda tokens: lambda question, app: RadioWidget(question, app, *tokens))

widget_drop_down = Suppress("dropdown") + Optional(string_arguments)
widget_drop_down.setParseAction(
    lambda tokens: lambda question, app: DropDownWidget(question, app, *tokens))

widget_type = widget_checkbox ^ widget_spinbox ^ widget_radio ^ widget_text ^\
              widget_whole_number ^ widget_real_number ^ widget_drop_down ^\
              widget_slider

widget_attribute = Suppress("widget") + widget_type
widget_attribute.setParseAction(lambda tokens: WidgetTypeAttribute(*tokens))

hexadecimal = Regex("#[0-9a-f]{6}")

color_attribute = Suppress("color") + Suppress(":") + hexadecimal
color_attribute.setParseAction(lambda tokens: ColorAttribute(*tokens))

font_size_attribute = Suppress("size") + Suppress(":") + integer
font_size_attribute.setParseAction(lambda tokens: FontSizeAttribute(*tokens))

weight = Literal("normal") ^ Literal("bold")

font_weight_attribute = Suppress("weight") + Suppress(":") + weight
font_weight_attribute.setParseAction(
    lambda tokens: FontWeightAttribute(*tokens))

font_family_attribute = Suppress("family") + Suppress(":") + string
font_family_attribute.setParseAction(
    lambda tokens: FontFamilyAttribute(*tokens))

width_attribute = Suppress("width") + Suppress(":") + integer
width_attribute.setParseAction(lambda tokens: WidthAttribute(*tokens))

attribute = widget_attribute ^ color_attribute ^ font_size_attribute ^\
            font_weight_attribute ^ font_family_attribute ^ width_attribute

attributes = Suppress("{") + ZeroOrMore(attribute) + Suppress("}")
attributes.setParseAction(lambda tokens: [tokens.asList()])

widget_styling = widget_attribute.copy()
widget_styling.addParseAction(lambda tokens: [tokens.asList()])

styling = widget_styling ^ attributes

unstyled_question = Suppress("question") + identifier
unstyled_question.setParseAction(lambda tokens: QuestionAnchor(*tokens))

styled_question = Suppress("question") + identifier + styling
styled_question.setParseAction(lambda tokens: StyledQuestionAnchor(*tokens))

question = unstyled_question ^ styled_question

default = Suppress("default") + datatype + styling
default.setParseAction(lambda tokens: DefaultStyling(*tokens))

defaults = OneOrMore(default)
defaults.setParseAction(lambda tokens: [tokens.asList()])

sectionbody = Forward()

unstyled_section = Suppress("section") + QuotedString("\"") + Suppress("{") +\
          sectionbody + Suppress("}")
unstyled_section.setParseAction(lambda tokens: Section(*tokens))

styled_section = Suppress("section") + QuotedString("\"") + Suppress("{") +\
          sectionbody + defaults + Suppress("}")
styled_section.setParseAction(lambda tokens: StyledSection(*tokens))

section = unstyled_section ^ styled_section

sectionbody <<= ZeroOrMore(section ^ question)
sectionbody.setParseAction(lambda tokens: [tokens.asList()])

pagebody = sectionbody

unstyled_page = Suppress("page") + identifier + Suppress("{") + pagebody +\
                Suppress("}")
unstyled_page.setParseAction(lambda tokens: Page(*tokens))

styled_page = Suppress("page") + identifier + Suppress("{") + pagebody +\
              defaults + Suppress("}")
styled_page.setParseAction(lambda tokens: StyledPage(*tokens))

page = unstyled_page ^ styled_page

layoutbody = ZeroOrMore(page)
layoutbody.setParseAction(lambda tokens: [tokens.asList()])

unstyled_layout = Suppress("stylesheet") + identifier + Suppress("{") +\
                  layoutbody + Suppress("}")
unstyled_layout.setParseAction(lambda tokens: Layout(*tokens))

styled_layout = Suppress("stylesheet") + identifier + Suppress("{") +\
                layoutbody + defaults + Suppress("}")
styled_layout.setParseAction(lambda tokens: StyledLayout(*tokens))

layout = unstyled_layout ^ styled_layout


def parse_file(filename):
    return layout.parseFile(filename)[0]


def parse_string(string):
    return layout.parseString(string, parseAll=True)[0]
