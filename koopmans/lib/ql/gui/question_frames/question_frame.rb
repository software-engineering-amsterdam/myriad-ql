@aap = 'as'
module QL
  module GUI
    class QuestionFrame
      attr_accessor :gui
      attr_accessor :ast_question
      attr_accessor :frame
      attr_accessor :enabled
      attr_accessor :condition
      attr_accessor :value

      def initialize(ast_question, condition=nil)
        @ast_question  = ast_question
        @condition = condition
        @enabled = true
      end

      def render(gui, position)
        @gui = gui
        @frame = TkFrame.new.grid(row: position)
        Label.new(@frame, label)
        create_widget
      end

      def create_widget
        widget_type.new(@frame) do |changed_value|
          @value = changed_value
          value_changed
        end
      end

      def value_changed
        store_value
        gui.reload_questions
      end

      def store_value
        QuestionTable.store(variable_name, literal_type.new(value))
        p value
      end

      def reload
        check_condition
      end

      def check_condition
        if condition
          condition.accept(Evaluator.new).to_value ? enable : disable
        end
      end

      def enable
        @frame.grid
        @enabled = true
      end

      def disable
        @frame.grid_remove
        @enabled = false
      end

      def to_json
        { label => value }
      end


      # helpers
      def variable_name
        ast_question.variable.name
      end

      def label
        ast_question.label.to_value
      end

      def literal_type
        ast_question.type.literal_type
      end

      def widget_type
        ast_question.type.widget
      end
    end
  end
end