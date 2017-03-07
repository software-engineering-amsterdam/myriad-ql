package com.matthewchapman.ql.ast;

import com.matthewchapman.ql.validation.Visitable;

/**
 * Created by matt on 24/02/2017.
 *
 * Base Expression class. Where Questions, etc. are Statements, things that return results
 * when evaluated are Expressions.
 */
public abstract class Expression extends TreeNode implements Visitable {


}
