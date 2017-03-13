module QL
  module GUI
    # class Frame
    #   def initialize(question_frame, position)
    #     question_frame.frame = TkFrame.new.grid(row: position)
    #   end
    # end

    class Label
      def initialize(frame, label)
        tk_label      = TkLabel.new(frame).pack
        tk_label.text = label
      end
    end

    # class SubmitButton
    #   def initialize(gui)
    #     position       = gui.number_of_questions + 1
    #     button         = TkButton.new.grid(row: position)
    #     button.text    = 'Submit'
    #     button.command = proc { gui.submit }
    #   end
    # end

    class RadioWidget
      def initialize(frame, args=nil, &block)
        shared_variable = TkVariable.new(true)

        if args
          @true_label  = args[:true_value]
          @false_label = args[:false_value]
        end

        radio_button          = TkRadioButton.new(frame).pack
        radio_button.text     = @true_label
        radio_button.value    = true
        radio_button.variable = shared_variable
        radio_button.command  = proc do
          block.call(shared_variable.bool)
        end

        radio_button          = TkRadioButton.new(frame).pack
        radio_button.text     = @false_label
        radio_button.value    = false
        radio_button.variable = shared_variable
        radio_button.command  = proc do
          block.call(shared_variable.bool)
        end

        block.call(shared_variable.bool)
      end
    end

    class CheckboxWidget
      def initialize(frame, args=nil, &block)
        variable = TkVariable.new(true)

        check_button          = TkCheckButton.new(frame).pack
        check_button.variable = variable
        check_button.command  = proc do
          block.call(variable.bool)
        end

        block.call(variable.bool)
      end
    end

    class DropdownWidget
      def initialize(frame, args=nil, &block)
        @true_label  = 'true'
        @false_label = 'false'

        if args
          @true_label  = args[:true_value]
          @false_label = args[:false_value]
        end

        combobox        = Tk::Tile::Combobox.new(frame).pack
        combobox.values = [@true_label, @false_label]
        combobox.value  = @true_label
        combobox.bind('<ComboboxSelected>') do
          block.call(to_value(combobox.value))
        end

        block.call(to_value(combobox.value))
      end

      def to_value(textual_value)
        if textual_value == @true_label
          true
        else
          false
        end
      end
    end

    class SpinboxWidget
      def initialize(frame, args=nil, &block)
        @minimum  = 0
        @maximum = 100

        if args
          @minimum  = args[:minimum]
          @maximum = args[:maximum]
        end

        spinbox         = TkSpinbox.new(frame).pack
        spinbox.from    = @minimum
        spinbox.to      = @maximum
        spinbox.value   = 0
        spinbox.command = proc do
          block.call(spinbox.value)
        end

        block.call(spinbox.value)
      end
    end

    # spinbox.increment  = 0.1
    # spinbox.format     = "%.2f"
    # spinbox.textvariable = variable

    class SliderWidget
      def initialize(frame, args=nil, &block)
        @minimum  = 0
        @maximum = 100

        if args
          @minimum  = args[:minimum]
          @maximum = args[:maximum]
        end

        scale         = TkScale.new(frame).pack
        scale.from    = @minimum
        scale.to      = @maximum
        scale.command = proc do
          block.call(scale.value)
        end
        block.call(scale.value)
      end
    end

    class TextWidget
      def initialize(frame, args=nil, &block)
        entry = TkEntry.new(frame).pack
        entry.bind('KeyRelease') do
          block.call(entry.value)
        end
        block.call(entry.value)
      end
    end

    class ComputedWidget
      attr_accessor :variable

      def initialize(frame, args=nil, &block)
        @variable = TkVariable.new

        entry              = TkEntry.new(frame).pack
        entry.textvariable = variable
        entry.state        = 'disabled'
      end

      def set_value(value)
        @variable.value = value
      end
    end
  end
end