module QL
  module GUI
    module Callback
      def callback(value = nil)
        return unless @block
        @block.call(value)
      end

      def listen(&block)
        @block = block
      end
    end
  end
end
