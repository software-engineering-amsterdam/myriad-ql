package com.mcsa.QL;


import java.util.ArrayList;

/**
 * Created by sotos on 16/2/2017.
 */

public class TreeNode<T> {

    private T value;
    private TreeNode<T> parent;
    private ArrayList<TreeNode<T>> nodeChildren = null;

    TreeNode(T nodeValue) {
        this.nodeChildren = new ArrayList<>();
        this.value = nodeValue;
        this.parent = null;
    }

    void addChild(TreeNode<T> child) {
        this.nodeChildren.add(child);
        child.parent = this;
    }

    void addChildren(ArrayList<TreeNode<T>> children) {
        for (TreeNode<T> child : children) {
            child.parent = this;
            this.nodeChildren.add(child);
        }
    }

    public ArrayList<TreeNode<T>> getChildren() {
        return this.nodeChildren;
    }

    public TreeNode<T> getChild(int index) {
        if (index < this.nodeChildren.size()) {
            return this.nodeChildren.get(index);
        }else {
            return null;
        }
    }

    public TreeNode<T> getParent() {
        return this.parent;
    }

    public T getValue() {
        return this.value;
    }

    int isInChildren(T val) {
        int position = 0;
        for ( TreeNode<T> child : this.getChildren() ) {
            if ( val == child.value) return position;
            position++;
        }
        return -1;
    }
    /*public boolean hasChildren(TreeNode<T> node) {
        if (node.nodeChildren != null) {
            return true;
        }else {
            return false;
        }
    }*/
}


