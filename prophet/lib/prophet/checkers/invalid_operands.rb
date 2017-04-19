module Prophet
  module Checkers
    class InvalidOperands < Base
      def check
          expression_type = question.value.visit(Visitors::ExpressionType.new(type_mapping))
        ast.select_by_type(:question_with_value).map do |question|
          if expression_type == Ast::UndefinedType.new
            mismatch_error_formatter(question)
          elsif expression_type != question.type
            invalid_error_formatter(question, expression_type)
          end
        end.compact
      end

      def mismatch_error_formatter(question)
        "Value attached to question `#{question.identifier.name}` contains " \
        "type mismatches between its terms (defined on " \
        "#{question.line_and_column.join(':')})"
      end

      def invalid_error_formatter(question, expression_type)
        "Value attached to question `#{question.identifier.name}` evaluates " \
        "to a type different than declared type (`#{expression_type.name}` " \
        "instead of `#{question.type.name}`, defined on " \
        "#{question.line_and_column.join(':')})"
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
