module Prophet
  module Checkers
    class DuplicateIdentifiers < Base
      def check
        identifiers = ast.visit(Collectors::DefinedIdentifiers.new)

        identifiers.map(&:name).group_by(&:to_s).values.reject(&:one?).collect do |names|
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
