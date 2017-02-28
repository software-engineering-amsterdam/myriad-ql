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
        @gui       = args[:gui]
        @label     = args[:label]
        @condition = args[:condition]

        @enabled  = true
        @variable = QL::GUI::Variable.new
        @gui.questions[args[:id]] = self

        create_frame
        create_label
        check_condition
      end

      def create_frame
        @frame = TkFrame.new.grid(row: @gui.questions.size)
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
        @condition.eval ? enable : disable if @condition
      end

      def disable
        @frame.grid_remove
        @enabled = false
      end

      def enable
        @frame.grid
        @enabled = true
      end

      def create_label
        label      = TkLabel.new(@frame).pack
        label.text = @label
      end
    end
  end
end