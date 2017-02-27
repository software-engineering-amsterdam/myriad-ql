/**
 * QLTestSetUp.java.
 */

package unitTesting;

import ASTnodes.ASTBuilder;
import ASTnodes.Form;

import org.junit.Before;
import semanticChecker.SemanticChecker;
import semanticChecker.formDataStorage.valueData.ValueData;

import java.io.*;

public abstract class QLTestSetUp {

    protected Form form;
    protected ValueData questionStates;
    protected SemanticChecker semanticChecker;

    protected String inputFileName;
    protected String inputFilePath;

    private final String path = "./src/unitTesting/QLTestForms/";

    @Before
    public void setUp() throws IOException {

        if (inputFileName == null) {
            System.err.println("Undefined filename.");
            System.exit(-1);
        }

        inputFilePath = path.concat(inputFileName);

        InputStream qlInputStream = new FileInputStream(inputFilePath);
        ASTBuilder astBuilder = new ASTBuilder(qlInputStream);
        form = astBuilder.buildAST();
        questionStates =  new ValueData();
        semanticChecker =  new SemanticChecker(form , questionStates);
    }
}
