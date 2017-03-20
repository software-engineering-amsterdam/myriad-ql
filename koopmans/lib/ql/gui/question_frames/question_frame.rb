module QL
  module GUI
    class QuestionFrame
      include Callback
      attr_reader :enabled, :condition, :widget

      def initialize(name, label, type, widget_type, condition=nil, assignment=nil)
        @name = name
        @label = label
        @type = type
        @widget_type = widget_type
        @condition = condition
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
        value_changed
      end

      def listen_to_widget
        @widget.listen do |changed_value|
          @value = changed_value
          value_changed
        end
      end

      def value_changed
        store_value
        callback
      end

      def store_value
        VariableTable.store(@name, @type.new(@value))
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