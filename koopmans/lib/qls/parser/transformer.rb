require 'parslet'

module QLS
  module Parser
    class Transformer < Parslet::Transform
      include AST
      # stylesheet
      rule(stylesheet: { variable: simple(:variable), pages: subtree(:pages) }) { Stylesheet.new(variable, pages) }

      # page
      rule(page: { variable: simple(:variable), block: subtree(:block) }) { Page.new(variable, block) }

      # section
      rule(section: { string: simple(:name), block: subtree(:block) }) { Section.new(name, block) }

      # question
      rule(question: { variable: simple(:name), properties: subtree(:properties) }) { Question.new(QL::AST::Variable.new(name), properties) }

      # default type properties
      rule(default: { type: 'boolean', properties: subtree(:properties) }) { Default.new(QL::AST::BooleanType, properties) }
      rule(default: { type: 'integer', properties: subtree(:properties) }) { Default.new(QL::AST::IntegerType, properties) }
      rule(default: { type: 'money', properties: subtree(:properties) }) { Default.new(QL::AST::MoneyType, properties) }
      rule(default: { type: 'string', properties: subtree(:properties) }) { Default.new(QL::AST::StringType, properties) }
      rule(default: { type: 'decimal', properties: subtree(:properties) }) { Default.new(QL::AST::DecimalType, properties) }
      rule(default: { type: 'date', properties: subtree(:properties) }) { Default.new(QL::AST::DateType, properties) }

      # properties
      # rule(width: { integer: simple(:value) }) { Width.new(value) }
      # rule(font: { string: simple(:value) }) { Font.new(value) }
      # rule(fontsize: { integer: simple(:value) }) { Fontsize.new(value) }
      # rule(color: { string: simple(:value) }) { Color.new(value) }

      # rule(width: { integer: simple(:value) }) { value }
      # rule(font: { string: simple(:value) }) { value }
      # rule(fontsize: { integer: simple(:value) }) { value }
      # rule(color: { string: simple(:value) }) { value }

      rule(integer: simple(:value)) { value }
      rule(string: simple(:value)) { value }

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