/**
 * QL.java.
 *
 * Actually a test class...
 */

import ASTnodes.Form;
import ASTnodes.statements.IfStatement;
import ASTnodes.statements.Statement;
import checkSemantics.CheckSemantics;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QL {

    private static final String ALLOWED_EXTENSION = "ql";

    private QL(String fileName) throws IOException, IllegalArgumentException {

        if (!this.fileExists(fileName)) {
            throw new IOException();
        }

        if (!this.correctExtension(fileName)) {
            throw new IllegalArgumentException();
        }

        InputStream qlInputStream = new FileInputStream(fileName);
        Form qlAST = this.getAST(qlInputStream);

        CheckSemantics semantics = new CheckSemantics(qlAST);
        // Test...


        Statement statement = qlAST.getStatements().get(3);
        if (statement instanceof IfStatement)
        {
            IfStatement s = (IfStatement) statement;
            System.out.println(s.getStatements());
        }
    }

    private boolean fileExists(String fileName) {
        Path path = Paths.get(fileName);
        return Files.exists(path);
    }

    private boolean correctExtension(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        return fileExtension.equals(ALLOWED_EXTENSION);
    }

    private Form getAST(InputStream inputStream) throws IOException {
        ASTBuilder qlAstBuilder = new ASTBuilder(inputStream);
        return qlAstBuilder.buildAST();
    }

    // Test...

    public static void main(String[] arguments) throws Exception {
        run("C:\\Users\\LGGX\\Desktop\\test.ql");
    }

    private static QL run(String qlFileName) throws IOException, IllegalArgumentException {
        return new QL(qlFileName);
    }
}
