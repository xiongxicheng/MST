/*
CSE6140 HW1
This is an example of how your experiments should look like.
Feel free to use and modify the code below, or write your own experimental code, as long as it produces the desired output.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class RunExperiments{

	public static void main(String[] args)throws IOException{
		if (args.length < 3) {
			System.err.println("Unexpected number of command line arguments");
			System.exit(1);
		}

		String graph_file = args[0];
		String change_file = args[1];
		String output_file = args[2];

		PrintWriter output;
		output = new PrintWriter(output_file, "UTF-8");

		Graph G = parseEdges(graph_file);

		double startMST = System.nanoTime();
		Pair MST = computeMST(G);
		int MSTweight = MST.getWeight();
		double finishMST = System.nanoTime();

		//Subtract the start time from the finish time to get the actual algorithm running time
		double MSTtotal = (finishMST - startMST)/1000000;

		//Write to output file the initial MST weight and time
		output.println(Integer.toString(MSTweight) + " " + Double.toString(MSTtotal));

		//Iterate through changes file
		BufferedReader br = new BufferedReader(new FileReader(change_file));
		String line = br.readLine();
		String[] split = line.split(" ");
		int num_changes = Integer.parseInt(split[0]);
		int u, v, weight;

		while ((line = br.readLine()) != null) {
			split = line.split(" ");
			u = Integer.parseInt(split[0]);
			v = Integer.parseInt(split[1]);
			weight = Integer.parseInt(split[2]);

			//Run your recomputeMST function to recalculate the new weight of the MST given the addition of this new edge
			//Note: you are responsible for maintaining the MST in order to update the cost without recalculating the entire MST
			double start_newMST = System.nanoTime();
			int newMST_weight = recomputeMST(u,v,weight,MST);
			double finish_newMST = System.nanoTime();

			double newMST_total = (finish_newMST - start_newMST)/1000000;

			//Write new MST weight and time to output file
			output.println(Integer.toString(newMST_weight) + " " + Double.toString(newMST_total));


		}

		output.close();
		br.close();




	}

	public static Graph parseEdges(String file)throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		String[] split = line.split(" ");
		int num_vertices = Integer.parseInt(split[0]);
		Graph G = new Graph(num_vertices);
		int u,v,w;
		while ((line = br.readLine())!=null){
			split = line.split(" ");
			u = Integer.parseInt(split[0]);
			v = Integer.parseInt(split[1]);
			w = Integer.parseInt(split[2]);
			G.addEdge(new Edge(u,v,w));
		}
		return G;
	}

	public static Pair computeMST(Graph G){
		int res = 0;
		PriorityQueue<Edge> q = new PriorityQueue<>(G.getNumEdges());
		for(int i=0;i<G.size();i++) {
			for (int j = 0; j < G.V[i].adjacencyList.size(); j++) {
				q.add(G.V[i].adjacencyList.get(j));
			}
		}
		DisjointSet set = new DisjointSet(G.size());
		Graph T = new Graph(G.size());
		while(!q.isEmpty()){
			Edge e = q.poll();
			if(set.find(e.getSource())!=set.find(e.getDestination())){
				T.addEdge(e);
				res+=e.getWeight();
				set.union(e.getSource(),e.getDestination());
			}
		}
		return new Pair(T,res);
	}

	public static int recomputeMST(int u, int v, int weight, Pair MST){
		Graph T = MST.getGraph();
		T.addEdge(new Edge(u,v,weight));
		MST = computeMST(T);
		return MST.getWeight();
	}
}
