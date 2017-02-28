module QL
  module GUI
    class IntegerQuestion < TextQuestion
      include AST

      def initialize(args)
        super
        @variable.value = (0)
        @variable.type  = IntegerType
        @previous_value = value
      end
    end
  end
end