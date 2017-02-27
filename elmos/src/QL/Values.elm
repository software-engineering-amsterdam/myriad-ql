module QL.Values exposing (Value, string, bool, int, float, undefined, asString, asBool, asInt, asFloat, isUndefined)


type Value
    = Str String
    | Boolean Bool
    | Integer Int
    | Decimal Float
    | Undefined


string : String -> Value
string =
    Str


bool : Bool -> Value
bool =
    Boolean


int : Int -> Value
int x =
    if isNanOrInfinite (toFloat x) then
        Undefined
    else
        Integer x


float : Float -> Value
float f =
    if isNanOrInfinite f then
        Undefined
    else
        Decimal f


undefined : Value
undefined =
    Undefined


isUndefined : Value -> Bool
isUndefined =
    (==) Undefined


isNanOrInfinite : Float -> Bool
isNanOrInfinite x =
    isNaN x || isInfinite x


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
