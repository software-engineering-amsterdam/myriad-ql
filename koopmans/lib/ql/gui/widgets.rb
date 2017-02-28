module QL
  module GUI
    class Widget
      attr_accessor :question

      def initialize(args)
        @question = args[:question]
      end
    end

    class RadioWidget < Widget
      def initialize(args)
        super
        radio_button          = TkRadioButton.new(@question.frame).pack
        radio_button.variable = @question.variable
        radio_button.command  = proc { @question.gui.value_changed }
        radio_button.text     = args[:true_value]
        radio_button.value    = true

        radio_button          = TkRadioButton.new(@question.frame).pack
        radio_button.variable = @question.variable
        radio_button.command  = proc { @question.gui.value_changed }
        radio_button.text     = args[:false_value]
        radio_button.value    = false
      end
    end

    class CheckboxWidget < Widget
      def initialize(args)
        super
        check_button          = TkCheckButton.new(@question.frame).pack
        check_button.variable = @question.variable
        check_button.command  = proc { @question.gui.value_changed }
      end
    end

    class DropdownWidget < Widget
      def initialize(args)
        super
        combobox        = Tk::Tile::Combobox.new(@question.frame).pack
        combobox.values = [args[:true_value], args[:false_value]]
        combobox.bind('<ComboboxSelected>') do
          @question.variable.value = true if combobox.value == args[:true_value]
          @question.variable.value = false if combobox.value == args[:false_value]
          @question.gui.value_changed
        end
        combobox.value = args[:true_value]
      end
    end

    class SpinboxWidget < Widget
      def initialize(args)
        super
        spinbox              = TkSpinbox.new(@question.frame).pack
        # system's min
        spinbox.from         = -(2**(0.size * 8 -2))
        # system's max
        spinbox.to           = (2**(0.size * 8 -2) -1)
        spinbox.value        = 0
        # spinbox.increment = 0.1
        # spinbox.format = "%.2f"
        spinbox.textvariable = @question.variable
        spinbox.command      = proc { @question.gui.value_changed }
      end
    end

    class SliderWidget < Widget
      def initialize(args)
        super
        scale          = TkScale.new(@question.frame).pack
        scale.orient   = 'horizontal'
        scale.from     = args[:minimum]
        scale.to       = args[:maximum]
        scale.variable = @question.variable
        scale.command  = proc { @question.gui.value_changed }
      end
    end

    class TextWidget < Widget
      def initialize(args)
        super
        entry              = TkEntry.new(@question.frame).pack
        entry.textvariable = @question.variable
        entry.bind('KeyRelease') do
          @question.gui.value_changed
        end
      end
    end
  end
end