package practice.DP;


import java.util.LinkedList;
import java.util.Queue;

class EdgeDistance {

    int edgeNo, vertex, distance;

    public EdgeDistance(int edgeNo, int vertex, int distance) {
        this.edgeNo = edgeNo;
        this.vertex = vertex;
        this.distance = distance;
    }
}

public class ShortestPathExactlyKEdges {


    public static final int INF = Integer.MAX_VALUE;

    public int getShortestPathCost(int[][] adjMatrix, int u, int v, int k) {

        int n = adjMatrix.length;
        Queue<EdgeDistance> edgeDistanceQueue = new LinkedList<>();
        edgeDistanceQueue.add(new EdgeDistance(0, u,0));
        int minDistance = Integer.MAX_VALUE;
        while (!edgeDistanceQueue.isEmpty()){

            EdgeDistance edgeDistance = edgeDistanceQueue.poll();
            if (edgeDistance.edgeNo == k && edgeDistance.vertex==v){
                minDistance = Math.min(minDistance, edgeDistance.distance);
            }
            if (edgeDistance.edgeNo>k){
                break;
            }
            int vertex = edgeDistance.vertex;
            int edgeNo = edgeDistance.edgeNo;
            int distance = edgeDistance.distance;

            for(int i =0;i<n;i++){
                if (adjMatrix[vertex][i]<INF && i!=vertex){
                    edgeDistanceQueue.add(new EdgeDistance(edgeNo+1, i,distance+adjMatrix[vertex][i] ));
                }
            }
        }
        return minDistance;

    }

    static final int V = 4;

    public int shortestPath(int graph[][], int u, int v, int k)
    {
        // Base cases
        if (k == 0 && u == v)             return 0;
        if (k == 1 && graph[u][v] != INF) return graph[u][v];
        if (k <= 0)                       return INF;

        // Initialize result
        int res = INF;

        // Go to all adjacents of u and recur
        for (int i = 0; i < V; i++)
        {
            if (graph[u][i] != INF && u != i && v != i)
            {
                int rec_res = shortestPath(graph, i, v, k-1);
                if (rec_res != INF)
                    res = Math.min(res, graph[u][i] + rec_res);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int graph[][] = new int[][]{ {0, 10, 3, 2},
                {INF, 0, INF, 7},
                {INF, 9, 0, 6},
                {INF, INF, INF, 0}
        };
        ShortestPathExactlyKEdges t = new ShortestPathExactlyKEdges();
        int u = 0, v = 3, k = 3;
        System.out.println("Weight of the shortest path is "+
                t.getShortestPathCost(graph, u, v, k));

        System.out.println("Weight of the shortest path is "+
                t.shortestPath(graph, u, v, k));
    }

}
