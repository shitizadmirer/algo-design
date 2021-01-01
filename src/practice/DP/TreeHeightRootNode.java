package practice.DP;

import java.util.ArrayList;
import java.util.List;

public class TreeHeightRootNode {

    private List<List<Integer>> adjacencyList;
    private int vertices;

    private int[] downwardHeights;
    private int[] upwardHeights;
    private int[] parents;

    public void printTreeHeightForEveryRootNode(List<List<Integer>> adjacencyList, int vertices){
        this.adjacencyList  = adjacencyList;
        this.vertices = vertices;
        this.downwardHeights = new int[vertices + 1];
        this.upwardHeights = new int[vertices+1];
        this.parents = new int[vertices+1];

        fillParents();
        fillDownwardHeight();
        fillUpwardHeight();

        for (int v = 1;v<=vertices;v++){
            System.out.println("Max height of vertex "+ v+ " as root is :" + Math.max(downwardHeights[v], upwardHeights[v]));
        }
    }

    private void fillParents(){
        for (int i =0;i<adjacencyList.size();i++){
            List<Integer> neighbors = adjacencyList.get(i);
            if (neighbors!=null){
                for (Integer  child:neighbors){
                    parents[child] = i;
                }
            }
        }
    }

    private void fillDownwardHeight(){
        fillDownHeight(1);
    }

    private void fillDownHeight(int vertex){
//        System.out.println(vertex);
        int maxHeight = 0;
        List<Integer> neighbors = adjacencyList.get(vertex);

        if(neighbors!=null) {
            for (Integer adj : neighbors) {
                fillDownHeight(adj);
                maxHeight = Math.max(maxHeight, downwardHeights[adj]+1);
            }
        }

        downwardHeights[vertex] = maxHeight;

    }

    private void fillUpwardHeight(){
        for(int v=1;v<=vertices;v++){
            fillUpHeight(v);
        }
    }

    private void fillUpHeight(int vertex){
        Integer parent = parents[vertex];
        List<Integer> children = adjacencyList.get(parent);
        int maxHeight = 0;
        if (children!=null) {
            for (Integer child : children) {
                if (child != vertex) {
                    maxHeight = Math.max(maxHeight, (downwardHeights[child] + 2));
                }
            }
        }
        upwardHeights[vertex] = Math.max(upwardHeights[parent]+1, maxHeight);
    }

    public static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
//        adj.get(v).add(u);+

    }

    public static void main(String[] args) {

        int V = 12;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < V; i++)
            adj.add(new ArrayList<Integer>());

        addEdge(adj, 1, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 1, 4);
        addEdge(adj, 2, 5);
        addEdge(adj, 2, 6);
        addEdge(adj, 3, 7);
        addEdge(adj, 7, 10);
        addEdge(adj, 7, 11);
        addEdge(adj, 4, 8);
        addEdge(adj, 4, 9);

        new TreeHeightRootNode().printTreeHeightForEveryRootNode(adj,V);
    }
}
