module QuestionTypeTable
  @storage = {}

  module_function

  def find(id)
    @storage[id]
  end

  def store(id, value)
    @storage[id] = value
  end
end