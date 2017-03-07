/**
 * GUIAbstractComponent.java.
 */

package ql.gui;

import ql.gui.formenvironment.values.Value;

public abstract class GUIAbstractComponent {

    private final GUIInterface guiInterface;

    public GUIAbstractComponent(GUIInterface guiInterface) {
        this.guiInterface = guiInterface;
    }

    protected void getNewChanges() {
        guiInterface.getGUIChanges(this);
    }

    public abstract Value getState();

    public abstract String getId();
}
