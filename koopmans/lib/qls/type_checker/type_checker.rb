module QLS
  module TypeChecker
    class TypeChecker

      def self.check(stylesheet, form)
        { errors: [QuestionChecker, WidgetChecker].map { |checker| stylesheet.accept(checker.new, form) }.flatten }
      end
    end
  end
end