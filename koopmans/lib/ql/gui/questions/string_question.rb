module QL
  module GUI
    class StringQuestion < Question
      include AST

      def initialize(args)
        super
        @variable.value = ("")
        @variable.type  = StringType

        TextWidget.new(question: self)
      end
    end
  end
end