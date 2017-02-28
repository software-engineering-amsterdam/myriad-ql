module QL
  module GUI
    class TextQuestion < Question
      def initialize(args)
        super
        # TextWidget.new(question: self)
        # SliderWidget.new(question: self, minimum: 0, maximum: 10)
      end
    end
  end
end