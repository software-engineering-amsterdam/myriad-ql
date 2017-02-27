module QL
  module GUI
    class TextQuestion < Question
      attr_accessor :previous_value

      def initialize(args)
        super
        create_entry
      end

      def create_entry
        entry = TkEntry.new(@frame).pack
        entry.textvariable = @variable
        # every time enter is pressed
        entry.bind('Return') do
          # only if value changes
          unless @previous_value == value
            @gui.value_changed
            @previous_value = value
          end
        end
      end
    end
  end
end