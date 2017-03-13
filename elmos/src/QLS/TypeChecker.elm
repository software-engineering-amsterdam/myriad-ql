module QLS.TypeChecker exposing (check)

import QL.AST exposing (Form)
import QLS.AST exposing (StyleSheet)
import QLS.TypeChecker.InvalidQuestionReferences as InvalidQuestionReferences
import QLS.TypeChecker.UnplacedQuestion as UnplacedQuestion
import QLS.TypeChecker.DuplicatePlacedQuestions as DuplicatePlacedQuestions
import QLS.TypeChecker.QuestionWidgetType as QuestionWidgetType
import QLS.TypeChecker.DefaultValueConfigWidgetType as DefaultValueConfigWidgetType
import QLS.TypeChecker.Messages exposing (Message)


check : Form -> StyleSheet -> List Message
check form styleSheet =
    List.concat
        [ InvalidQuestionReferences.check form styleSheet
        , UnplacedQuestion.check form styleSheet
        , DuplicatePlacedQuestions.check form styleSheet
        , QuestionWidgetType.check form styleSheet
        , DefaultValueConfigWidgetType.check form styleSheet
        ]
