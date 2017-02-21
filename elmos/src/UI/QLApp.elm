module UI.QLApp exposing (Model, Msg, init, update, view)

import Html exposing (Html, div, text, h3, form, pre, ul, li, a)
import Html.Events exposing (onClick)
import Html.Attributes exposing (class, attribute)
import UI.FormDslInput as FormDslInput
import UI.FormRenderer as FormRenderer


type Tab
    = DslInput
    | Preview


type alias Model =
    { formDslInput : FormDslInput.Model
    , formRenderer : Maybe FormRenderer.Model
    , activeTab : Tab
    }


type Msg
    = FormDslInputMsg FormDslInput.Msg
    | FormRendererMsg FormRenderer.Msg
    | ChangeTab Tab


init : Model
init =
    let
        formDslInput =
            FormDslInput.init
    in
        { formDslInput = formDslInput
        , formRenderer = Maybe.map FormRenderer.init (FormDslInput.asForm formDslInput)
        , activeTab = DslInput
        }


update : Msg -> Model -> Model
update msg model =
    case msg of
        ChangeTab newTab ->
            { model | activeTab = newTab }

        FormDslInputMsg subMsg ->
            { model | formDslInput = FormDslInput.update subMsg model.formDslInput }

        FormRendererMsg subMsg ->
            { model | formRenderer = Maybe.map (FormRenderer.update subMsg) model.formRenderer }


view : Model -> Html Msg
view model =
    div [ class "container" ]
        [ tabMenu model.activeTab
        , case model.activeTab of
            DslInput ->
                div []
                    [ h3 [] [ text "DSL Input" ]
                    , FormDslInput.view model.formDslInput |> Html.map FormDslInputMsg
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
    [ ( DslInput, "Dsl Input" )
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
