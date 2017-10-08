import java.util.Comparator;

/**
 * Created by xiongxicheng on 9/13/2017.
 */
public class Edge implements Comparable<Edge>{
    private int s;
    private int d;
    private int w;
    public Edge(int source, int destination, int weight){
        s = source;
        d = destination;
        w = weight;
    }
    public int compareTo(Edge other){
        return Integer.compare(this.w,other.w);
    }
    public int getSource(){
        return this.s;
    }
    public int getDestination(){
        return this.d;
    }
    public int getWeight(){
        return w;
    }
}
