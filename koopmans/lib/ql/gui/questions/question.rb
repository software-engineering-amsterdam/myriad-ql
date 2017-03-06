module QL
  module GUI
    class Question
      attr_accessor :gui
      attr_accessor :label
      attr_accessor :frame
      attr_accessor :enabled
      attr_accessor :variable
      attr_accessor :condition

      def initialize(args)
        @question                               = args[:question]
        @gui                                    = args[:gui]
        @label                                  = @question.label
        @condition                              = @question.condition
        @enabled                                = true
        @variable                               = QL::GUI::Variable.new
        @gui.questions[@question.variable.name] = self

        Frame.new(question: self)
        Label.new(question: self)

        check_condition
      end

      def value
        @variable.eval
      end

      def to_json
        { @label => value }
      end

      def reload
        check_condition
      end

      def check_condition
        eval(@condition.eval.to_s) ? enable : disable if @condition
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