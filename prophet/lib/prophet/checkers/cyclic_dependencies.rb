module Prophet
  module Checkers
    class CyclicDependencies < Base
      def check
        dependency_hash.tsort
        []
      rescue TSort::Cyclic
        error_formatter
      end

      def error_formatter
        'A cyclic dependency exists'
      end

      private

      attr_reader :dependency_hash

      def dependency_hash
        @dependency_hash ||= begin
          mapping = ast.select_by_type(:question_with_value).map do |question|
            [question.identifier.name.to_s,
             question.select_by_type(:identifier).map(&:name).map(&:to_s)]
          end
          DependencyHash[mapping]
        end
      end
    end
  end
end
