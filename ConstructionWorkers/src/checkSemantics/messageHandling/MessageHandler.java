package checkSemantics.messageHandling;

/**
 * Created by LGGX on 17-Feb-17.
 */
public abstract class MessageHandler {
    public String type;

    public abstract String getMessage();

    public String getType() {
        return type;
    }
}
