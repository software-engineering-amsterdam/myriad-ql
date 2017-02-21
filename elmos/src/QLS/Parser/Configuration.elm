module QLS.Parser.Configuration exposing (..)

import Combine exposing (..)
import QLS.AST exposing (Configuration(SingleConfig, MultiConfig), ConfigItem(StyleConfig, WidgetConfig))
import QLS.Parser.Style exposing (style)
import QLS.Parser.Widget exposing (widget)
import Combine.Extra exposing (whitespace1)


configuration : Parser state Configuration
configuration =
    or
        (SingleConfig <$> configItem)
        (MultiConfig <$> configItemBlock)


configItemBlock : Parser state (List ConfigItem)
configItemBlock =
    braces (sepBy whitespace1 configItem)


configItem : Parser state ConfigItem
configItem =
    choice
        [ StyleConfig <$> style
        , WidgetConfig <$> (string "widget" *> whitespace1 *> widget)
        ]
