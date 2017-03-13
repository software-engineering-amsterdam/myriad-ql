module QL
  module GUI
    class QuestionFrame
      # include Widgets
      attr_reader :enabled, :tk_frame

      def initialize(ast_question, condition=nil)
        @ast_question = ast_question
        @condition    = condition
        @enabled      = true
      end

      def render(row_position, &block)
        @block = block
        @tk_frame = TkFrame.new.grid(row: row_position)
        Label.new(@tk_frame, label)
        create_widget
        reload
      end

      def create_widget
        # create_radio_widget(@tk_frame) do |changed_value|
        #   @value = changed_value
        #   value_changed
        # end

        widget_type.new(@tk_frame) do |changed_value|
          @value = changed_value
          value_changed
        end
      end

      def value_changed
        store_value
        # yield
        if @block
          @block.call
        end
      end

      def store_value
        QuestionTable.store(variable_name, literal_type.new(@value))
      end

      def reload
        if @condition
          check_condition
        end
      end

      def check_condition
        @condition.accept(Evaluator.new).to_value ? enable : disable
      end

      def enable
        @tk_frame.grid
        @enabled = true
      end

      def disable
        @tk_frame.grid_remove
        @enabled = false
      end

      def to_json
        { label => @value }
      end


      # helpers
      def variable_name
        @ast_question.variable.name
      end

      def label
        @ast_question.label.to_value
      end

      def literal_type
        @ast_question.type.literal_type
      end

      def widget_type
        @ast_question.type.widget
      end
    end
  end
end