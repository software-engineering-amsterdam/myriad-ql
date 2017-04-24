form invalidQL
  "Normal question"
    number normalQuestion

  "Duplicate identifier"
    number normalQuestion

  "Undefined identifier"
    text isDefined => undefinedIdentifier

  "Invalid operands, part one"
    number invalidOne => "string assigned to number"

  "Invalid operands, part two"
    number invalidTwo => (1 + "invalid" * 100)

  if true + 1
    "Invalid operands, part three"
      number invalidThree => 1
  end

  "Direct cyclic dependency"
    number directCycle => directCycle

  "Indirect cyclic dependency, part one"
    text indirectCycleOne => indirectCycleTwo

  "Indirect cyclic dependency, part two"
    text indirectCycleTwo => indirectCycleOne
end
