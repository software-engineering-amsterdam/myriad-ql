module Prophet
  module Checkers
    class UndefinedIdentifiers < Base
      def check
        defined_names = ast.select_by_type(:form, :question, :question_with_value).map do |node|
          node.identifier.name
        end

        ast.select_by_type(:identifier).map(&:name).reject do |name|
          defined_names.include? name
        end.each do |name|
          puts error_formatter(name)
        end
      end

      def error_formatter(name)
        "Identifier `#{name}` is not defined (used on #{name.line_and_column.join(':')})"
      end

      private

      attr_reader :ast
    end
  end
end
