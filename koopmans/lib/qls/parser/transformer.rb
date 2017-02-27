module QLS
  module Parser
    class Transformer < Parslet::Transform
      include AST
      # variable
      # rule(variable: simple(:variable)) { Variable.new(variable) }
      rule(stylesheet: {variable: simple(:variable), pages: subtree(:pages)}) { StyleSheet.new(variable, pages) }

      rule(page: {
          variable: simple(:variable),
          block: subtree(:block)
      }) { Page.new(variable, block) }

    end
  end
end