import unittest

import pyparsing as pp
from QLS import AST
from QL.AST import BlockNode
from QLS.stages.parser import Parser


class TestQLSParser(unittest.TestCase):
    parser = Parser()
    parse_properties = parser.property_type
    parse_widget_type = parser.widget_type
    parse_question = parser.question
    parse_default = parser.default
    parse_section = parser.section
    parse_page = parser.page
    parse_stylesheet = parser.grammar

    def validate_node(self, parse_method, parse_str, test_node):
        parse_res = self.parse(parse_method, parse_str)[0]
        self.assertEqual(parse_res, test_node)

    def check_parse_exception(self, parse_method, parse_str, raised_exception):
        with self.assertRaises(raised_exception) as excep:
            self.parse(parse_method, parse_str)
        self.assertEqual(excep.expected, raised_exception)

    @staticmethod
    def parse(parse_method, input_str):
        return parse_method.parseString(input_str, parseAll=True)

    def test_literal_int(self):
        pass

    def test_property_width(self):
        self.validate_node(self.parse_properties, "width: 20",
                           AST.WidthNode(20))
        self.validate_node(self.parse_properties, "width: 30",
                           AST.WidthNode(30))
        self.check_parse_exception(self.parse_properties, "width: 20.0",
                                   pp.ParseException)

    def test_property_height(self):
        self.validate_node(self.parse_properties, "height: 20",
                           AST.HeightNode(20))
        self.validate_node(self.parse_properties, "height: 30",
                           AST.HeightNode(30))
        self.check_parse_exception(self.parse_properties, "height: 20.0",
                                   pp.ParseException)

    def test_property_font(self):
        self.validate_node(self.parse_properties, "font: \"Arial\"",
                           AST.FontNode("Arial"))
        self.validate_node(self.parse_properties, "font: \"Times New Roman\"",
                           AST.FontNode("Times New Roman"))
        self.check_parse_exception(self.parse_properties, "font: 20.0",
                                   pp.ParseException)

    def test_property_fontsize(self):
        self.validate_node(self.parse_properties, "fontsize: 20",
                           AST.FontSizeNode(20))
        self.validate_node(self.parse_properties, "fontsize: 30",
                           AST.FontSizeNode(30))
        self.check_parse_exception(self.parse_properties, "fontsize: 20.0",
                                   pp.ParseException)

    def test_property_color(self):
        self.validate_node(self.parse_properties, "color: #123456",
                           AST.ColorNode("#123456"))

        self.validate_node(self.parse_properties, "color: #ABCdef",
                           AST.ColorNode("#ABCdef"))
        self.check_parse_exception(self.parse_properties, "color: #12345678",
                                   pp.ParseException)
        self.check_parse_exception(self.parse_properties, "color: #ABCD0G",
                                   pp.ParseException)

    def test_widget_type_slider(self):
        self.validate_node(self.parse_widget_type, "widget slider",
                           AST.SliderNode())

    def test_widget_type_spinbox(self):
        self.validate_node(self.parse_widget_type, "widget spinbox",
                           AST.SpinboxNode())

    def test_widget_type_text(self):
        self.validate_node(self.parse_widget_type, "widget text",
                           AST.TextNode())

    def test_widget_type_numeric(self):
        self.validate_node(self.parse_widget_type, "widget numeric",
                           AST.NumericNode())

    def test_widget_type_radio(self):
        self.validate_node(self.parse_widget_type, "widget radio",
                           AST.RadioNode())

    def test_widget_type_checkbox(self):
        self.validate_node(self.parse_widget_type, "widget checkbox",
                           AST.CheckboxNode())

    def test_widget_type_dropdown(self):
        self.validate_node(self.parse_widget_type, "widget dropdown",
                           AST.DropdownNode())

    def test_question(self):
        self.validate_node(self.parse_question, "question name",
                           AST.QuestionNode("name"))

    def test_widget_question(self):
        self.validate_node(self.parse_question, "question name widget numeric",
                           AST.WidgetQuestionNode("name", AST.NumericNode()))

    def test_default(self):
        self.validate_node(
            self.parse_default, "default boolean widget checkbox",
            AST.DefaultNode(AST.BoolTypeNode(), AST.CheckboxNode())
        )

    def test_default_with_props(self):
        default = """
            default boolean {
                width: 400
                widget checkbox
            }
        """
        default_node = AST.DefaultWithPropsNode(
            AST.BoolTypeNode(),
            BlockNode([AST.WidthNode(400)]), AST.CheckboxNode()
        )
        self.validate_node(self.parse_default, default, default_node)

        default = """
            default boolean {
                width: 400
                widget checkbox
                font: "Arial"
            }
        """
        self.check_parse_exception(self.parse_default, default,
                                   pp.ParseException)

    def test_section(self):
        section = """
            section "section name" {
                question question_name
            }
        """
        section_node = AST.SectionNode("section name", BlockNode(
                [AST.QuestionNode("question_name")]
            )
        )
        self.validate_node(self.parse_section, section, section_node)
        self.check_parse_exception(self.parse_section, "section \"name\" {}",
                                   pp.ParseException)

    def test_section_in_section(self):
        section = """
            section "section1" {
                section "section2" {
                    question name
                }
            }
        """
        section_node = AST.SectionNode(
            "section1",
            BlockNode([
                AST.SectionNode(
                    "section2",
                    BlockNode([AST.QuestionNode("name")])
                )
            ])
        )
        self.validate_node(self.parse_section, section, section_node)

    def test_section_defaults(self):
        section = """
            section "section name" {
                question question_name
                default integer widget slider
                default boolean widget checkbox
            }
        """
        section_node = AST.SectionWithDefaultsNode(
            "section name",
            BlockNode([AST.QuestionNode("question_name")]),
            BlockNode([
                AST.DefaultNode(AST.IntTypeNode(), AST.SliderNode()),
                AST.DefaultNode(AST.BoolTypeNode(), AST.CheckboxNode())
            ])
        )
        self.validate_node(self.parse_section, section, section_node)

        wrong_section = """
            section \"section name\" {
                question name
                default integer widget slider
                question name
            }
        """
        self.check_parse_exception(self.parse_section, wrong_section,
                                   pp.ParseException)

    def test_page(self):
        page = """
            page Selling {
                section "section1 name" {
                    question question_name1
                }
                section "section2 name" {
                    question question_name2
                 }
            }
        """
        page_node = AST.PageNode(
            "Selling",
            BlockNode([
                AST.SectionNode("section1 name", BlockNode(
                        [AST.QuestionNode("question_name1")]
                )),
                AST.SectionNode("section2 name", BlockNode(
                    [AST.QuestionNode("question_name2")]
                ))
            ])
        )
        self.validate_node(self.parse_page, page, page_node)
        wrong_page = """
            page Selling {
                page Page {
                    section "section" {
                        question question_name
                    }
                }
            }
        """
        self.check_parse_exception(self.parse_page, wrong_page,
                                   pp.ParseException)

    def test_page_with_defaults(self):
        page = """
            page Selling {
                section "section1 name" {
                    question question_name1
                }
                section "section2 name" {
                    question question_name2
                 }
                 default boolean widget radio
                 default integer widget slider
            }
        """
        page_node = AST.PageWithDefaultsNode(
            "Selling",
            BlockNode([
                AST.SectionNode("section1 name", BlockNode(
                    [AST.QuestionNode("question_name1")]
                )),
                AST.SectionNode("section2 name", BlockNode(
                    [AST.QuestionNode("question_name2")]
                ))
            ]),
            BlockNode([
                AST.DefaultNode(AST.BoolTypeNode(), AST.RadioNode()),
                AST.DefaultNode(AST.IntTypeNode(), AST.SliderNode())
            ])
        )
        self.validate_node(self.parse_page, page, page_node)
        wrong_page = """
            page Selling {
                section "section" {
                    question question_name
                }
                default integer widget spinbox
                section "section" {
                    question question_name
                }
            }
        """
        self.check_parse_exception(self.parse_page, wrong_page,
                                   pp.ParseException)

    def test_stylesheet(self):
        stylesheet = """
        stylesheet StyleSheet {
            page Selling {
                section "Selling" {
                    question hasSoldHouse
                    question widgetQuestion widget spinbox
                    default integer {
                        color: #123123
                        fontsize: 12
                        widget spinbox
                    }
                    default boolean widget radio
                 }
            }
            page Housing {
                section "Buying" {
                    question name
                }
            }
        }
        """
        default_block = BlockNode([
            AST.DefaultWithPropsNode(
                AST.IntTypeNode(),
                BlockNode([
                    AST.ColorNode("#123123"),
                    AST.FontSizeNode(12)
                ]),
                AST.SpinboxNode()
            ),
            AST.DefaultNode(
                AST.BoolTypeNode(),
                AST.RadioNode()
            )
        ])

        stylesheet_node = AST.StylesheetNode(
            "StyleSheet",
            BlockNode([
                AST.PageNode(
                    "Selling",
                    BlockNode([
                        AST.SectionWithDefaultsNode(
                            "Selling",
                            BlockNode([
                                AST.QuestionNode("hasSoldHouse"),
                                AST.WidgetQuestionNode(
                                    "widgetQuestion",
                                    AST.SpinboxNode()
                                )
                            ]),
                            default_block
                        )
                    ])
                ),
                AST.PageNode(
                    "Housing",
                    BlockNode([
                        AST.SectionNode(
                            "Buying",
                            BlockNode([
                                AST.QuestionNode("name")
                            ])
                        )
                    ])
                )
            ])
        )
        self.validate_node(self.parse_stylesheet, stylesheet, stylesheet_node)


if __name__ == '__main__':
    unittest.main()
