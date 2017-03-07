module Ast
  module Visitable
    def accept(visitor)
      visitor.visit(self)
    end
  end

  module Evaluatable
    def eval
      raise NotImplementedError
    end
  end

  class Form < Struct.new(:identifier, :body)
    include Visitable
  end

  class Question < Struct.new(:string, :type, :identifier, :value)
    include Visitable
  end

  class IfStatement < Struct.new(:condition, :if_true, :if_false)
    include Visitable
  end

  class Identifier < Struct.new(:name)
  end

  class String < Struct.new(:value)
  end

  class Integer < Struct.new(:value)
  end

  class Boolean < Struct.new(:value)
  end

  class Type < Struct.new(:name)
  end

  class Multiplication < Struct.new(:left, :right)
  end

  class Division < Struct.new(:left, :right)
  end

  class Addition < Struct.new(:left, :right)
  end

  class Subtraction < Struct.new(:left, :right)
  end
end
