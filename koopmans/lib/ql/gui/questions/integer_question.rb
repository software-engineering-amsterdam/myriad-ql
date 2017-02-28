module QL
  module GUI
    class IntegerQuestion < TextQuestion
      include AST

      def initialize(args)
        super
        @variable.value = (0)
        @variable.type  = IntegerType
        @previous_value = value
        SliderWidget.new(question: self, minimum: 0, maximum: 10)
      end
    end
  end
end