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
          ast.visit(Collectors::QuestionsWithValue.new).compact.map do |question|
            [question.identifier.name.to_s,
             question.visit(Collectors::ExpressionIdentifiers.new).flatten.compact.map { |i| i.name.to_s }]
          end
        ]
      end
    end
  end
end
