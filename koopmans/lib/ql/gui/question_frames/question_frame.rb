module QL
  module GUI
    class QuestionFrame
      include Callback
      attr_reader :name, :enabled, :condition, :widget

      def initialize(args)
        @name = args[:name]
        @label = args[:label]
        @literal_type = args[:literal_type]
        @widget_type = args[:widget_type]
        @condition = args[:condition]
        @enabled = true
      end

      def render
        @tk_frame = TkFrame.new.grid
        Label.new(@tk_frame, @label)
        @widget = @widget_type.new(@tk_frame)

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
        if @condition
          check_condition
        end
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

      # TODO Open/closed principle formatting (https://subvisual.co/blog/posts/19-solid-principles-in-ruby)
      def to_json
        { label => @value }
      end
    end
  end
end