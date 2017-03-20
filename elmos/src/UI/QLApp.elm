module UI.QLApp exposing (Model, Msg, init, update, view)

import Html exposing (Html, div, text, h3, ul, li, a)
import Html.Events exposing (onClick)
import Html.Attributes exposing (class, attribute)
import UI.QLFormRenderer as FormRenderer
import UI.QLInput as QLInput


type Tab
    = QLTab
    | Preview


type alias Model =
    { qlInput : QLInput.Model
    , formRenderer : Maybe FormRenderer.Model
    , activeTab : Tab
    }


type Msg
    = FormDslInputMsg QLInput.Msg
    | FormRendererMsg FormRenderer.Msg
    | ChangeTab Tab


init : Model
init =
    let
        formDslInput =
            QLInput.init
    in
        { qlInput = formDslInput
        , formRenderer = Maybe.map FormRenderer.init (QLInput.asForm formDslInput)
        , activeTab = QLTab
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
            in
                { model
                    | qlInput = newQLInput
                    , formRenderer = Maybe.map FormRenderer.init maybeNewForm
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
