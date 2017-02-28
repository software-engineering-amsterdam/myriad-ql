module QL
  module GUI
    class Widget
      attr_accessor :question

      def initialize(args)
        @question = args[:question]
        p @question
      end
    end

    class RadioWidget < Widget
      def initialize(args)
        super
        create_radio_button(args[:true_value], true)
        create_radio_button(args[:false_value], false)
      end

      def create_radio_button(text, value)
        radio_button          = TkRadioButton.new(@question.frame).pack
        radio_button.variable = @question.variable
        radio_button.command  = proc { @question.gui.value_changed }
        radio_button.text     = text
        radio_button.value    = value
      end
    end

    class CheckboxWidget < Widget
      def initialize(args)
        super
        create_checkbox_button
      end

      def create_checkbox_button
        check_button          = TkCheckButton.new(@question.frame).pack
        check_button.variable = @question.variable
        check_button.command  = proc { @question.gui.value_changed }
      end
    end

    class DropdownWidget < Widget
      def initialize(args)
        super
        create_combobox(args[:true_value], args[:false_value])
      end

      def create_combobox(true_value, false_value)
        combobox        = Tk::Tile::Combobox.new(@question.frame).pack
        combobox.values = [true_value, false_value]
        combobox.bind('<ComboboxSelected>') do
          @question.variable.value = true if combobox.value == true_value
          @question.variable.value = false if combobox.value == false_value
          @question.gui.value_changed
        end
        combobox.value = true_value
      end
    end

    class SpinboxWidget < Widget
      def initialize(args)
        super
        create_spinbox
      end

      def create_spinbox
        spinbox = TkSpinbox.new(@question.frame).pack
        # system's min
        spinbox.from = -(2**(0.size * 8 -2))
        # system's max
        spinbox.to = (2**(0.size * 8 -2) -1)
        spinbox.value = 0
        spinbox.textvariable = @question.variable
        spinbox.command = proc { @question.gui.value_changed }
      end
    end

    class SliderWidget < Widget
      def initialize(args)
        super
        create_scale(args[:minimum], args[:maximum])
      end
      def create_scale(minimum, maximum)
        scale = TkScale.new(@question.frame).pack
        scale.orient 'horizontal'
        scale.from = minimum
        scale.to = maximum
        scale.variable = @question.variable
        scale.command = proc { @question.gui.value_changed }
      end
    end

    class TextWidget < Widget
      def initialize(args)
        super
        create_entry
      end

      def create_entry
        entry              = TkEntry.new(@question.frame).pack
        entry.textvariable = @question.variable
        entry.bind('KeyRelease') do
          # p @question.variable.type
          # @question.variable.value = @question.previous_value if entry.value == ''
          # only if value changes
          unless @question.previous_value == entry.value
            @question.gui.value_changed
            @question.previous_value = entry.value
          end
        end
      end
    end
  end
end