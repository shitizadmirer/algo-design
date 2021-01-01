package practice;

import java.util.ArrayList;
import java.util.List;

class DistNode{

    int val;
    DistNode left = null;
    DistNode right = null;

    public DistNode(int val){
        this.val = val;
    }
}
public class EveryNodeDistance {

    DistNode root;
    private String nodePath = "";
    DistNode target;
    List<Integer> nodeDistances = new ArrayList<>();



    private void formDistances(){

        DistNode node = root;
        int targetDistance = nodePath.length();
        for (int i = 0;i<nodePath.length();i++){
            char direction = nodePath.charAt(i);
            List<Integer> result = new ArrayList<>();
            if (direction == 'L'){
                formSubTreeDistances(node.right, result, 1);
            }else {
                formSubTreeDistances(node.left, result, 1);
            }
            nodeDistances.add(targetDistance-i);
            for (Integer res: result){
                nodeDistances.add(targetDistance-i+res+1);
            }
            node = direction == 'L' ? node.left : node.right;
        }

        List<Integer> downDistances = new ArrayList<>();
        formSubTreeDistances(target, downDistances, 0);

        nodeDistances.addAll(downDistances);
    }


    private void formSubTreeDistances(DistNode distNode, List<Integer> distances, int dist){
        if (distNode == null){
            return;
        }
        formSubTreeDistances(distNode.left, distances, dist+1);
        formSubTreeDistances(distNode.right,distances,dist+1);
        distances.add(dist);
    }


    private boolean setNodePath(DistNode node){
        if (node == null){
            return false;
        }
        if (target == node){
            return true;
        }

        if (setNodePath(node.left)){
            nodePath+="L";
            return true;
        } else if (setNodePath(node.right)){
            nodePath+="R";
            return true;
        }
        return false;
    }

    public List<Integer> distancesFromEveryNode(DistNode root, DistNode target){
        this.target = target;
        this.root = root;
        setNodePath(root);
        StringBuilder stringBuilder = new StringBuilder(nodePath);
        stringBuilder.reverse();
        nodePath = stringBuilder.toString();
        formDistances();
        return nodeDistances;
    }


    public static void main(String[] args) {

    }
}
