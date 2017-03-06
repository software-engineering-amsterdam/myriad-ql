module QL
  module GUI
    class Widget
      attr_accessor :question

      def initialize(args)
        @question_frame = args[:question_frame]
      end

      def frame
        @question_frame.frame
      end

      def variable
        @question_frame.variable
      end

      def callback
        @question_frame.gui.value_changed
      end
    end

    class RadioWidget < Widget
      def initialize(args)
        super
        radio_button          = TkRadioButton.new(frame).pack
        radio_button.text     = args[:true_value]
        radio_button.value    = true
        radio_button.variable = variable
        radio_button.command  = proc { callback }

        radio_button          = TkRadioButton.new(frame).pack
        radio_button.text     = args[:false_value]
        radio_button.value    = false
        radio_button.variable = variable
        radio_button.command  = proc { callback }
      end
    end

    class CheckboxWidget < Widget
      def initialize(args)
        super
        check_button          = TkCheckButton.new(frame).pack
        check_button.variable = variable
        check_button.command  = proc { callback }
      end
    end

    class DropdownWidget < Widget
      def initialize(args)
        super
        combobox        = Tk::Tile::Combobox.new(frame).pack
        combobox.values = [args[:true_value], args[:false_value]]
        combobox.value = args[:true_value]
        combobox.bind('<ComboboxSelected>') do
          variable.value = true if combobox.value == args[:true_value]
          variable.value = false if combobox.value == args[:false_value]
          callback
        end
      end
    end

    class SpinboxWidget < Widget
      def initialize(args)
        super
        spinbox              = TkSpinbox.new(frame).pack
        spinbox.from         = -(2**(0.size * 8 -2)) # system's min
        spinbox.to           = (2**(0.size * 8 -2) -1) # system's max
        spinbox.value        = 0
        # spinbox.increment  = 0.1
        # spinbox.format     = "%.2f"
        spinbox.textvariable = variable
        spinbox.command      = proc { callback }
      end
    end

    class SliderWidget < Widget
      def initialize(args)
        super
        scale          = TkScale.new(frame).pack
        scale.from     = args[:minimum]
        scale.to       = args[:maximum]
        scale.variable = variable
        scale.command  = proc { callback }
      end
    end

    class TextWidget < Widget
      def initialize(args)
        super
        entry              = TkEntry.new(frame).pack
        entry.textvariable = variable
        entry.bind('KeyRelease') { callback }
      end
    end

    class ComputedWidget < Widget
      def initialize(args)
        super
        entry              = TkEntry.new(frame).pack
        entry.textvariable = variable
        entry.state        = 'disabled'
      end
    end

    class Label < Widget
      def initialize(args)
        super
        label      = TkLabel.new(frame).pack
        label.text = @question_frame.question.label
      end
    end

    class Frame < Widget
      def initialize(args)
        super
        @question_frame.frame = TkFrame.new.grid(row: position)
      end

      def position
        @question_frame.gui.questions.size
      end
    end
  end
end