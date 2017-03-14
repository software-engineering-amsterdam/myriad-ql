module UI.QLSApp exposing (Model, Msg, init, update, view)

import Html exposing (Html, div, text, h3, ul, li, a)
import Html.Events exposing (onClick)
import Html.Attributes exposing (class, attribute)
import UI.QLSFormRenderer as FormRenderer
import UI.QLInput as QLInput
import UI.QLSInput as QLSInput


type Tab
    = QLTab
    | QLSTab
    | Preview


type alias Model =
    { qlInput : QLInput.Model
    , qlsInput : QLSInput.Model
    , formRenderer : Maybe FormRenderer.Model
    , activeTab : Tab
    }


type Msg
    = FormDslInputMsg QLInput.Msg
    | QLSInputMsg QLSInput.Msg
    | FormRendererMsg FormRenderer.Msg
    | ChangeTab Tab


init : Model
init =
    let
        qlInput =
            QLInput.init

        qlsInput =
            QLSInput.init (QLInput.asForm qlInput)

        formRenderer =
            Maybe.map2 FormRenderer.init (QLInput.asForm qlInput) (QLSInput.asStyleSheet qlsInput)
    in
        { qlInput = qlInput
        , qlsInput = qlsInput
        , formRenderer = formRenderer
        , activeTab = Preview
        }


update : Msg -> Model -> Model
update msg model =
    case msg of
        ChangeTab newTab ->
            { model | activeTab = newTab }

        FormDslInputMsg subMsg ->
            let
                newQLInput =
                    QLInput.update subMsg model.qlInput

                maybeNewForm =
                    QLInput.asForm newQLInput

                newQLSInput =
                    QLSInput.setForm maybeNewForm model.qlsInput

                maybeNewStyleSheet =
                    QLSInput.asStyleSheet newQLSInput
            in
                { model
                    | qlInput = newQLInput
                    , qlsInput = newQLSInput
                    , formRenderer = Maybe.map2 FormRenderer.init maybeNewForm maybeNewStyleSheet
                }

        QLSInputMsg subMsg ->
            let
                maybeForm =
                    QLInput.asForm model.qlInput

                newQLSInput =
                    QLSInput.update subMsg model.qlsInput

                maybeNewStyleSheet =
                    QLSInput.asStyleSheet newQLSInput
            in
                { model
                    | qlsInput = QLSInput.update subMsg model.qlsInput
                    , formRenderer = Maybe.map2 FormRenderer.init maybeForm maybeNewStyleSheet
                }

        FormRendererMsg subMsg ->
            { model | formRenderer = Maybe.map (FormRenderer.update subMsg) model.formRenderer }


view : Model -> Html Msg
view model =
    div [ class "container" ]
        [ tabMenu model.activeTab
        , case model.activeTab of
            QLTab ->
                div []
                    [ h3 [] [ text "QL Input" ]
                    , QLInput.view model.qlInput |> Html.map FormDslInputMsg
                    ]

            QLSTab ->
                div []
                    [ h3 [] [ text "QLS Input" ]
                    , QLSInput.view model.qlsInput |> Html.map QLSInputMsg
                    ]

            Preview ->
                model.formRenderer
                    |> Maybe.map (FormRenderer.view >> Html.map FormRendererMsg)
                    |> Maybe.withDefault (div [] [])
        ]


tabMenu : Tab -> Html Msg
tabMenu currentlyActive =
    ul
        [ class "nav nav-tabs" ]
        (List.map
            (\( tab, name ) ->
                tabItem (currentlyActive == tab) tab name
            )
            availableTabItems
        )


availableTabItems : List ( Tab, String )
availableTabItems =
    [ ( QLTab, "QL" )
    , ( QLSTab, "QLS" )
    , ( Preview, "Preview" )
    ]


tabItem : Bool -> Tab -> String -> Html Msg
tabItem isActive goTo name =
    li
        [ attribute "role" "presentation"
        , class
            (if isActive then
                "active"
             else
                ""
            )
        ]
        [ a
            [ onClick (ChangeTab goTo) ]
            [ text name ]
        ]
