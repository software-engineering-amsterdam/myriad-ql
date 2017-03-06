module QL
  module GUI
    class Widget
      # attr_accessor :question

      def initialize(args)
        @question_frame = args[:question_frame]
      end

      def frame
        @question_frame.frame
      end

      def variable
        @question_frame.variable
      end

      def reload_questions
        @question_frame.gui.reload_questions
      end
    end

    class RadioWidget < Widget
      def initialize(args)
        super
        radio_button          = TkRadioButton.new(frame).pack
        radio_button.text     = args[:true_value]
        radio_button.value    = true
        radio_button.variable = variable
        radio_button.command  = proc { reload_questions }

        radio_button          = TkRadioButton.new(frame).pack
        radio_button.text     = args[:false_value]
        radio_button.value    = false
        radio_button.variable = variable
        radio_button.command  = proc { reload_questions }
      end
    end

    class CheckboxWidget < Widget
      def initialize(args)
        super
        check_button          = TkCheckButton.new(frame).pack
        check_button.variable = variable
        check_button.command  = proc { reload_questions }
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
          reload_questions
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
        spinbox.command      = proc { reload_questions }
      end
    end

    class SliderWidget < Widget
      def initialize(args)
        super
        scale          = TkScale.new(frame).pack
        scale.from     = args[:minimum]
        scale.to       = args[:maximum]
        scale.variable = variable
        scale.command  = proc { reload_questions }
      end
    end

    class TextWidget < Widget
      def initialize(args)
        super
        entry              = TkEntry.new(frame).pack
        entry.textvariable = variable
        entry.bind('KeyRelease') { reload_questions }
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
        label.text = @question_frame.label
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

    class SubmitButton
      def initialize(gui)
        button         = TkButton.new.grid(row: gui.questions.size + 1)
        button.text    = 'Submit'
        button.command = proc { gui.submit }
      end
    end
  end
end