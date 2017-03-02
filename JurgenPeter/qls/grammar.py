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


integer = Regex("-?[0-9]+").addParseAction(lambda tokens: int(tokens[0]))

integer_arguments = Suppress("(") + integer + Suppress(",") + \
                    integer + Suppress(")")

string_arguments = Suppress("(") + QuotedString("\"") + Suppress(",") +\
            QuotedString("\"") + Suppress(")")


widget_entry = Suppress("text").setParseAction(
    lambda _: EntryWidget)

widget_checkbox = Suppress("checkbox").setParseAction(
    lambda _: CheckBoxWidget)

widget_spinbox = Suppress("spinbox") + Optional(integer_arguments)
widget_spinbox.setParseAction(
    lambda _: SpinBoxWidget)

widget_slider = Suppress("slider") + Optional(integer_arguments)
widget_slider.setParseAction(
    lambda tokens: lambda question: SliderWidget(question, *tokens))

widget_radio = Suppress("radio") + Optional(string_arguments)
widget_radio.setParseAction(
    lambda tokens: lambda question: RadioWidget(question, *tokens))

widget_drop_down = Suppress("dropdown") + Optional(string_arguments)
widget_drop_down.setParseAction(
    lambda tokens: lambda question: DropDownWidget(question, *tokens))


widget_type = widget_checkbox ^ widget_spinbox ^ widget_radio ^ widget_entry ^\
              widget_drop_down ^ widget_slider

widget = Suppress("widget") + widget_type

name = oneOf("width height font color fontsize fontcolor")

hexadecimal = Regex("#[0-9a-f]{6}")
literal = pyparsing_common.integer ^ pyparsing_common.real ^\
          QuotedString("\"") ^ hexadecimal

attribute = widget ^ (name + Suppress(":") + literal)

attributes = Suppress("{") + ZeroOrMore(attribute) + Suppress("}")

styling = widget ^ attributes

question = Suppress("question") + identifier + Optional(styling)


default = Suppress("default") + datatype + styling


sectionbody = Forward()

section = Suppress("section") + QuotedString("\"") + Suppress("{") +\
          sectionbody + Suppress("}")
section.setParseAction(lambda tokens: Section(*tokens))

sectionbody <<= ZeroOrMore(section ^ question ^ default)
sectionbody.setParseAction(lambda tokens: [tokens.asList()])

pagebody = sectionbody
# pagebody.setParseAction(lambda tokens: [tokens.asList()])

page = Suppress("page") + identifier + Suppress("{") + pagebody + Suppress("}")
page.setParseAction(lambda tokens: Page(*tokens))

stylebody = ZeroOrMore(page ^ default)
stylebody.setParseAction(lambda tokens: [tokens.asList()])
stylesheet = Suppress("stylesheet") + identifier + Suppress("{") + stylebody +\
             Suppress("}")
stylesheet.setParseAction(lambda tokens: StyleSheet(*tokens))


def parse_file(filename):
    return stylesheet.parseFile(filename)[0]


def parse_string(string):
    return stylesheet.parseString(string, parseAll=True)[0]


if __name__ == "__main__":
    parse_file("../examplestylesheet")
    # print(parse_string("""
    # stylesheet x {
    #     page y {
    #         section "1" {
    #             question ques
    #                 widget spinbox(0, 100)
    #         }
    #         section "2" {
    #             question quess
    #                 widget checkbox
    #             section "22" {
    #                 question quesinsec
    #                     widget dropdown("a", "b")
    #             }
    #         }
    #     }
    #
    #     page z {
    #         section "Selling" {
    #             question hasSoldHouse
    #                 widget radio("Yes", "No")
    #             section "You sold a house" {
    #                 question sellingPrice
    #                 widget spinbox(0, 10)
    #                 question privateDebt
    #                 widget spinbox(0, 10)
    #                 question valueResidue
    #                 default integer {
    #                     width: 400
    #                     font: "Arial"
    #                     fontsize: 14
    #                     color: #999999
    #                     widget spinbox
    #                 }
    #             }
    #         }
    #     }
    # }
    # """))
