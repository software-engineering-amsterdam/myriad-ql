package test.org.uva.taxfree.ast;

import org.testng.annotations.Test;
import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.MessageWindow;

public class MessageWindowTest {
    @Test
    public void testComposedDialog() throws Exception {
        MessageList errors = new MessageList();
        errors.addError("Big error!");
        errors.addWarning("Small warning!");
        MessageWindow.showMessages(errors);
    }

    @Test
    public void testWarningDialog() throws Exception {
        MessageList warnings = new MessageList();
        warnings.addWarning("This is a warning");
        warnings.addWarning("This is another warning");
        MessageWindow.showMessages(warnings);
    }
}