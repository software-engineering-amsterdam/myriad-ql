module Prophet
  module Checkers
    class InvalidConditions < Base
      def check
          expression_type = if_statement.condition.visit(Visitors::ExpressionType.new(type_mapping))
        ast.select_by_type(:if_statement, :if_else_statement).map do |if_statement|
          if expression_type == Ast::UndefinedType.new
            mismatch_error_formatter(if_statement)
          elsif expression_type != Ast::BoolType.new
            invalid_error_formatter(if_statement, expression_type)
          end
        end.compact
      end

      def mismatch_error_formatter(if_statement)
        "Condition attached to if statement contains type mismatches between " \
        "its terms (defined on #{if_statement.line_and_column.join(':')})"
      end

      def invalid_error_formatter(if_statement, expression_type)
        "Condition attached to if statement evaluates to a type different " \
        "than `bool` (`#{expression_type.name}`, defined on " \
        "#{if_statement.line_and_column.join(':')})"
      end

      private

      attr_reader :type_mapping

      def type_mapping
        @type_mapping ||= ast.select_by_type(:question, :question_with_value).map do |question|
          [question.identifier.name.to_s, question.type]
        end.to_h
      end
    end
  end
end
