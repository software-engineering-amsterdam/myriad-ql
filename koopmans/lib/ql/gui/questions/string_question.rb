module QL
  module GUI
    class StringQuestion < TextQuestion
      include AST

      def initialize(args)
        super
        @variable.value = ("")
        @variable.type  = StringType
        @previous_value = value
      end
    end
  end
end