module Values exposing (Value(Str, Boolean, Integer, Undefined), string, bool, int, undefined, asString, asBool, asInt)


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
int =
    Integer


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
