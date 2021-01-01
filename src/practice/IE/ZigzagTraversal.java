package practice.IE;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ZigzagTraversal {

    List<List<Integer>> result = new ArrayList<>();

    public void printZigZag(TreeNode root){

        Stack<TreeNode> stackA = new Stack<>();
        Stack<TreeNode> stackB = new Stack<>();
        stackA.push(root);

        while (!stackA.isEmpty() && !stackB.isEmpty()){
            if (!stackA.isEmpty()) {
                popParentAndInsertChildren(stackA, stackB);
            }else{
                popParentAndInsertChildren(stackB, stackA);
            }
        }


    }

    private void popParentAndInsertChildren(Stack<TreeNode> parentStack, Stack<TreeNode> childrenStack){
        List<Integer> curLevel = new ArrayList<>();
        while (!parentStack.isEmpty()){
            TreeNode node = parentStack.pop();
            curLevel.add(node.data);
            if (node.left !=null ){
                childrenStack.push(node.left);
            }
            if (node.right!=null){
                childrenStack.push(node.right);
            }
        }
        if (!curLevel.isEmpty()){
            result.add(curLevel);
        }
    }

    public static void main(String[] args) {

    }
}
