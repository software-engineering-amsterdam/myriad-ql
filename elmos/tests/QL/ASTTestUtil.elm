module QL.ASTTestUtil
    exposing
        ( loc
        , emptyLoc
        , removeLocationFromBlock
        , removeLocationFromFormItem
        , removeLocactionFromId
        , removeLocactionFromExpression
        )

import QL.AST exposing (..)


loc : Int -> Int -> Location
loc =
    Location


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

        Decimal _ x ->
            Decimal emptyLoc x

        Boolean _ b ->
            Boolean emptyLoc b

        ParensExpression _ inner ->
            ParensExpression emptyLoc (removeLocactionFromExpression inner)

        BinaryExpression op _ l r ->
            BinaryExpression op emptyLoc (removeLocactionFromExpression l) (removeLocactionFromExpression r)


removeLocactionFromId : Id -> Id
removeLocactionFromId ( a, _ ) =
    ( a, Location 0 0 )
