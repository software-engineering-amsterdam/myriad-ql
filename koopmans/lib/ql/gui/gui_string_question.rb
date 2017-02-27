module QL
  module GUI
    class GUIStringQuestion < GUITextQuestion
      include AST
      def initialize(args)
        super
        @variable.value = ("")
        @variable.type = StringType
        @previous_value = value
      end
    end
  end
end