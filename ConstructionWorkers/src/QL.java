
import ASTnodes.Form;
import ASTnodes.sections.IfStatement;
import ASTnodes.sections.Section;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by LGGX on 11-Feb-17.
 */
public class QL {

    public static final String EXTENSION = "ql";

    public static void main(String[] arguments) throws Exception {
        run("C:\\Users\\LGGX\\Desktop\\test.ql");
    }

    public static QL run(String qlFileName) throws IOException, IllegalArgumentException {
        return new QL(qlFileName);
    }

    private QL(String qlFileName) throws IOException, IllegalArgumentException {

        if (!this.fileExists(qlFileName)) {
            throw new IOException();
        }

        if (!this.correctExtension(qlFileName, EXTENSION)) {
            throw new IllegalArgumentException();
        }

        InputStream qlInputStream = new FileInputStream(qlFileName);

        Form qlForm = this.buildForm(qlInputStream);
        Section section = qlForm.getSections().get(3);
        if (section instanceof IfStatement)
        {
            IfStatement s = (IfStatement) section;
            System.out.println(s.getSections());
        }

    }

    private Form buildForm(InputStream inputStream) throws IOException {
        ASTBuilder qlAstBuilder = new ASTBuilder(inputStream);
        return qlAstBuilder.buildForm();
    }

    private boolean fileExists(String filename) {
        Path path = Paths.get(filename);
        return Files.exists(path);
    }

    private boolean correctExtension(String filename, String extension) {
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        return fileExtension.equals(extension);
    }
}
