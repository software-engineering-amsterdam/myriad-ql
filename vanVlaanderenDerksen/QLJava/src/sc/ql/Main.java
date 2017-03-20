package sc.ql;

import sc.ql.antlr.*;
import sc.ql.ast.*;
import sc.ql.model.*;
import sc.ql.checkform.*;
import sc.ql.gui.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	
	public static void main(String[] args) throws Exception {
		InputStream input = new FileInputStream("samples/sample-ql2.frm");
        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.form();
        
        AstVisitor visitor = new AstVisitor();
        Form form = (Form) visitor.visit(tree);

    	CheckForm checkForm = new CheckForm(form);    	
    	List<Message> messages = checkForm.getMessages();
    	
    	for(Message message : messages) {
    		System.out.println(message.toString());
    	}
        
        /*SwingUtilities.invokeLater(new Runnable() {
        	public void run() {
        		GUI gui = new GUI(form);
            	
            	JFrame frame = new JFrame("QL Form");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(gui);
                frame.setResizable(false);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });*/
    }
	
}