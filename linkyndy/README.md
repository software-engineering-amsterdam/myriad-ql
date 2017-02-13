Questionnaire DSL implemented in Ruby
=====================================

Just a simple questionnaire DSL implemented in Ruby

Example
-------

```
# Forms have identifiers
form festivalParticipationSurvey do
  text "Did you participate in a festival last year?"
  # Fields have identifiers too
  bool participatedLastYear

  if participatedLastYear do
    text "How many festivals did you participate in?"
    number numberOfFestivals

    text "How much did you spend on average per tickets?"
    money spendingOnTickets

    text "How much did you spend on average on the festival site?"
    money spendingAtFestival

    text "This means you've spent a total of:"
    # Provide a default value for the money field; also make field readonly
    money value: { spendingOnTickets + spendingAtFestival }, readonly: true
  end
end
```

Style guide
-----------

```ruby
rubocop
```
