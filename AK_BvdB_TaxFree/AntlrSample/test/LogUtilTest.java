package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.util.LogUtil;

import java.io.File;

public class LogUtilTest {
    @Test
    public void testLogError() throws Exception {
        Assert.assertTrue(TestUtil.RemoveFile("log.err"));
        LogUtil.logError("This is an error!");
        Assert.assertTrue(new File("log.err").isFile(), "File should exist after logging an error");
        LogUtil.logError("This is another error!");
    }

}