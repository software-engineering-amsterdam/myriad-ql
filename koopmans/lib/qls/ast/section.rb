module QLS
  module AST
    class Section
      attr_reader :block

      def initialize(block)
        @block = block
      end

      # def initialize(sections, questions, default_type_properties)
      #   @sections = sections
      #   @questions = questions
      #   @default_type_properties = default_type_properties
      # end
    end
  end
end