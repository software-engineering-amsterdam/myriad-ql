module Prophet
  module Checkers
    class DuplicateIdentifiers < Base
      def check
        defined_identifiers.group_by(&:to_s).values.select do |identifiers|
          identifiers.count > 1
        end.each do |identifiers|
          puts error_formatter(identifiers)
        end
      end

      def error_formatter(items)
        "Identifier `#{items.first}` is defined multiple times (on " \
        "#{items.map { |i| i.line_and_column.join(':') }.join(', ')})"
      end

      private

      attr_reader :ast

      def defined_identifiers
        ast.select do |node|
          Ast::Question === node || Ast::Form === node
        end.map do |question|
          question.identifier.name
        end
      end
    end
  end
end
