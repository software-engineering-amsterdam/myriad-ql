module QLS.AST.Widget exposing (widgetFromConfiguration)

import QLS.AST exposing (Configuration(..), Widget)


widgetFromConfiguration : Configuration -> Maybe Widget
widgetFromConfiguration c =
    case c of
        SingleConfig widget ->
            Just widget

        MultiConfig _ widgetMaybe ->
            widgetMaybe
