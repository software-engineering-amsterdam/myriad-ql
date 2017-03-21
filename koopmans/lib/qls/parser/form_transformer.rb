require 'parslet'

module QLS
  module Parser
    class FormTransformer < Parslet::Transform
      include AST
      # stylesheet
      rule(stylesheet: { variable: simple(:variable), pages: subtree(:pages) }) { Stylesheet.new(variable, pages) }

      # page
      rule(page: { variable: simple(:variable), block: subtree(:block) }) { Page.new(variable, block) }

      # section
      rule(section: { string: simple(:name), block: subtree(:block) }) { Section.new(name, block) }

      # question
      rule(question: { variable: simple(:name), properties: subtree(:properties) }) { Question.new(QL::AST::Variable.new(name), properties) }

      # types
      rule(type: 'boolean') { QL::AST::BooleanType.new }
      rule(type: 'integer') { QL::AST::IntegerType.new }
      rule(type: 'money')   { QL::AST::MoneyType.new }
      rule(type: 'string')  { QL::AST::StringType.new }
      rule(type: 'decimal') { QL::AST::DecimalType.new }
      rule(type: 'date')    { QL::AST::DateType.new }

      # default type properties
      rule(default: { type: simple(:type), properties: subtree(:properties) }) { Default.new(type, properties) }

      # properties
      rule(width: { integer_literal: simple(:value) }) { Width.new(value) }
      rule(font: { string_literal: simple(:value) }) { Font.new(value) }
      rule(fontsize: { integer_literal: simple(:value) }) { Fontsize.new(value) }
      rule(color: { string_literal: simple(:value) }) { Color.new(value) }

      # rule(width: { integer: simple(:value) }) { value }
      # rule(font: { string: simple(:value) }) { value }
      # rule(fontsize: { integer: simple(:value) }) { value }
      # rule(color: { string: simple(:value) }) { value }

      rule(integer_literal: simple(:value)) { value }
      rule(string_literal: simple(:value)) { value }

      # widgets
      rule(widget: 'spinbox') { { widget: SpinboxWidget.new } }
      rule(widget: 'text') { { widget: TextWidget.new } }
      rule(widget: 'checkbox') { { widget: CheckboxWidget.new } }
      rule(widget: { slider: { first_value: simple(:minimum), second_value: simple(:maximum) } }) { { widget: SliderWidget.new(minimum, maximum) } }
      rule(widget: { radio: { first_value: simple(:true_value), second_value: simple(:false_value) } }) { { widget: RadioWidget.new(true_value, false_value) } }
      rule(widget: { dropdown: { first_value: { string: simple(:true_value), second_value: simple(:false_value) } } }) { { widget: DropdownWidget.new(true_value, false_value) } }
    end
  end
end