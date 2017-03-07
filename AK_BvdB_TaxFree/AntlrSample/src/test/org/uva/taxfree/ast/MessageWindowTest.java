package test.org.uva.taxfree.ast;

import org.testng.annotations.Test;
import org.uva.taxfree.gui.ErrorMessage;
import org.uva.taxfree.gui.Message;
import org.uva.taxfree.gui.MessageWindow;
import org.uva.taxfree.gui.WarningMessage;

import java.util.ArrayList;
import java.util.List;

public class MessageWindowTest {
    @Test
    public void testComposedDialog() throws Exception {
        List<Message> errors = new ArrayList<>();
        errors.add(new ErrorMessage("Big error!"));
        errors.add(new WarningMessage("Small warning!"));
        MessageWindow.showMessageDialog(errors);
    }

    @Test
    public void testWarningDialog() throws Exception {
        List<Message> warnings = new ArrayList<>();
        warnings.add(new WarningMessage("This is a warning"));
        warnings.add(new WarningMessage("This is another warning"));
        MessageWindow.showMessageDialog(warnings);
    }
}