module Prophet
  module Checkers
    class UndefinedIdentifiers < Base
      def check
        ast.select do |node|
          Ast::Identifier === node
        end.reject do |identifier|
          defined_identifiers.include? identifier.name.to_s
        end.each do |identifier|
          puts error_formatter(identifier)
        end
      end

      def error_formatter(item)
        "Identifier `#{item.name}` is not defined (used on #{item.name.line_and_column.join(':')})"
      end

      private

      attr_reader :ast

      def defined_identifiers
        ast.select do |node|
          Ast::Question === node || Ast::Form === node
        end.map do |question|
          question.identifier.name.to_s
        end
      end
    end
  end
end
