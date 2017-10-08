/**
 * Created by xiongxicheng on 9/13/2017.
 */
public class Pair {
    private int total_weight;
    private Graph graph;
    public Pair(Graph g, int w){
        total_weight = w;
        graph = g;
    }
    public int getWeight(){
        return total_weight;
    }
    public Graph getGraph(){
        return graph;
    }
}
