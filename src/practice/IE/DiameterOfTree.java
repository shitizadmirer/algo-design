package practice.IE;

class Diameter{
    int maxDiameter = 0;

}

public class DiameterOfTree {

    public int diameterOfTree(TreeNode root){
        if (root == null){
            return  0 ;
        }
        Diameter diameter = new Diameter();
        heightOfTree(root,diameter);
        return diameter.maxDiameter;
    }

    private int heightOfTree(TreeNode root, Diameter diameter){
        if (root == null){
            return 0;
        }

        int leftHt = heightOfTree(root.left,diameter);
        int rightHt = heightOfTree(root.right,diameter);
        if (leftHt + rightHt +1 > diameter.maxDiameter){
            diameter.maxDiameter = leftHt + rightHt +1;
        }
        return Math.max(leftHt,rightHt) +1;
    }




    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.left.left.left = new TreeNode(6);
        node.left.left.right = new TreeNode(7);
        node.right.right = new TreeNode(3);
        node.right.right.right = new TreeNode(3);

        node.right.right.right.right = new TreeNode(3);

        System.out.println(new DiameterOfTree().diameterOfTree(node));


    }
}
