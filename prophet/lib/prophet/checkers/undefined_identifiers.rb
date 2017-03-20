module Prophet
  module Checkers
    class UndefinedIdentifiers < Base
      def check
        defined_identifier_names = ast.visit(Collectors::DefinedIdentifiers.new).map(&:name).map(&:to_s)
        used_identifier_names = ast.visit(Collectors::UsedIdentifiers.new).flatten.compact.map(&:name)

        used_identifier_names.reject do |name|
          defined_identifier_names.include? name
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
