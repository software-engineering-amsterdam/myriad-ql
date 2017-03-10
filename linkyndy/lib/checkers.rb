module Checkers
  class Base
    def check
      raise NotImplementedError
    end

    def visit_form(node, parent)
    end

    def visit_question(node, parent)
    end

    def visit_if_statement(node, parent)
    end

    def visit_identifier(node, parent)
    end

    def visit_string_literal(node, parent)
    end

    def visit_integer_literal(node, parent)
    end

    def visit_boolean_literal(node, parent)
    end

    def visit_type(node, parent)
    end

    def visit_unary_expression(node, parent)
    end

    def visit_binary_expression(node, parent)
    end

    def visit_addition(node, parent)
    end

    def visit_subtraction(node, parent)
    end

    def visit_multiplication(node, parent)
    end

    def visit_division(node, parent)
    end
  end

  class DuplicateIdentifiers < Base
    attr_reader :bag

    def initialize
      @bag = []
    end

    def check
      bag.group_by(&:to_s).values.select { |identifiers| identifiers.count > 1 }.each do |identifiers|
        puts error_formatter(identifiers)
      end
    end

    def error_formatter(items)
      "Identifier `#{items.first}` is defined multiple times (on #{items.map { |i| i.line_and_column.join(':') }.join(', ')})"
    end

    def visit_identifier(node, parent)
      bag << node.name if Ast::Form === parent || Ast::Question === parent
    end
  end

  class UndefinedIdentifiers < Base
    attr_reader :defined, :used

    def initialize
      @defined = []
      @used = []
    end

    def check
      undefined = used.reject do |identifier|
        defined.map(&:to_s).include? identifier.to_s
      end
      undefined.each do |identifier|
        puts error_formatter(identifier)
      end
    end

    def error_formatter(item)
      "Identifier `#{item}` is not defined (used on #{item.line_and_column.join(':')})"
    end

    def visit_identifier(node, parent)
      case parent
      when Ast::Form, Ast::Question
        defined << node.name
      when Ast::UnaryExpression, Ast::BinaryExpression
        used << node.name
      end
    end
  end
