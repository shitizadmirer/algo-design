package practice.IE;

public class ConsecutiveOrderTree {

    public int maxConsecutiveOrderTree(NaryTreeNode naryTreeNode){
        if (naryTreeNode == null){
            return 0;
        }

        int data = naryTreeNode.data;
        int maxConsecutive = 1;
        for (NaryTreeNode treeNode : naryTreeNode.naryTreeNodes){

            if (treeNode.data == data+1){
                maxConsecutive = Math.max(maxConsecutiveOrderTree(treeNode), maxConsecutive);
            }
        }
        return maxConsecutive;
    }
}
