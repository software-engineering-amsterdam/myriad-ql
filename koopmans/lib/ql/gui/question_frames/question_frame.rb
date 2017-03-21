module QL
  module GUI
    class QuestionFrame
      include Callback
      attr_reader :name, :enabled, :condition
      attr_accessor :widget, :tk_frame

      def initialize(args)
        @name = args[:name]
        @label = args[:label]
        @literal_type = args[:literal_type]
        @widget = args[:widget]
        @condition = args[:condition]
        @enabled = true
        @tk_frame = TkFrame.new
      end

      def render
        pp @tk_frame.width
        @tk_frame.grid
        @label.render(@tk_frame)
        @widget.render(@tk_frame)

        store_default_value
        listen_to_widget
      end

      def store_default_value
        @value = @widget.default_value
        store_value
      end

      def listen_to_widget
        @widget.listen do |changed_value|
          @value = changed_value
          store_value
          callback
        end
      end

      def store_value
        VariableTable.store(@name, @literal_type.new(@value))
      end

      def reload
        return unless @condition
        check_condition
      end

      def check_condition
        @condition.accept(Evaluator.new).value ? enable : disable
      end

      def enable
        @tk_frame.grid
        @enabled = true
      end

      def disable
        @tk_frame.grid_remove
        @enabled = false
      end

      def print
        "#{@label} #{@value}"
      end
    end
  end
end
