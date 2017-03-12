module Prophet
  module Checkers
    class CyclicDependencies < Base
      def check
        dependency_hash.tsort
      rescue TSort::Cyclic
        puts error_formatter
      end

      def error_formatter
        "A cyclic dependency exists"
      end

      private

      attr_reader :ast, :dependency_hash

      def dependency_hash
        @dependency_hash ||= DependencyHash[
          ast.select do |node|
            Ast::Question === node && node.value
          end.map do |question|
            identifiers = question.value.select do |node|
              Ast::Identifier === node
            end
            [question.identifier.name.to_s, identifiers.map { |i| i.name.to_s }]
          end
        ]
      end
    end
  end
end
