package GUI.GUIInterface;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class GUIMediator {

    private final GUIInterface guiInterface;

    public GUIMediator(GUIInterface guiInterface) {
        this.guiInterface = guiInterface;
    }

    protected void sendToMediator() {
        this.guiInterface.getGUIChanges(this);
    }

    //public abstract ExpressionValue getState();
    //public abstract String getId();
}
