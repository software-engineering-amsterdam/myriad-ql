module UI.QLApp exposing (Model, Msg, init, update, view)

import Html exposing (Html, div, text, h3, ul, li, a)
import Html.Events exposing (onClick)
import Html.Attributes exposing (class, attribute)
import UI.FormRenderer as FormRenderer
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
        formDslInput =
            QLInput.init
    in
        { qlInput = formDslInput
        , qlsInput = QLSInput.init (QLInput.asForm formDslInput)
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

                newQLSInput =
                    QLSInput.setForm maybeNewForm model.qlsInput
            in
                { model
                    | qlInput = newQLInput
                    , qlsInput = newQLSInput
                    , formRenderer = Maybe.map FormRenderer.init maybeNewForm
                }

        QLSInputMsg subMsg ->
            let
                newQLSInput =
                    QLSInput.update subMsg model.qlsInput
            in
                { model | qlsInput = QLSInput.update subMsg model.qlsInput }

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
    [ ( QLTab, "Dsl Input" )
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
