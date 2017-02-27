package QL.GUI;

import QL.semanticChecker.formDataStorage.valueData.values.Value;

/**
 * Created by LGGX on 22-Feb-17.
 */
public abstract class GUIAbstractComponent {

    private final GUIInterface guiInterface;

    public GUIAbstractComponent(GUIInterface guiInterface) {
        this.guiInterface = guiInterface;
    }

    protected void getNewChanges() {
        this.guiInterface.getGUIChanges(this);
    }

    public abstract Value getState();

    public abstract String getId();
}
