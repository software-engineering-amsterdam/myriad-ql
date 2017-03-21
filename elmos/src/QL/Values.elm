module QL.Values
    exposing
        ( Value(Str, Boolean, Integer, Decimal, Undefined)
        , asString
        , asBool
        , asInt
        , asFloat
        , isUndefined
        )


type Value
    = Str String
    | Boolean Bool
    | Integer Int
    | Decimal Float
    | Undefined


isUndefined : Value -> Bool
isUndefined =
    (==) Undefined


asInt : Value -> Maybe Int
asInt value =
    case value of
        Integer b ->
            Just b

        _ ->
            Nothing


asFloat : Value -> Maybe Float
asFloat value =
    case value of
        Integer b ->
            Just (toFloat b)

        Decimal f ->
            Just f

        _ ->
            Nothing


asString : Value -> Maybe String
asString value =
    case value of
        Str b ->
            Just b

        _ ->
            Nothing


asBool : Value -> Maybe Bool
asBool value =
    case value of
        Boolean b ->
            Just b

        _ ->
            Nothing
