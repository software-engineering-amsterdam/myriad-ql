module QL
  module GUI
    class QuestionFrame
      attr_accessor :gui
      attr_accessor :question
      attr_accessor :frame
      attr_accessor :enabled
      attr_accessor :condition
      attr_accessor :variable # TODO remove

      def initialize(args)
        @question                               = args[:question]
        @gui                                    = args[:gui]
        @enabled                                = true
        @variable                               = QL::GUI::Variable.new
        @gui.questions[@question.variable.name] = self

        Frame.new(question_frame: self)
        Label.new(question_frame: self)

        check_condition
      end

      def value
        @variable.eval
      end

      def label
        @question.label
      end

      def to_json
        { label => value }
      end

      def reload
        check_condition
      end

      def check_condition
        eval(condition.eval.to_s) ? enable : disable if condition
      end

      def disable
        @frame.grid_remove
        @enabled = false
      end

      def enable
        @frame.grid
        @enabled = true
      end
    end
  end
end