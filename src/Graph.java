import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by xiongxicheng on 9/13/2017.
 */
public class Graph{
    Vertex[] V;
    private int num_edges = 0; 
    public Graph(int n){
        V = new Vertex[n];
        for(int i=0;i<n;i++){
            V[i] = new Vertex();
        }
    }
    public void addEdge(Edge edge){
    	int s = edge.getSource();
    	for(int i=0;i<V[s].adjacencyList.size();i++){
    		Edge e = V[s].adjacencyList.get(i);
    		if(e.getDestination()==edge.getDestination()){
    			if(edge.getWeight()<e.getWeight()){
    				V[s].adjacencyList.remove(e);
    				num_edges--;
    			}else{
    				return;
    			}
    		}
    	}
        V[edge.getSource()].adjacencyList.add(edge);
        num_edges++;
    }
    public int size(){
        return V.length;
    }
    public void removeEdge(Edge e) {
        V[e.getSource()].adjacencyList.remove(e);
        num_edges--;
    }
    public int getNumEdges(){
        return num_edges;
    }
}
