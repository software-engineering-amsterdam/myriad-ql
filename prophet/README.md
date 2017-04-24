# Prophet

Prophet interprets a custom-tailored questionnaire DSL and launches an interactive GUI. It is a toy project implemented to showcase the steps of writing an interpreter using beautiful and idiomatic Ruby code.

The basic flavour of the DSL looks like this:

```
form festivalParticipationSurvey
  "Did you participate in a festival last year?"
    bool participatedLastYear

  if participatedLastYear
    "How many festivals did you participate in?"
      number numberOfFestivals

    "How much did you spend on average per tickets?"
      number spendingOnTickets

    "How much did you spend on average on the festival site?"
      number spendingAtFestival

    "This means you've spent a total of:"
      number spendingTotal => (spendingOnTickets + spendingAtFestival) # Wooah, that's pretty much, isn't it?
  else
    "You really need to go to a festival. Here are some examples:"
      text festivalName => "Tomorrowland, Defqon.1, ..."
  end
end

```

## Installation

Add this line to your application's Gemfile:

```ruby
gem 'prophet'
```

And then execute:

    $ bundle

Or install it yourself as:

    $ gem install prophet

## Usage

```ruby
Prophet.interpret('examples/sample.ql')
```

## Development

After checking out the repo, run `bin/setup` to install dependencies. Then, run `rake spec` to run the tests. You can also run `bin/console` for an interactive prompt that will allow you to experiment.

To install this gem onto your local machine, run `bundle exec rake install`. To release a new version, update the version number in `version.rb`, and then run `bundle exec rake release`, which will create a git tag for the version, push git commits and tags, and push the `.gem` file to [rubygems.org](https://rubygems.org).

### Style guide

```ruby
rubocop
reek
```
