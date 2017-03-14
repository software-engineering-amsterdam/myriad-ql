module UI.Widget.Slider exposing (..)

import Html exposing (Html, div, input, span, text)
import Html.Attributes as HA exposing (type_, class, step, value, title)
import Html.Events exposing (onInput)
import UI.Widget.Base exposing (WidgetContext)
import QL.Environment as Environment
import QL.Values as Values exposing (Value)


type alias SliderProperties =
    { min : Int
    , max : Int
    }


view : SliderProperties -> WidgetContext msg -> Html msg
view sliderProperties { identifier, env, onChange } =
    let
        currentValue =
            Environment.getFormValue identifier env
                |> Maybe.andThen Values.asInt

        ( start, end ) =
            startEndPair sliderProperties
    in
        renderSlider
            (toString start)
            (toString end)
            (Maybe.withDefault "" <| Maybe.map toString currentValue)
            (parseIntegerInput >> onChange)


renderSlider : String -> String -> String -> (String -> msg) -> Html msg
renderSlider start end currentValue onChange =
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


parseIntegerInput : String -> Value
parseIntegerInput =
    String.toInt
        >> Result.map Values.int
        >> Result.withDefault Values.undefined
