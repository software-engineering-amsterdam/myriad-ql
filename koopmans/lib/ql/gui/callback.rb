module QL
  module GUI
    module Callback
      def callback(value=nil)
        if @block
          @block.call(value)
        end
      end

      def listen(&block)
        @block = block
      end
    end
  end
end