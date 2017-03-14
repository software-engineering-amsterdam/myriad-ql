package com.matthewchapman.ql.ast;

/**
 * Created by matt on 21/02/2017.
 *
 * Base Statement class. All statements derive from this. Implements visitable
 * to allow derived classes to be visited.
 */
public abstract class Statement extends TreeNode implements QLVisitable {

}
