@aap = 'as'
module QL
  module GUI
    class QuestionFrame
      attr_accessor :gui
      attr_accessor :question
      attr_accessor :frame
      attr_accessor :enabled
      attr_accessor :condition
      attr_accessor :widget

      def initialize(gui, question, condition=nil)
        @question  = question
        @gui       = gui
        @condition = condition
        @enabled   = true

        gui.questions << self

        Frame.new(self)
        Label.new(self)

        create_widget
        store_value
      end

      def create_widget
        widget_type = @question.type.widget
        @widget = widget_type.new(self)
      end

      def label
        question.label.to_value
      end

      def value
        @widget.value
      end

      def to_json
        { label => value }
      end

      def reload
        check_condition
      end

      def check_condition
        if condition
          condition.accept(TypeChecker::Evaluator.new).to_value ? enable : disable
        end
      end

      def disable
        frame.grid_remove
        @enabled = false
      end

      def enable
        frame.grid
        @enabled = true
      end

      def value_changed
        store_value
        gui.reload_questions
      end

      def store_value
        QuestionTable.store(question.variable.name, literal_type.new(@widget.value))
      end

      def literal_type
        question.type.literal_type
      end
    end
  end
end