module QLS
  module Parser
    class Transformer < Parslet::Transform
      include AST
      # variable
      # rule(variable: simple(:variable)) { Variable.new(variable) }
      rule(stylesheet: {variable: simple(:variable), pages: subtree(:pages)}) { StyleSheet.new(variable, pages) }

      rule(page: {variable: simple(:variable), block: subtree(:block)}) { Page.new(variable, block) }

      rule(section: {string: simple(:name), block: subtree(:block)}) { Section.new(name, block) }

      rule(question: {variable: simple(:variable), properties: subtree(:properties)}) { Question.new(variable, properties) }
      # rule(question: {variable: simple(:variable), properties: sequence(:properties)}) { Question.new(variable, properties) }
      # rule(properties: sequence(:properties)) { p 'assad' }

      rule('spinbox') { SpinboxWidget.new }
      rule(radio_button: {
          first: {string: simple(:true_value)},
          second: {string: simple(:false_value)}}) { RadioWidget.new(true_value, false_value) }

    end
  end
end