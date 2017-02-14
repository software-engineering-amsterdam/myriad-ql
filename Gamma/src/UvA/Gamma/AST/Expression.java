package UvA.Gamma.AST;

import UvA.Gamma.AST.Values.Boolean;
import UvA.Gamma.AST.Values.Number;

/**
 * Created by Tjarco, 14-02-17.
 */
public interface Expression {
    String toString();
    Number evaluateNumber() throws Exception;
    Boolean evaluateBool() throws Exception;
}

