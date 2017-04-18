package sc.ql.model;

import sc.ql.model.visitors.FormElementVisitor;

public abstract class FormElement extends Node {
	public abstract <T> T accept(FormElementVisitor<T> visitor);
}
