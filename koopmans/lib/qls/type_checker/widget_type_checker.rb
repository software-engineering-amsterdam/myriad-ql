module QLS
  module TypeChecker
    class WidgetTypeChecker
      def initialize(variable_type_map)
        @variable_type_map = variable_type_map
      end

      def visit_stylesheet(stylesheet, _)
        stylesheet.pages.each { |page| page.accept(self) }
      end

      def visit_page(page)
        page.body.each { |element| element.accept(self) }
      end

      def visit_section(section, _)
        section.body.each { |element| element.accept(self) }
      end

      def visit_question(question, _)
        question_type = @variable_type_map[question.variable.name]
        question.properties.each { |property| property.accept(self, question_type) }
      end

      def visit_default(default, _)
        default.properties.each { |property| property.accept(self, default.type) }
      end

      def visit_slider_widget(slider_widget, type)
        compatible_types = [QL::AST::IntegerType, QL::AST::MoneyType, QL::AST::DecimalType]
        check_compatibility(slider_widget, type, compatible_types)
      end

      def visit_spinbox_widget(spinbox_widget, type)
        compatible_types = [QL::AST::IntegerType, QL::AST::MoneyType, QL::AST::DecimalType]
        check_compatibility(spinbox_widget, type, compatible_types)
      end

      def visit_text_widget(text_widget, type)
        compatible_types = [QL::AST::IntegerType, QL::AST::DateType, QL::AST::DecimalType, QL::AST::StringType, QL::AST::MoneyType]
        check_compatibility(text_widget, type, compatible_types)
      end

      def visit_radio_widget(radio_widget, type)
        compatible_types = [QL::AST::BooleanType]
        check_compatibility(radio_widget, type, compatible_types)
      end

      def visit_checkbox_widget(checkbox_widget, type)
        compatible_types = [QL::AST::BooleanType]
        check_compatibility(checkbox_widget, type, compatible_types)
      end

      def visit_dropdown_widget(dropdown_widget, type)
        compatible_types = [QL::AST::BooleanType]
        check_compatibility(dropdown_widget, type, compatible_types)
      end

      def visit_width(_, _) end

      def visit_font(_, _) end

      def visit_fontsize(_, _) end

      def visit_color(_, _) end

      def check_compatibility(widget, type, compatible_types)
        return if compatible_types.include?(type.class)
        NotificationTable.store(Notification::Error.new("incompatible types at #{widget}"))
      end
    end
  end
end
