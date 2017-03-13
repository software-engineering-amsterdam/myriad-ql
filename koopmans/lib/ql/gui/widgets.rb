module QL
  module GUI
    class Label
      def initialize(tk_frame, label)
        tk_label      = TkLabel.new(tk_frame).pack
        tk_label.text = label
      end
    end

    class SubmitButton
      def initialize(position, &block)
        button         = TkButton.new.grid(row: position)
        button.text    = 'Submit'
        button.command = proc { block.call}
      end
    end

    class RadioWidget
      def initialize(tk_frame, options=nil, &block)
        shared_variable = TkVariable.new(true)

        if options
          @true_label  = options[:true_value]
          @false_label = options[:false_value]
        end

        radio_button          = TkRadioButton.new(tk_frame).pack
        radio_button.text     = @true_label
        radio_button.value    = true
        radio_button.variable = shared_variable
        radio_button.command  = proc { block.call(shared_variable.bool) }

        radio_button          = TkRadioButton.new(tk_frame).pack
        radio_button.text     = @false_label
        radio_button.value    = false
        radio_button.variable = shared_variable
        radio_button.command  = proc { block.call(shared_variable.bool) }

        block.call(shared_variable.bool)
      end
    end

    class CheckboxWidget
      def initialize(tk_frame, options=nil, &block)
        variable = TkVariable.new(true)

        check_button          = TkCheckButton.new(tk_frame).pack
        check_button.variable = variable
        check_button.command  = proc { block.call(variable.bool) }

        block.call(variable.bool)
      end
    end

    class DropdownWidget
      def initialize(tk_frame, options=nil, &block)
        @true_label  = 'true'
        @false_label = 'false'

        if options
          @true_label  = options[:true_value]
          @false_label = options[:false_value]
        end

        combobox        = Tk::Tile::Combobox.new(tk_frame).pack
        combobox.values = [@true_label, @false_label]
        combobox.value  = @true_label
        combobox.bind('<ComboboxSelected>') { block.call(to_value(combobox.value)) }

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
      def initialize(tk_frame, options=nil, &block)
        @minimum = 0
        @maximum = 100

        if options
          @minimum = options[:minimum]
          @maximum = options[:maximum]
        end

        spinbox         = TkSpinbox.new(tk_frame).pack
        spinbox.from    = @minimum
        spinbox.to      = @maximum
        spinbox.value   = 0
        spinbox.command = proc { block.call(spinbox.value) }


        block.call(spinbox.value)
      end
    end

    # spinbox.increment  = 0.1
    # spinbox.format     = "%.2f"
    # spinbox.textvariable = variable

    class SliderWidget
      def initialize(tk_frame, options=nil, &block)
        @minimum = 0
        @maximum = 100

        if options
          @minimum = options[:minimum]
          @maximum = options[:maximum]
        end

        scale         = TkScale.new(tk_frame).pack
        scale.from    = @minimum
        scale.to      = @maximum
        scale.command = proc { block.call(scale.value) }

        block.call(scale.value)
      end
    end

    class TextWidget
      def initialize(tk_frame, options=nil, &block)
        entry = TkEntry.new(tk_frame).pack
        entry.bind('KeyRelease') do
          block.call(entry.value)
        end
        block.call(entry.value)
      end
    end

    class ComputedWidget
      def initialize(tk_frame, options=nil, &block)
        @variable = TkVariable.new

        entry              = TkEntry.new(tk_frame).pack
        entry.textvariable = @variable
        entry.state        = 'disabled'
      end

      def set_value(value)
        @variable.value = value
      end
    end
  end
end