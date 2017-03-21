module QL.Numbers exposing (isValidFloat, isValidInt)


isValidInt : Int -> Bool
isValidInt =
    not << isNanOrInfinite << toFloat


isValidFloat : Float -> Bool
isValidFloat =
    not << isNanOrInfinite


isNanOrInfinite : Float -> Bool
isNanOrInfinite x =
    isNaN x || isInfinite x
