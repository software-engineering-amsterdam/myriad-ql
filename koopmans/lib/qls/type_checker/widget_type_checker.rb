module QLS
  module TypeChecker
    class WidgetTypeChecker
      def visit_stylesheet(stylesheet, collected_data)
        @variable_type_map = collected_data
        stylesheet.pages.map { |page| page.accept(self) }
      end

      def visit_page(page)
        page.body.map { |element| element.accept(self) }
      end

      def visit_section(section, _)
        section.body.map { |element| element.accept(self) }
      end

      def visit_question(question, _)
        question_type = @variable_type_map[question.variable.name]
        question.properties.map { |property| property.accept(self, question_type) }
      end

      def visit_default(default, _)
        default.properties.map { |property| property.accept(self, default.type) }
      end

      def visit_slider_widget(slider_widget, type)
        check_compatibility(slider_widget, type, [QL::AST::IntegerType, QL::AST::MoneyType, QL::AST::DecimalType])
      end

      def visit_spinbox_widget(spinbox_widget, type)
        check_compatibility(spinbox_widget, type, [QL::AST::IntegerType, QL::AST::MoneyType, QL::AST::DecimalType])
      end

      def visit_text_widget(text_widget, type)
        check_compatibility(text_widget, type, [QL::AST::IntegerType, DateType, QL::AST::DecimalType, StringType, QL::AST::MoneyType])
      end

      def visit_radio_widget(radio_widget, type)
        check_compatibility(radio_widget, type, [QL::AST::BooleanType])
      end

      def visit_checkbox_widget(checkbox_widget, type)
        check_compatibility(checkbox_widget, type, [QL::AST::BooleanType])
      end

      def visit_dropdown_widget(dropdown_widget, type)
        check_compatibility(dropdown_widget, type, [QL::AST::BooleanType])
      end

      def visit_property(_, _) end

      def check_compatibility(widget, type, compatible_types)
        return if compatible_types.include?(type.class)
        NotificationTable.store(Notification::Error.new("#{type} is incompatible with #{widget}"))
      end
    end
  end
end
