# Patches buggy behaviour when setting a Numeric type to a TkVariable. The
# original implementation checks whether the given type is exactly Numeric,
# case that does not cover Fixnum/Bignum
class TkVariable
  alias original_set_default_value_type_core _set_default_value_type_core

  def _set_default_value_type_core(type, idxs)
    type = :numeric if type && type < Numeric
    original_set_default_value_type_core(type, idxs)
  end
end
