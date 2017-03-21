package test.org.uva.taxfree.ql.ui;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.uva.taxfree.ql.gui.FileSelector;

import java.io.File;

public class FileSelectorTest {

    @Ignore
    public void testFileDialog() throws Exception {
        File inputFile = FileSelector.select();
        System.out.println(inputFile);
    }
}