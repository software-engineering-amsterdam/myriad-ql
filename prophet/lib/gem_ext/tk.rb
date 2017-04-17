# Patches buggy behaviour when setting a Numeric type to a TkVariable. The
# original implementation checks whether the given type is exactly Numeric,
# case that does not cover Fixnum/Bignum
class TkVariable
  alias_method :original_set_default_value_type_core, :_set_default_value_type_core

  def _set_default_value_type_core(type, idxs)
    if type && type < Numeric
      type = :numeric
    end
    original_set_default_value_type_core(type, idxs)
  end
end
