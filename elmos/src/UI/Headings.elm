module UI.Headings exposing (Heading, init, deeper, header)

import Html exposing (Html, Attribute, node)


type Heading
    = Heading Level


type alias Level =
    Int


init : Heading
init =
    Heading 3


deeper : Heading -> Heading
deeper (Heading x) =
    Heading (x + 1)


header : Heading -> List (Attribute a) -> List (Html a) -> Html a
header (Heading level) =
    if level <= 5 then
        node ("h" ++ toString level)
    else
        node "h6"
