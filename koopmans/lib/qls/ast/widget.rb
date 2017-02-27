module QLS
  module AST
    class SliderWidget
      attr_reader :minimum, :maximum

      def initialize(minimum, maximum)
        @minimum = minimum
        @maximum = maximum
      end
    end

    class SpinboxWidget

    end

    class TextWidget

    end


    class RadioWidget
      attr_reader :true_text, :false_text

      def initialize(true_text, false_text)
        @true_text = true_text
        @false_text = false_text
      end
    end

    class CheckboxWidget

    end

    class DropdownWidget
      attr_reader :true_text, :false_text

      def initialize(true_text, false_text)
        @true_text = true_text
        @false_text = false_text
      end
    end
  end
end