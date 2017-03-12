module Prophet
  module Checkers
    class InvalidOperands < Base
      # Checks that, for each question having an assigned expression, all of the
      # expression's terminals (identifiers or literals) match the type of the
      # question
      def check
        ast.select do |node|
          Ast::Question === node && node.value
        end.each do |question|
          terminals = question.value.select do |expression|
            expression.children.count.zero?
          end
          next if terminals.empty?
          next if terminals.all? do |expression|
            case expression
            when Ast::Identifier
              type_mapping[expression.name.to_s] == question.type.name.to_s
            when Ast::TextLiteral
              question.type.name.to_s == 'text'
            when Ast::NumberLiteral
              question.type.name.to_s == 'number'
            when Ast::BoolLiteral
              question.type.name.to_s == 'bool'
            end
          end

          puts error_formatter(question)
        end
      end

      def error_formatter(question)
        "Expression attached to question `#{question.identifier.name}` " \
        "contains types other than `#{question.type.name}` (defined on " \
        "#{question.identifier.name.line_and_column.join(':')})"
      end

      private

      attr_reader :ast, :type_mapping

      def type_mapping
        @type_mapping ||= ast.select do |node|
          Ast::Question === node
        end.map do |question|
          [question.identifier.name.to_s, question.type.name.to_s]
        end.to_h
      end
    end
  end
end
