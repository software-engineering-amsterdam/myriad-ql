# module QL
#   module GUI
#     class BooleanQuestion < Question
#       include AST
#
#       def initialize(args)
#         super
#         @variable.value = (true)
#         @variable.type  = BooleanType
#         create_radio_button('Yes', true)
#         create_radio_button('No', false)
#       end
#
#       def create_radio_button(text, value)
#         radio_button          = TkRadioButton.new(@frame).pack
#         radio_button.variable = @variable
#         radio_button.command  = proc { @gui.value_changed }
#         radio_button.text     = text
#         radio_button.value    = value
#       end
#     end
#   end
# end

module QL
  module GUI
    class BooleanQuestion < Question
      include AST

      def initialize(args)
        super
        @variable.value = (true)
        @variable.type  = BooleanType
        RadioWidget.new(question: self, true_value: 'JAAAA', false_value: 'NEEEE')
        # CheckboxWidget.new(question: self)
        DropdownWidget.new(question: self, true_value: 'JAAA', false_value: 'NEEE')
      end
    end

    class Widget
      attr_accessor :question

      def initialize(args)
        @question = args[:question]
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
        check_button = TkCheckButton.new(@question.frame).pack
        check_button.variable = @question.variable
        check_button.command = proc { @question.gui.value_changed }
      end
    end

    class DropdownWidget < Widget
      def initialize(args)
        super
        create_combobox(args[:true_value], args[:false_value])
      end

      def create_combobox(true_value, false_value)
        combobox = Tk::Tile::Combobox.new(@question.frame).pack
        combobox.values = [true_value, false_value]
        combobox.bind('<ComboboxSelected>') do
          @question.variable.value = true if combobox.value == true_value
          @question.variable.value = false if combobox.value == false_value
          @question.gui.value_changed
        end
        combobox.value = true_value
      end
    end

  end
end

