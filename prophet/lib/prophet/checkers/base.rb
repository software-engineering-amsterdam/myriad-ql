module Prophet
  module Checkers
    class Base
      def initialize(ast)
        @ast = ast
      end

      def check
        raise NotImplementedError
      end

      private

      attr_reader :ast
    end
  end
end
