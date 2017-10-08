import java.lang.reflect.Array;

/**
 * Created by xiongxicheng on 9/13/2017.
 */
public class DisjointSet {
    private int[] array;
    public DisjointSet(int n){
        array = new int[n];
        for(int i=0;i<n;i++){
            array[i]=-1;
        }
    }
    public void union(int r1, int r2){
    	int root1 = find(r1);
    	int root2 = find(r2);
        if(array[root2]<array[root1]){
            array[root2]+=array[root1];
            array[root1]=root2;
        }else {
            array[root1]+=array[root2];
            array[root2]=root1;
        }
    }
    public int find(int x){
        if(array[x]<0){
            return x;
        }else {
            array[x]=find(array[x]);
            return array[x];
        }
    }
}
