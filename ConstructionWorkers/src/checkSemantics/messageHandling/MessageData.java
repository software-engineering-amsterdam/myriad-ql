package checkSemantics.messageHandling;

import checkSemantics.messageHandling.errors.ErrorHandler;
import checkSemantics.messageHandling.warnings.WarningHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGGX on 17-Feb-17.
 */
public class MessageData {

    private List<ErrorHandler> errorList;
    private List<WarningHandler> warningList;

    public MessageData() {
        errorList = new ArrayList<ErrorHandler>();
        warningList = new ArrayList<WarningHandler>();
    }

    public void addError(ErrorHandler error) {
        errorList.add(error);
    }

    public void addWarning(WarningHandler warning) {
        warningList.add(warning);
    }

    public List<ErrorHandler> getErrors() {
        return errorList;
    }

    public List<WarningHandler> getWarnings() {
        return warningList;
    }

}
