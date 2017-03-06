package qls.astnodes.widgets.widgettypes;

/**
 * Created by LGGX on 03-Mar-17.
 */
public interface WidgetType {

    <T> T accept(WidgetTypeInterface<T> visitor);
}
