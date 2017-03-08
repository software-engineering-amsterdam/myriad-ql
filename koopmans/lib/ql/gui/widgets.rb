module QL
  module GUI
    class Widget
      attr_accessor :value

      def initialize(question_frame, args=nil)
        @question_frame = question_frame
      end

      # def frame
      #   @question_frame.frame
      # end

      # def variable
      #   @question_frame.variable
      # end

      def reload_questions
        @question_frame.gui.reload_questions
      end
    end

    class RadioWidget < Widget
      def initialize(question_frame, args=nil)
        super
        @value = true

        if args
          @true_label = args[:true_value]
          @false_label = args[:false_value]
        end

        shared_variable = TkVariable.new(@value)

        radio_button          = TkRadioButton.new(question_frame.frame).pack
        radio_button.text     = @true_label
        radio_button.value    = true
        radio_button.variable = shared_variable
        radio_button.command  = proc do
          @value = shared_variable.bool
          question_frame.store_value
          # @question_frame.store_value_in_question_table(shared_variable.bool)
          # reload_questions
        end

        radio_button          = TkRadioButton.new(question_frame.frame).pack
        radio_button.text     = @false_label
        radio_button.value    = false
        radio_button.variable = shared_variable
        radio_button.command  = proc do
          @value = shared_variable.bool
          question_frame.store_value
          # @question_frame.store_value_in_question_table(shared_variable.bool)
          # reload_questions
        end

        # @question_frame.store_value_in_question_table(shared_variable.bool)

      end


    end

    class CheckboxWidget < Widget
      def initialize(question_frame, args=nil)
        super
        check_button          = TkCheckButton.new(frame).pack
        # check_button.variable = variable
        check_button.command  = proc { reload_questions }
      end
    end

    class DropdownWidget < Widget
      def initialize(question_frame, args=nil)
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
      def initialize(question_frame, args=nil)
        super
        spinbox              = TkSpinbox.new(question_frame.frame).pack
        spinbox.from         = -(2**(0.size * 8 -2)) # system's min
        spinbox.to           = (2**(0.size * 8 -2) -1) # system's max
        spinbox.value        = 0
        # spinbox.increment  = 0.1
        # spinbox.format     = "%.2f"
        # spinbox.textvariable = variable
        spinbox.command      = proc do
          @value = spinbox.value
          question_frame.store_value
        end
        # @question_frame.store_value_in_question_table(spinbox.value)
      end
    end

    class SliderWidget < Widget
      def initialize(question_frame, args=nil)
        super
        scale          = TkScale.new(frame).pack
        scale.from     = args[:minimum]
        scale.to       = args[:maximum]
        # scale.variable = variable
        scale.command  = proc { reload_questions }
      end
    end

    class TextWidget < Widget
      def initialize(question_frame, args=nil)
        super
        entry              = TkEntry.new(frame).pack
        # entry.textvariable = variable
        entry.bind('KeyRelease') { reload_questions }
      end
    end

    class ComputedWidget < Widget
      attr_accessor :variable

      def initialize(question_frame, args=nil)
        super
        @variable = TkVariable.new

        @entry              = TkEntry.new(question_frame.frame).pack
        @entry.textvariable = variable
        @entry.state        = 'disabled'
      end

      def set_value(value)
        @variable.value = value
        value = value
      end
    end

    class Label < Widget
      def initialize(question_frame, args=nil)
        super
        label      = TkLabel.new(question_frame.frame).pack
        label.text = question_frame.label
      end
    end

    class Frame < Widget
      def initialize(question_frame, args=nil)
        super
        question_frame.frame = TkFrame.new.grid(row:  question_frame.gui.questions.size)
      end

      # def position
      #   question_frame.gui.questions.size
      # end
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