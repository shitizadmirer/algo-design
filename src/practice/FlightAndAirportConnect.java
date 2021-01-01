package practice;

import java.util.*;
import java.util.stream.Collectors;

class Graph{
     int vertices;
     List<List<Integer>> adjacencyList;

    public Graph(int vertices){
        this.vertices = vertices;
        this.adjacencyList = new ArrayList<>();
        for (int i =0;i<vertices;i++){
            this.adjacencyList.add(new ArrayList<>());
        }

    }
}

class Route{
    String src;
    String dest;
    public Route(String src, String dest){
        this.dest = dest;
        this.src = src;
    }
}

public class FlightAndAirportConnect {

    private Map<String,Integer> airportVertexMap = new HashMap<>();
    private Graph routeGraph;
    private Graph reverseGraph;
    private Map<Integer,Integer> vertexVsComponent = new HashMap<>();

    private Stack<Integer> vertexOrderStack = new Stack<>();
    private boolean[] visited ;

    private boolean [] reverseVisited;

    private void formRouteGraph(List<Route> routes, int vertices){
        routeGraph = new Graph(vertices);
        for (Route route: routes){
            String src = route.src;
            String dest = route.dest;
            routeGraph.adjacencyList.get(airportVertexMap.get(src)).add(airportVertexMap.get(dest));
        }
    }

    private void setComponentsOfVertices(){

        for (Map.Entry<String,Integer> vertex : airportVertexMap.entrySet()) {
            if (!visited[vertex.getValue()]) {
                fillStack(vertex.getValue());
            }
        }

        while (!vertexOrderStack.isEmpty()){
            int top = vertexOrderStack.pop();
            if (!vertexVsComponent.containsKey(top)){
                markComponent(top,top);
            }
        }

    }

    private void markComponent(int vertex, int component){
        reverseVisited[vertex] = true;
        vertexVsComponent.put(vertex,component);
        List<Integer> adjacent  = reverseGraph.adjacencyList.get(vertex);
        for(Integer neighbour : adjacent){
            if (!reverseVisited[neighbour] && !vertexVsComponent.containsKey(neighbour)){
                markComponent(neighbour,component);
            }
        }
    }


    private void fillStack(int vertex){
        visited[vertex] = true;
        List<Integer> adjacent = routeGraph.adjacencyList.get(vertex);
        for (Integer neighbour : adjacent){
            if (!visited[neighbour]){
                fillStack(neighbour);
            }
        }

        vertexOrderStack.push(vertex);
    }


    private void formGraphTranspose(List<Route> routes, int vertices){
        reverseGraph = new Graph(vertices);
        for (Route route : routes){
            String src = route.src;
            String dest = route.dest;
            reverseGraph.adjacencyList.get(airportVertexMap.get(dest)).add(airportVertexMap.get(src));
        }
    }

    private List<Integer> getMissingFlightNum(int source){

        int sourceComp = vertexVsComponent.get(source);
        Map<Integer,Integer> componentToVertexMap = new HashMap<>();
        for (Map.Entry<Integer,Integer> entry : vertexVsComponent.entrySet()){
            if (entry.getValue()!=sourceComp){
                componentToVertexMap.put(entry.getValue(), entry.getKey());
            }
        }
        return new ArrayList<>(componentToVertexMap.values());
    }



    public List<String> getMissingFlightRouteFromSource(List<String>  airports, List<Route> routes, String source){
        int vertices = airports.size();
        for (int i  = 0;i<vertices;i++){
            airportVertexMap.put(airports.get(i),i);
        }
        this.visited = new boolean[vertices];
        this.reverseVisited = new boolean[vertices];

        //Kosaraju Algo Start
        formRouteGraph(routes,vertices);
        formGraphTranspose(routes,vertices);
        setComponentsOfVertices();
        //Kosaraju Algo End

        List<Integer> flights = getMissingFlightNum(airportVertexMap.get(source));
        return flights.stream().map(airports::get).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> airports = new ArrayList<>();
        airports.add("BGI");
        airports.add("CDG");
        airports.add("DEL");
        airports.add("DOH");
        airports.add("DSM");
//        airports.add("EWR");
//        airports.add("EYW");
//        airports.add("HND");
//        airports.add("ICN");
//        airports.add("JFK");
//        airports.add("LGA");
//        airports.add("LHR");
//        airports.add("ORD");
//        airports.add("SAN");
//        airports.add("SFO");
//        airports.add("SIN");
//        airports.add("TLV");
//        airports.add("BUD");

        List<Route> routes = new ArrayList<>();
        routes.add(new Route("CDG","BGI"));
        routes.add(new Route("BGI","DEL"));
        routes.add(new Route("DEL","CDG"));
        routes.add(new Route("BGI","DOH"));
        routes.add(new Route("DOH","DSM"));

//        routes.add(new Route("DSM", "ORD"));
//        routes.add(new Route("ORD", "BGI"));
//        routes.add(new Route("BGI", "LGA"));
//        routes.add(new Route("SIN", "CDG"));
//        routes.add(new Route("CDG", "SIN"));
//        routes.add(new Route("CDG", "BUD"));
//        routes.add(new Route("DEL", "DOH"));
//        routes.add(new Route("DEL", "CDG"));
//        routes.add(new Route("TLV", "DEL"));
//        routes.add(new Route("EWR", "HND"));
//        routes.add(new Route("HND", "ICN"));
//        routes.add(new Route("HND", "JFK"));
//        routes.add(new Route("ICN", "JFK"));
//        routes.add(new Route("JFK", "LGA"));
//        routes.add(new Route("EYW", "LHR"));
//        routes.add(new Route("LHR", "SFO"));
//        routes.add(new Route("SFO", "SAN"));
//        routes.add(new Route("SFO", "DSM"));
//        routes.add(new Route("SAN", "EYW"));

        System.out.println(new FlightAndAirportConnect().getMissingFlightRouteFromSource(airports, routes, "LGA"));


    }
}
