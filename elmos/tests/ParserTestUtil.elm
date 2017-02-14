module ParserTestUtil
    exposing
        ( TestParserInput
        , testWithParser
        , testWithParserAndMap
        , parseToMaybe
        , removeLocationFromBlock
        , removeLocationFromFormItem
        , removeLocactionFromId
        , removeLocactionFromExpression
        )

import Combine exposing (Parser, end, (<*))
import Test exposing (Test, describe, test)
import AST exposing (..)
import Expect


type alias TestParserInput b =
    ( String, String, Maybe b )


testWithParser : Parser () b -> String -> List ( String, String, Maybe b ) -> Test
testWithParser p =
    testWithParserAndMap p identity


testWithParserAndMap : Parser () b -> (b -> c) -> String -> List ( String, String, Maybe c ) -> Test
testWithParserAndMap p f name tests =
    describe name
        (List.map (testParser p f) tests)


testParser : Parser () b -> (b -> c) -> TestParserInput c -> Test
testParser p f ( name, input, output ) =
    test name <|
        \() -> parseToMaybe p input |> Maybe.map f |> Expect.equal output


parseToMaybe : Parser () res -> String -> Maybe res
parseToMaybe p s =
    case Combine.parse (p <* end) s of
        Err _ ->
            Nothing

        Ok ( _, _, res ) ->
            Just res


emptyLoc : Location
emptyLoc =
    Location 0 0


removeLocationFromBlock : Block -> Block
removeLocationFromBlock =
    List.map removeLocationFromFormItem


removeLocationFromFormItem : FormItem -> FormItem
removeLocationFromFormItem formItem =
    case formItem of
        Field label id valueType ->
            Field label (removeLocactionFromId id) valueType

        ComputedField label id valueType expr ->
            ComputedField label (removeLocactionFromId id) valueType (removeLocactionFromExpression expr)

        IfThen expr thenBranch ->
            IfThen
                (removeLocactionFromExpression expr)
                (removeLocationFromBlock thenBranch)

        IfThenElse expr thenBranch elseBranch ->
            IfThenElse
                (removeLocactionFromExpression expr)
                (removeLocationFromBlock thenBranch)
                (removeLocationFromBlock elseBranch)


removeLocactionFromExpression : Expression -> Expression
removeLocactionFromExpression expr =
    case expr of
        Var x ->
            Var (removeLocactionFromId x)

        Str _ s ->
            Str emptyLoc s

        Integer _ x ->
            Integer emptyLoc x

        Boolean _ b ->
            Boolean emptyLoc b

        ParensExpression _ inner ->
            ParensExpression emptyLoc (removeLocactionFromExpression inner)

        ArithmeticExpression op _ l r ->
            ArithmeticExpression op emptyLoc (removeLocactionFromExpression l) (removeLocactionFromExpression r)

        RelationExpression op _ l r ->
            RelationExpression op emptyLoc (removeLocactionFromExpression l) (removeLocactionFromExpression r)

        LogicExpression op _ l r ->
            LogicExpression op emptyLoc (removeLocactionFromExpression l) (removeLocactionFromExpression r)

        ComparisonExpression op _ l r ->
            ComparisonExpression op emptyLoc (removeLocactionFromExpression l) (removeLocactionFromExpression r)


removeLocactionFromId : Id -> Id
removeLocactionFromId ( a, b ) =
    ( a, Location 0 0 )
