Questionnaire DSL implemented in Ruby
=====================================

Just a simple questionnaire DSL implemented in Ruby

Example
-------

```
# Forms have identifiers
form festivalParticipationSurvey
  "Did you participate in a festival last year?"
    # Fields have identifiers too
    bool participatedLastYear

  if (participatedLastYear)
    "How many festivals did you participate in?"
      number numberOfFestivals

    "How much did you spend on average per tickets?"
      number spendingOnTickets

    "How much did you spend on average on the festival site?"
      number spendingAtFestival

    "This means you've spent a total of:"
      # Fields can have (dynamic) values!
      number spendingTotal => (spendingOnTickets + spendingAtFestival)
  end
end
```

Style guide
-----------

```ruby
rubocop
```
