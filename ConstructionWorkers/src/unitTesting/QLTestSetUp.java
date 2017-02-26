package unitTesting;

import ASTnodes.ASTBuilder;
import ASTnodes.Form;

import org.junit.Before;
import semanticChecker.SemanticChecker;
import semanticChecker.formDataStorage.valueData.ValueData;

import java.io.*;

/**
 * Created by LGGX on 22-Feb-17.
 */
public abstract class QLTestSetUp {

    protected SemanticChecker semanticChecker;
    protected Form form;
    protected ValueData questionStates;

    protected String inputFile;
    protected String fileName;

    private final String path = "src/unitTesting/QLTestForms/";

    @Before
    public void setUp() throws IOException {

        if (this.fileName == null) {
            System.err.println("No filename given as input!");
            System.exit(-1);
        }

        this.inputFile = this.path.concat(this.fileName);

        InputStream qlInputStream = new FileInputStream(this.inputFile);
        ASTBuilder astBuilder = new ASTBuilder(qlInputStream);

        this.form = astBuilder.buildAST();
        this.questionStates =  new ValueData();
        this.semanticChecker =  new SemanticChecker(this.form , this.questionStates);

    }
}
