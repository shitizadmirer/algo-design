//package practice;
//
//
//import java.util.*;
//
//public class TreeDuplicate {
//
//    static class Result {
//        boolean answer = false;
//    }
//
//    static class TreeNode {
//        int element;
//        TreeNode left;
//        TreeNode right;
//
//        public TreeNode(String element) {
//            this.element = element;
//        }
//    }
//
//    public static boolean duplicateSubTree(TreeNode root) {
//        Result result = new Result();
//        serializeTree(root, new HashSet<>(), result);
//        return result.answer;
//    }
//
//    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
//
//
////        Comparator<TreeNode> comparator = (o1, o2) -> {
////            if (o1.element <o2.element){
////                return -1;
////            }else if (o1.element>  o2.element){
////                return 1;
////            }
////            return 0;
////        };
//        TreeSet<TreeNode> treeNodes = new TreeSet<>(comparator);
//
//        serializeTree(root, new HashSet<>(),treeNodes);
//
//        return new ArrayList<>(treeNodes);
//    }
//
//    private String serializeTree(TreeNode root, Set<String> subTreeSet, List<TreeNode> treeNodes) {
//
//        if (root == null) {
//            return "";
//        }
//        if (root.left == null && root.right == null) {
//            String value = String.valueOf(root.val);
//            if (subTreeSet.contains(value)){
//                treeNodes.add(root);
//            }
//            subTreeSet.add(value);
//            return value;
//        }
//        String leftLeaf = serializeTree(root.left, subTreeSet, treeNodes);
//        String rightLeaf = serializeTree(root.right, subTreeSet, treeNodes);
//        String value = leftLeaf + root.val + rightLeaf;
//        if (subTreeSet.contains(value)){
//            treeNodes.add(root);
//        }
//        subTreeSet.add(value);
//        return value;
//    }
//
//
//    public static boolean duplicateSubtreeByBFS(TreeNode rootNode) {
//
//        if (rootNode == null) {
//            return false;
//        }
//        Queue<TreeNode> treeQ = new LinkedList<>();
//
//        Set<String> subTrees = new HashSet<>();
//        treeQ.add(rootNode);
//        while (!treeQ.isEmpty()) {
//
//
//            TreeNode headNode = treeQ.peek();
//            treeQ.remove();
//
//            String left = "", right = "";
//            if (headNode.left != null) {
//
//                if (headNode.left.left == null && headNode.left.right == null) {
//                    left = headNode.element + headNode.left.element;
//                }
//                treeQ.add(headNode.left);
//
//            }
//            if (headNode.right != null) {
//
//                if (headNode.right.left == null && headNode.right.right == null) {
//                    right = headNode.element + headNode.right.element;
//                }
//                treeQ.add(headNode.right);
//            }
//
//            if (!left.equals("")) {
//                if (subTrees.contains(left)) {
//                    return true;
//                }
//                subTrees.add(left);
//            }
//            if (!right.equals("")) {
//                if (subTrees.contains(right)) {
//                    return true;
//                }
//                subTrees.add(right);
//            }
//        }
//        return false;
//    }
//
//    public static void main(String[] args) {
//
//        TreeNode root = new TreeNode("A");
//        root.left = new TreeNode("B");
//        root.right = new TreeNode("C");
//        root.left.left = new TreeNode("D");
//        root.left.right = new TreeNode("E");
//
//        root.right.left = new TreeNode("F");
//        root.right.right = new TreeNode("G");
//        root.right.left.left = new TreeNode("B");
//        root.right.left.right = new TreeNode("D");
//
//        root.right.right.left = new TreeNode("I");
//        root.right.right.right = new TreeNode("J");
//
//        root.right.left.left.left = new TreeNode("D");
////        root.right.left.left.right = new TreeNode("E");
//
//        System.out.println(duplicateSubTree(root));
//        System.out.println(duplicateSubtreeByBFS(root));
//    }
//}
