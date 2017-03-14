module Listener
  def callback(value=nil)
    if @block
      @block.call(value)
    end
  end

  def listen(&block)
    @block = block
  end
end

# module QL
#   module GUI
#     module Widget < Widgets
#       def create_radio_widget(tk_frame, options=nil)
#         shared_variable = TkVariable.new(true)
#
#         if options
#           @true_label  = options[:true_value]
#           @false_label = options[:false_value]
#         end
#
#         radio_button          = TkRadioButton.new(tk_frame).pack
#         radio_button.text     = @true_label
#         radio_button.value    = true
#         radio_button.variable = shared_variable
#         radio_button.command  = proc { callback(shared_variable.bool) }
#
#         radio_button          = TkRadioButton.new(tk_frame).pack
#         radio_button.text     = @false_label
#         radio_button.value    = false
#         radio_button.variable = shared_variable
#         radio_button.command  = proc { callback(shared_variable.bool) }
#
#         callback(shared_variable.bool)
#       end
#     end
#   end
# end

module QL
  module GUI
    class Label
      def initialize(tk_frame, label)
        tk_label      = TkLabel.new(tk_frame).pack
        tk_label.text = label
      end
    end

    class SubmitButton
      include Callback

      def initialize
        button         = TkButton.new.grid
        button.text    = 'Submit'
        button.command = proc { callback }
      end
    end

    class Widget
      include Callback
      attr_reader :default_value
    end

    class RadioWidget < Widget
      def initialize(tk_frame, options=nil)
        @default_value = true

        if options
          @true_label  = options[:true_value]
          @false_label = options[:false_value]
        end

        shared_variable = TkVariable.new(@default_value)

        radio_button          = TkRadioButton.new(tk_frame).pack
        radio_button.text     = @true_label
        radio_button.value    = true
        radio_button.variable = shared_variable
        radio_button.command  = proc { callback(shared_variable.bool) }

        radio_button          = TkRadioButton.new(tk_frame).pack
        radio_button.text     = @false_label
        radio_button.value    = false
        radio_button.variable = shared_variable
        radio_button.command  = proc { callback(shared_variable.bool) }
      end
    end

    class CheckboxWidget < Widget
      def initialize(tk_frame, options=nil)
        @default_value = true

        variable = TkVariable.new(@default_value)

        check_button          = TkCheckButton.new(tk_frame).pack
        check_button.variable = variable
        check_button.command  = proc { callback(variable.bool) }

        callback(variable.bool)
      end
    end

    class DropdownWidget < Widget
      def initialize(tk_frame, options=nil)
        @default_value = true

        @true_label  = 'true'
        @false_label = 'false'

        if options
          @true_label  = options[:true_value]
          @false_label = options[:false_value]
        end

        combobox        = Tk::Tile::Combobox.new(tk_frame).pack
        combobox.values = [@true_label, @false_label]
        combobox.value  = @true_label
        combobox.bind('<ComboboxSelected>') { callback(to_value(combobox.value)) }

        callback(to_value(combobox.value))
      end

      def to_value(textual_value)
        if textual_value == @true_label
          true
        else
          false
        end
      end
    end

    class SpinboxWidget < Widget
      def initialize(tk_frame, options=nil)
        @default_value = 0

        @minimum = 0
        @maximum = 100

        if options
          @minimum = options[:minimum]
          @maximum = options[:maximum]
        end

        spinbox         = TkSpinbox.new(tk_frame).pack
        spinbox.from    = @minimum
        spinbox.to      = @maximum
        spinbox.value   = @default_value
        spinbox.command = proc { callback(spinbox.value) }

        callback(spinbox.value)
      end
    end

    # spinbox.increment  = 0.1
    # spinbox.format     = "%.2f"
    # spinbox.textvariable = variable

    class SliderWidget < Widget
      def initialize(tk_frame, options=nil)
        @default_value = 0

        @minimum = 0
        @maximum = 100

        if options
          @minimum = options[:minimum]
          @maximum = options[:maximum]
        end

        scale         = TkScale.new(tk_frame).pack
        scale.from    = @minimum
        scale.to      = @maximum
        scale.command = proc { callback(scale.value) }

        callback(scale.value)
      end
    end

    class TextWidget < Widget
      def initialize(tk_frame, options=nil)
        @default_value = ''

        entry = TkEntry.new(tk_frame).pack
        entry.bind('KeyRelease') { callback(entry.value) }

        callback(entry.value)
      end
    end

    class ComputedWidget < Widget
      def initialize(tk_frame, options=nil)
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