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
