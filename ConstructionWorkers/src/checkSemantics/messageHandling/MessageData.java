package checkSemantics.messageHandling;

import checkSemantics.messageHandling.errors.ErrorHandler;
import checkSemantics.messageHandling.warnings.WarningHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGGX on 17-Feb-17.
 */
public class MessageData {

    private List<MessageHandler> messageList;

    public MessageData() {
        messageList = new ArrayList<>();

    }

    public void addMessage(MessageHandler error) {
        messageList.add(error);
    }

    public List<MessageHandler> getMessages() {
        return messageList;
    }


}
