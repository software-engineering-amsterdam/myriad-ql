package ui;
import java.util.ArrayList;
import java.util.List;

import ast.Question;
import ast.Statement;
import ast.Visitor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

// Or Statement Visitor
public class QuestionnaireVisitor extends Visitor {
	
	private GridPane grid;
	private List<TextField> activeQuestions;
	private int rowIndex; // TODO do you want to set it here to zero?
	
	public QuestionnaireVisitor(GridPane grid) {
		this.grid = grid;
		this.activeQuestions = new ArrayList<TextField>();
		this.rowIndex = 0;
	}
	
	// TODO create a QuestionnaireQuestion class?
	// call here createQQuestion
	@Override 
	public void visit(Question question) {
        Label questionLabel = new Label(question.getLabel());
        grid.add(questionLabel, 0, 1 + rowIndex);
             
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1 + rowIndex); // TODO Maybe moving buttons to the
        // screen to a user interface class?
        activeQuestions.add(userTextField);
        ++rowIndex;
	}
	
	@Override
	public void visit(Statement statement) {	
		
		// TODO many functions - functions : can you assume
		// the ATOM is a boolean?
		System.out.println(statement.getExpression().evaluate());
		if (statement.getExpression().evaluate().getValue()) {
			statement.getBlock().accept(this);
		} 
	}

	public List<TextField> getActiveQuestions() {
		// TODO Auto-generated method stub
		return activeQuestions;
	}	
}
