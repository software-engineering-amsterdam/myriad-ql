require 'parslet'

module QLS
  module Parser
    class FormTransformer < Parslet::Transform
      include AST
      # variable
      rule(variable: simple(:name)) { QL::AST::Variable.new(name) }

      # stylesheet
      rule(stylesheet: { id: simple(:variable), pages: subtree(:pages) }) { Stylesheet.new(variable, pages) }

      # page
      rule(page: { id: simple(:variable), body: subtree(:body) }) { Page.new(variable, body) }

      # section
      rule(section: { string_literal: simple(:name), body: subtree(:body) }) { Section.new(name, body) }

      # question
      rule(question: { id: simple(:variable), properties: subtree(:properties) }) { Question.new(variable, properties) }

      # types
      rule(type: 'boolean') { QL::AST::BooleanType.new }
      rule(type: 'integer') { QL::AST::IntegerType.new }
      rule(type: 'money')   { QL::AST::MoneyType.new }
      rule(type: 'string')  { QL::AST::StringType.new }
      rule(type: 'decimal') { QL::AST::DecimalType.new }
      rule(type: 'date')    { QL::AST::DateType.new }

      # default type properties
      rule(default_properties: { type: simple(:type), properties: subtree(:properties) }) { DefaultProperties.new(type, properties) }

      # properties
      rule(width: simple(:value))    { Width.new(value) }
      rule(font: simple(:value))     { Font.new(value) }
      rule(fontsize: simple(:value)) { Fontsize.new(value) }
      rule(color: simple(:value))    { Color.new(value) }

      # literals
      rule(integer_literal: simple(:value)) { QL::AST::IntegerLiteral.new(value) }
      rule(string_literal: simple(:value))  { QL::AST::StringLiteral.new(value) }

      # widgets
      rule(widget: { spinbox: simple(:widget_options) })  { SpinboxWidget.new(widget_options) }
      rule(widget: { slider: simple(:widget_options) })   { SliderWidget.new(widget_options) }
      rule(widget: { radio: simple(:widget_options) })    { RadioWidget.new(widget_options) }
      rule(widget: { dropdown: simple(:widget_options) }) { DropdownWidget.new(widget_options) }
      rule(widget: 'text')                                { TextWidget.new }
      rule(widget: 'checkbox')                            { CheckboxWidget.new }

      # widget options
      rule(widget_options: { first_value: simple(:first_value), second_value: simple(:second_value) }) { WidgetOptions.new(first_value, second_value) }
    end
  end
end
