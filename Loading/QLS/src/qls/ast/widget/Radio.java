package qls.ast.widget;

public class Radio extends Widget {
	
	private final String checked;
	private final String unchecked;
	
	Radio(String checked, String unchecked, int line) {
		super(line);
		this.checked = checked;
		this.unchecked = unchecked;
	}
}
