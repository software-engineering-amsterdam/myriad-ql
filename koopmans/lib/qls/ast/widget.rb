class SliderWidget
  attr_reader :min
  attr_reader :max

  def initialize(min, max)
    @min = min
    @max = max
  end
end

class SpinboxWidget

end

class TextWidget

end


class RadioWidget
  attr_reader :true_text
  attr_reader :false_text

  def initialize(true_text, false_text)
    @true_text = true_text
    @false_text = false_text
  end
end

class CheckboxWidget
  attr_reader :true_text

  def initialize(true_text)
    @true_text = true_text
  end
end

class DropdownWidget
  attr_reader :true_text
  attr_reader :false_text

  def initialize(true_text, false_text)
    @true_text = true_text
    @false_text = false_text
  end
end

end