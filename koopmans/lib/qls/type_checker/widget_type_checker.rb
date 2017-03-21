module QLS
  module TypeChecker
    class WidgetTypeChecker
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

      def check_compatibility(widget, type, compatible_types)
        return if compatible_types.include?(type.class)
        NotificationTable.store(Notification::Error.new("#{type} is incompatible with #{widget}"))
      end
    end
  end
end
