module QLS
  module TypeChecker
    class WidgetChecker
      include Notification

      # gather all widgets with their variables and their associated types and check for errors
      # e.g. [{QL::AST::BooleanType=>[#<QLS::AST::CheckboxWidget:0x007fd5920c6970>]},
      #      {QL::AST::MoneyType=>[#<QLS::AST::SpinboxWidget:0x007fd59081e940>]}]
      def visit_stylesheet(stylesheet, form)
        @ql_types   = form.accept(QuestionVisitor.new).map { |question| [question.variable.name, question.type] }.to_h
        qls_widgets = stylesheet.pages.map { |page| page.accept(self) }.flatten.compact

        #TODO cleanup
        errors = []
        qls_widgets.each do |widget_hash|
          widget_hash.each do |type, widget_object|
            type = 'undefined' unless type
            errors.push(Notification.new("#{widget_object.class} can not be used with #{type}")) unless widget_object.is_compatible_with.include?(type)
          end
        end
        errors.uniq
      end

      def visit_page(page)
        page.block.map { |element| element.accept(self) }
      end

      def visit_section(section)
        section.block.map { |element| element.accept(self) }
      end

      def visit_question(question)
        { @ql_types[question.variable.name] => question.properties[:widget] } if question.properties
      end

      def visit_default(default)
        { default.type => default.properties[:widget] } if default.properties
      end

      def visit_widget(widget)
        widget
      end

      def visit_property(_)
      end
    end
  end
end