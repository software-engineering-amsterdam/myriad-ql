package test.org.uva.taxfree.ql.ui;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.MessageWindow;

public class MessageWindowTest {
    @Ignore
    public void testComposedDialog() throws Exception {
        MessageList errors = new MessageList();
        errors.addError("Big error!");
        errors.addWarning("Small warning!");
        MessageWindow.showMessages(errors);
    }

    @Ignore
    public void testWarningDialog() throws Exception {
        MessageList warnings = new MessageList();
        warnings.addWarning("This is a warning");
        warnings.addWarning("This is another warning");
        MessageWindow.showMessages(warnings);
    }
}