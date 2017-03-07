package test.org.uva.taxfree.ast;

import org.testng.annotations.Test;
import org.uva.taxfree.gui.FileSelector;

import java.io.File;

public class FileSelectorTest {

    @Test
    public void testFileDialog() throws Exception {
        File inputFile = FileSelector.select();
        System.out.println(inputFile);
    }
}