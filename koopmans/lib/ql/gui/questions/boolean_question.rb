module QL
  module GUI
    class BooleanQuestion < Question
      include AST

      def initialize(args)
        super
        @variable.value = true
        @variable.type  = BooleanType

        RadioWidget.new(question: self, true_value: 'JAAAA', false_value: 'NEEEE')
        # CheckboxWidget.new(question: self)
        # DropdownWidget.new(question: self, true_value: 'JAAA', false_value: 'NEEE')
      end
    end
  end
end

