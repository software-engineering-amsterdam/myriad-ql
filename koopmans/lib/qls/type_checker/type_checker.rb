module QLS
  module TypeChecker
    class TypeChecker
      # include Visitor

      def self.check(stylesheet, form)
        { errors:   [QuestionReferenceChecker].map { |checker| stylesheet.accept(checker.new, form) }.flatten }
      end
    end
  end
end