module Prophet
  module Widgets
    class Text
      attr_reader :name

      def initialize(name, value, state: 'normal')
        @name = name
        @variable = TkVariable.new
        @variable.value_type = value
        @state = state
      end

      def render(window)
        entry = TkEntry.new(window)
        entry.textvariable = variable
        entry.state = state
        entry.pack
      end

      def value
        variable.value
      end

      private

      attr_reader :variable, :state
    end
  end
end
