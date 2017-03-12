require_relative './utils/dependency_hash'

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

    def visit_text_literal(node, parent)
    end

    def visit_number_literal(node, parent)
    end

    def visit_bool_literal(node, parent)
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
    def initialize(ast)
      @ast = ast
    end

    def check
      defined_identifiers.group_by(&:to_s).values.select do |identifiers|
        identifiers.count > 1
      end.each do |identifiers|
        puts error_formatter(identifiers)
      end
    end

    def error_formatter(items)
      "Identifier `#{items.first}` is defined multiple times (on " \
      "#{items.map { |i| i.line_and_column.join(':') }.join(', ')})"
    end

    private

    attr_reader :ast

    def defined_identifiers
      ast.select do |node|
        Ast::Question === node || Ast::Form === node
      end.map do |question|
        question.identifier.name
      end
    end
  end

  class UndefinedIdentifiers < Base
    def initialize(ast)
      @ast = ast
    end

    def check
      ast.select do |node|
        Ast::Identifier === node
      end.reject do |identifier|
        defined_identifiers.include? identifier.name.to_s
      end.each do |identifier|
        puts error_formatter(identifier)
      end
    end

    def error_formatter(item)
      "Identifier `#{item.name}` is not defined (used on #{item.name.line_and_column.join(':')})"
    end

    private

    attr_reader :ast

    def defined_identifiers
      ast.select do |node|
        Ast::Question === node || Ast::Form === node
      end.map do |question|
        question.identifier.name.to_s
      end
    end
  end

  class CyclicDependencies < Base
    def initialize(ast)
      @ast = ast
    end

    def check
      dependency_hash.tsort
    rescue TSort::Cyclic
      puts error_formatter
    end

    def error_formatter
      "A cyclic dependency exists"
    end

    private

    attr_reader :ast, :dependency_hash

    def dependency_hash
      @dependency_hash ||= DependencyHash[
        ast.select do |node|
          Ast::Question === node && node.value
        end.map do |question|
          identifiers = question.value.select do |node|
            Ast::Identifier === node
          end
          [question.identifier.name.to_s, identifiers.map { |i| i.name.to_s }]
        end
      ]
    end
  end

  class InvalidOperands < Base
    def initialize(ast)
      @ast = ast
    end

    # Checks that, for each question having an assigned expression, all of the
    # expression's terminals (identifiers or literals) match the type of the
    # question
    def check
      ast.select do |node|
        Ast::Question === node && node.value
      end.each do |question|
        terminals = question.value.select do |expression|
          expression.children.count.zero?
        end
        next if terminals.empty?
        next if terminals.all? do |expression|
          case expression
          when Ast::Identifier
            type_mapping[expression.name.to_s] == question.type.name.to_s
          when Ast::TextLiteral
            question.type.name.to_s == 'text'
          when Ast::NumberLiteral
            question.type.name.to_s == 'number'
          when Ast::BoolLiteral
            question.type.name.to_s == 'bool'
          end
        end

        puts error_formatter(question)
      end
    end

    def error_formatter(question)
      "Expression attached to question `#{question.identifier.name}` " \
      "contains types other than `#{question.type.name}` (defined on " \
      "#{question.identifier.name.line_and_column.join(':')})"
    end

    private

    attr_reader :ast, :type_mapping

    def type_mapping
      @type_mapping ||= ast.select do |node|
        Ast::Question === node
      end.map do |question|
        [question.identifier.name.to_s, question.type.name.to_s]
      end.to_h
    end
  end
end
