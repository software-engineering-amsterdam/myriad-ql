module UI.Widget.Slider exposing (..)

import Html exposing (Html, div, input, span, text)
import Html.Attributes as HA exposing (type_, class, step, value, title, disabled)
import Html.Events exposing (onInput)
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)
import UI.Widget.Values as Values


type alias SliderProperties =
    { min : Int
    , max : Int
    }


view : SliderProperties -> WidgetContext msg -> Html msg
view sliderProperties { identifier, env, onChange, editable } =
    let
        currentValue =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asInt

        ( start, end ) =
            startEndPair sliderProperties
    in
        renderSlider
            editable
            (toString start)
            (toString end)
            (Maybe.withDefault "" <| Maybe.map toString currentValue)
            (Values.parseIntegerInput >> onChange)


renderSlider : Bool -> String -> String -> String -> (String -> msg) -> Html msg
renderSlider editable start end currentValue onChange =
    div [ class "row" ]
        [ div [ class "col-md-2 col-sm-2 col-xs-2" ]
            [ span [] [ text start ]
            ]
        , div [ class "col-md-8 col-sm-8 col-xs-6" ]
            [ input
                [ type_ "range"
                , HA.min start
                , HA.max end
                , step "1"
                , value currentValue
                , title ("Value: " ++ currentValue)
                , onInput onChange
                , disabled (not editable)
                ]
                []
            ]
        , div [ class "col-md-2 col-sm-2 col-xs-2" ]
            [ span [] [ text end ]
            ]
        ]


startEndPair : SliderProperties -> ( Int, Int )
startEndPair props =
    if props.min < props.max then
        ( props.min, props.max )
    else
        ( props.max, props.min )
