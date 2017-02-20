module Parser.Expression exposing (expression)

import AST
    exposing
        ( Location
        , Expression(Integer, Decimal, ParensExpression, Var, Str, Boolean, LogicExpression, ComparisonExpression, RelationExpression, ArithmeticExpression)
        , Logic(And, Or)
        , Comparison(Equal, NotEqual)
        , Relation(GreaterThanOrEqual, LessThanOrEqual, LessThan, GreaterThan)
        , Operator(Plus, Minus, Multiply, Divide)
        )
import Combine exposing (Parser, chainl, choice, lazy, parens, succeed, (<$), (<$>), (*>), (<*), (<*>), (<|>))
import Combine.Num exposing (int, float)
import Combine.Extra exposing (trimmed, stringAs)
import List exposing (foldr)
import Parser.Token exposing (identifier, quotedString, withLoc, parseLoc)


type alias BinaryOperator =
    Expression -> Expression -> Expression


expression : Parser s Expression
expression =
    lazy <| \() -> foldr chainl atom precedenceOrderedOperators


precedenceOrderedOperators : List (Parser s BinaryOperator)
precedenceOrderedOperators =
    List.map trimmed <|
        [ orOp
        , andOp
        , comparisonOp
        , relationalOp
        , addOp
        , multiplyOp
        ]


orOp : Parser s BinaryOperator
orOp =
    defineBinary "||" LogicExpression Or


andOp : Parser s BinaryOperator
andOp =
    defineBinary "&&" LogicExpression And


defineBinary : String -> (a -> Location -> BinaryOperator) -> a -> Parser s BinaryOperator
defineBinary token expr operand =
    trimmed (withLoc (stringAs token (expr operand)))


comparisonOp : Parser s BinaryOperator
comparisonOp =
    choice
        [ defineBinary "==" ComparisonExpression Equal
        , defineBinary "!=" ComparisonExpression NotEqual
        ]


relationalOp : Parser s BinaryOperator
relationalOp =
    choice
        [ defineBinary ">=" RelationExpression GreaterThanOrEqual
        , defineBinary "<=" RelationExpression LessThanOrEqual
        , defineBinary ">" RelationExpression GreaterThan
        , defineBinary "<" RelationExpression LessThan
        ]


addOp : Parser s BinaryOperator
addOp =
    choice
        [ defineBinary "+" ArithmeticExpression Plus
        , defineBinary "-" ArithmeticExpression Minus
        ]


multiplyOp : Parser s BinaryOperator
multiplyOp =
    choice
        [ defineBinary "*" ArithmeticExpression Multiply
        , defineBinary "/" ArithmeticExpression Divide
        ]


atom : Parser s Expression
atom =
    lazy <| \() -> anyAtom


anyAtom : Parser s Expression
anyAtom =
    lazy <|
        \() ->
            choice <|
                [ integerAtom
                , decimalAtom
                , stringAtom
                , booleanAtom
                , parensAtom
                , varAtom
                ]


integerAtom : Parser s Expression
integerAtom =
    succeed Integer
        <*> parseLoc
        <*> int


decimalAtom : Parser s Expression
decimalAtom =
    succeed Decimal
        <*> parseLoc
        <*> float


stringAtom : Parser s Expression
stringAtom =
    succeed Str
        <*> parseLoc
        <*> quotedString


varAtom : Parser s Expression
varAtom =
    Var <$> identifier


booleanAtom : Parser s Expression
booleanAtom =
    succeed Boolean
        <*> parseLoc
        <*> (stringAs "true" True <|> stringAs "false" False)


parensAtom : Parser s Expression
parensAtom =
    succeed ParensExpression
        <*> parseLoc
        <*> parens (trimmed expression)
