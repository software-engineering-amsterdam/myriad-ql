package com.mcsa;

import java.util.ArrayList;

/**
 * Created by sotos on 20/2/2017.
 */
public class AddTheValuesToAST {

    public TreeNode<String> rootNode;

    AddTheValuesToAST() {
        rootNode = new TreeNode<>("Forms");
    }

    void AddFormsToTheAST(ArrayList<String> forms ) {
        ArrayList<TreeNode<String>> children = new ArrayList<>();
        for (String form : forms) {
            TreeNode<String> formName = new TreeNode<>(form);
            children.add(formName);
        }
        rootNode.addChildren(children);
    }

}
