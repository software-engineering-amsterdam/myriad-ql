package ql.gui.elements;

import javafx.scene.text.Text;
import ql.ast.Expr;
import ql.gui.evaluator.BaseEvaluator;
import ql.gui.elements.fields.QLField;
import ql.values.Value;

/**
 * Created by Erik on 20-3-2017.
 */
public class GUIQuestionExpr extends GUIElement{
        private final QLField field;
        private final String variableName;
        private final Expr expr;

        public GUIQuestionExpr(String question, String variableName, QLField field, Expr expr) {
            this.variableName = variableName;
            this.field = field;
            this.getChildren().addAll(new Text(question), field.getNode());
            this.expr = expr;
        }

        public String getKey() {
            return this.variableName;
        }

        public Value getValue() {
            return this.field.getValue();
        }

        public Expr getExpr() {
            return expr;
        }

        public void update(Value<?> value) {
            field.update(value);
        }

        public <T> T accept(BaseEvaluator<T> visitor) {
            return visitor.visit(this);
        }
    }

