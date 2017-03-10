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
      # attr_accessor :variable # TODO remove

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
        @widget = @question.type.widget.new(self)
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
          # p condition.accept(TypeChecker::Evaluator.new)
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

      def store_value
        literal_type = question.type.literal_type
        QuestionTable.store(question.variable.name, literal_type.new(@widget.value))
        gui.reload_questions
      end
    end
  end
end