package practice;


import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class KDistanceNode {

    private TreeNode targetNode = null;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null || target == null) {
            return new ArrayList<>();
        }
        this.targetNode = target;
        if (K == 0) {
            List<Integer> nodes = new ArrayList<>();
            nodes.add(target.val);
            return nodes;
        }

        List<TreeNode> nodes = new ArrayList<>();
        setNodePath(root, nodes);
        List<Integer> result = new ArrayList<>();
        setKDistanceNodesDown(target, K, result);
        int nodeSize = nodes.size();

        for (int i = 0; i < nodeSize - 1; i++) {
            int nextNodeInPath = nodes.get(i + 1).val;
            TreeNode curNode = nodes.get(i);
            TreeNode left = curNode.left;
            TreeNode right = curNode.right;
            TreeNode nodeToTraverse = left != null && left.val == nextNodeInPath ? right : left;
            int lookUpSize = K - (nodeSize-i-1);
            if (lookUpSize == 0){
                result.add(curNode.val);
            }else {
                setKDistanceNodesDown(nodeToTraverse, lookUpSize - 1, result);
            }
        }

        return result;
    }


    private boolean setNodePath(TreeNode currentNode, List<TreeNode> nodes) {
        if (currentNode == null) {
            return false;
        }
        nodes.add(currentNode);
        if (currentNode.val == targetNode.val) {
            return true;
        }
        if (setNodePath(currentNode.left, nodes) || setNodePath(currentNode.right, nodes)) {
            return true;
        }
        nodes.remove(currentNode);
        return false;
    }

    private void setKDistanceNodesDown(TreeNode startNode, int k, List<Integer> nodes) {
        if (k < 0) {
            return;
        }
        if (startNode == null) {
            return;
        }
        if (k == 0) {
            nodes.add(startNode.val);
            return;
        }
        setKDistanceNodesDown(startNode.left, k - 1, nodes);
        setKDistanceNodesDown(startNode.right, k - 1, nodes);
    }

}
