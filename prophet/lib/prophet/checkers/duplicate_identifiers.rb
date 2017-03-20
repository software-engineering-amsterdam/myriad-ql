module Prophet
  module Checkers
    class DuplicateIdentifiers < Base
      def check
        names = ast.select_by_type(:question, :question_with_value).map do |question|
          question.identifier.name
        end

        names.group_by(&:to_s).values.reject(&:one?).collect do |names|
          puts error_formatter(names)
        end
      end

      def error_formatter(names)
        "Identifier `#{names.first}` is defined multiple times (on " \
        "#{names.map { |i| i.line_and_column.join(':') }.join(', ')})"
      end

      private

      attr_reader :ast
    end
  end
end
