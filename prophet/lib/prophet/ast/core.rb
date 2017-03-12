module Prophet
  module Ast
    class Form < Node.new(:identifier, :body)
    end

    class Question < Node.new(:string, :type, :identifier, :value)
    end

    class IfStatement < Node.new(:condition, :if_true, :if_false)
    end

    class Identifier < Node.new(:name)
      def eval(context)
        context[name.to_s]
      end
    end

    class Type < Node.new(:name)
    end
  end
end
