//package qls.ui;
//
//import QL.ui.Environment;
//import QL.ui.Notifier;
//import QL.ui.QEvaluator;
//import javafx.scene.control.Label;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import qls.PrettyEnvironment;
//
//// TODO rename prettyEvaluator
//public class QLSEvaluator extends QEvaluator {
//	
//	private PrettyEnvironment environment;
//
//	public QLSEvaluator(Environment answers, Notifier notifier) {
//		super(answers, notifier);
//		this.environment = (PrettyEnvironment) answers;
//		// this.styleTable = (StyleReferenceTable) answers;
//	}
//	
//	@Override    
//    protected Label createLabel(String label) {
//		Label l = new Label(label);
//		setSettings(l, "Arial", 12, Color.RED, 200);
//        return l; 
//    }
//	
//	// TODO move to different class?
//    private void setSettings(Label label, String font, int fontSize, Color color, int width) {
//    	label.setFont(new Font(font, fontSize));
//    	label.setTextFill(color);
//    	label.setPrefWidth(width);
//    }
//	
//}
