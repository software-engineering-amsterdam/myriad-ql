module Values exposing (Value(Str, Boolean, Undefined), string, bool, int, undefined, asString, asBool, asInt)


type Value
    = Str String
    | Boolean Bool
    | Integer Int
    | Undefined


string : String -> Value
string =
    Str


bool : Bool -> Value
bool =
    Boolean


int : Int -> Value
int x =
    if isNaN (toFloat x) then
        Undefined
    else if isInfinite (toFloat x) then
        Undefined
    else
        Integer x


undefined : Value
undefined =
    Undefined


asInt : Value -> Maybe Int
asInt value =
    case value of
        Integer b ->
            Just b

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
