//package ui;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import ast.Question;
//import ast.Statement;
//import ast.Visitor;
//import ast.expression.BinaryExpression;
//import ast.expression.IdExpression;
//import ast.expression.UnaryExpression;
//import value.Value;
//
//// Or Statement Visitor
//public class QuestionnaireVisitor extends Visitor {
//
//	private List<QuestionnaireQuestion> activeQuestions; // TODO QQuestion String and type?
//	private Map<String, Value> answers;
//
//	public QuestionnaireVisitor(Map<String, Value> answers) {
//		this.activeQuestions = new ArrayList<>();
//		this.answers = answers;
//	}
//
//	@Override
//	public void visit(Question question) {
//        activeQuestions.add(new QuestionnaireQuestion(question.getVariable(),
//        		question.getLabel(), question.getType())); // TODO change last argument
//	}
//
//	@Override
//	public void visit(Statement statement) {
//		statement.getExpression().accept(this);
//
//		// TODO many functions - functions : can you assume the ATOM is a boolean?
//		System.out.println("QUESTIONNAIRE VISITOR: "+ statement.getExpression().evaluate().getValue());
//
//		// Call the evaluator with answers
//		if (answers.size() != 0) {
//			List<Value> valuesList = new ArrayList<>(answers.values());
//			List<String> keysList = new ArrayList<>(answers.keySet());
//
//			Value answer = valuesList.get(0);
//			String question = keysList.get(0);
//			System.out.println("question: " + question);
//			System.out.println("answer: " + answer);
//			System.out.println("getvalue: " + statement.getExpression().evaluate().getValue());
//
//			if (statement.getExpression().evaluate().getValue()) {
//				statement.getBlock().accept(this);
//			}
//		}
//		if (statement.getExpression().evaluate().getValue()) {
//			statement.getBlock().accept(this);
//		}
//	}
//
//	@Override
//	public void visit(BinaryExpression binaryExpression) {
//		binaryExpression.getLhs().accept(this);
//		binaryExpression.getRhs().accept(this);
//	}
//
//	@Override
//	public void visit(UnaryExpression unaryExpression) {
//		unaryExpression.getLhs().accept(this);
//	}
//
//	@Override
//	public void visit(IdExpression id) {
//		System.out.println("TYPE " + activeQuestions.get(0).getType());
//		Value answer = answers.get(id.getName());
//		if (answer != null) {
//			System.out.println("VISIT IDEXPR, ANSWER: " + answer.getValue());
//		} else {
//			System.out.println("VISIT IDEXPR, ANSWER: " + answer);
//		}
//
////		return answer;
//	}
//
//
//	public List<QuestionnaireQuestion> getActiveQuestions() {
//		return activeQuestions;
//	}
//}
