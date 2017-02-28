from pyparsing import *
from qls.ast import *
from ql.ast import Datatype
from gui.widgets import *

ParserElement.enablePackrat()

identifier = Word(alphas)
identifier.addCondition(lambda tokens: tokens[0] not in "stylesheet page "
                        "section question default widget ".split())

datatype = oneOf("boolean string integer decimal")
datatype.setParseAction(lambda tokens: Datatype[tokens[0]])

widgettype = Literal("spinbox") ^ Literal("checkbox") ^ Literal("radio") ^ Literal("entry")

# TODO: implement non-default values for radio, drop_down and slider
# wrap constructor in lambda function to pass parameters to GUI
widget_checkbox = Literal("checkbox").setParseAction(lambda _: CheckBoxWidget)
widget_spinbox = Literal("spinbox").setParseAction(lambda _: SpinBoxWidget)
widget_radio = Literal("radio").setParseAction(lambda _: RadioWidget)
widget_entry = Literal("text").setParseAction(lambda _: EntryWidget)
widget_drop_down = Literal("dropdown").setParseAction(lambda _: DropDownWidget)
widget_slider = Literal("slider").setParseAction(lambda _: SliderWidget)

widget = Suppress("widget") + widgettype

name = oneOf("width height font color fontsize fontcolor")

hexadecimal = Regex("#[0-9a-f]{6}")
literal = pyparsing_common.integer ^ pyparsing_common.real ^ QuotedString("\"") ^ hexadecimal

attribute = widget ^ (name + Suppress(":") + literal)

attributes = Suppress("{") + ZeroOrMore(attribute) + Suppress("}")

styling = widget ^ attributes

question = Suppress("question") + identifier + Optional(styling)


default = Suppress("default") + datatype + styling


sectionbody = Forward()

section = Suppress("section") + QuotedString("\"") + Suppress("{") + sectionbody + Suppress("}")
section.setParseAction(lambda tokens: Section(*tokens))

sectionbody <<= ZeroOrMore(section ^ question ^ default)
sectionbody.setParseAction(lambda tokens: [tokens.asList()])

pagebody = sectionbody
# pagebody.setParseAction(lambda tokens: [tokens.asList()])

page = Suppress("page") + identifier + Suppress("{") + pagebody + Suppress("}")
page.setParseAction(lambda tokens: Page(*tokens))

stylebody = ZeroOrMore(page ^ default)
stylebody.setParseAction(lambda tokens: [tokens.asList()])
stylesheet = Suppress("stylesheet") + identifier + Suppress("{") + stylebody + Suppress("}")
stylesheet.setParseAction(lambda tokens: StyleSheet(*tokens))
