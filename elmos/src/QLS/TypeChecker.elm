module QLS.TypeChecker exposing (check)

import QL.AST exposing (Form)
import QLS.AST exposing (StyleSheet)
import QLS.TypeChecker.InvalidQuestionReferences as InvalidQuestionReferences
import QLS.TypeChecker.UnplacedQuestion as UnplacedQuestion
import QLS.TypeChecker.DuplicatePlacedQuestions as DuplicatePlacedQuestions
import QLS.TypeChecker.Messages exposing (Message)


{-| TODO
 - [x] no references to questions that are not in the QL program
 - [x] all questions of the QL program are placed by the QLS program.
 - [ ] (default) widget assignments are compatible with question types (e.g. no radio button for integer widgets).
 - [ ] you cannot place a single question multiple times.
-}
check : Form -> StyleSheet -> List Message
check form styleSheet =
    List.concat
        [ InvalidQuestionReferences.check form styleSheet
        , UnplacedQuestion.check form styleSheet
        , DuplicatePlacedQuestions.check form styleSheet
        ]
