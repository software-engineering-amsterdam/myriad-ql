import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class LogWindowTest {
    @Test
    public void testLogError() throws Exception {
        Assert.assertTrue(TestUtil.RemoveFile("log.err"));
        LogWindow.logError("This is an error!");
        Assert.assertTrue(new File("log.err").isFile(), "File should exist after logging an error");
        LogWindow.logError("This is another error!");
    }

}