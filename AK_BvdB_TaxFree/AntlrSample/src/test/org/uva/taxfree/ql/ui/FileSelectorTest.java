package test.org.uva.taxfree.ql.ui;

import org.testng.annotations.Test;
import org.uva.taxfree.ql.gui.FileSelector;

import java.io.File;

public class FileSelectorTest {

    @Test
    public void testFileDialog() throws Exception {
        File inputFile = FileSelector.select();
        System.out.println(inputFile);
    }
}