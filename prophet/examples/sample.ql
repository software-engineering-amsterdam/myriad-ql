form festivalParticipationSurvey
  "Did you participate in a festival last year?"
    bool participatedLastYear

  if participatedLastYear
    "How many festivals did you participate in?"
      number numberOfFestivals

    "How much did you spend on average per tickets?"
      number spendingOnTickets => 1 + (100 * 2)

    "How much did you spend on average on the festival site?"
      number spendingAtFestival => 500

    "This means you've spent a total of:"
      number spendingTotal => (spendingOnTickets + spendingAtFestival)
  else
    "You really need to go to a festival. Type one festival you would like to attend:"
      text festivalName => "Tomorrowland, Defqon.1, ..."
  end
end
