public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

   
    public DepthFirstSearch(Graph G, int s) {
	marked = new boolean[G.V()];
	dfs(G, s);
    }


    private void dfs(Graph G, int v) {
	count++;
	marked[v] = true;
	for (int w : G.adj(v)) {
	    if (!marked[w]) {
		dfs(G, w);
	    }
	}
    }

    public boolean marked(int v) {
	return marked[v];
    }

    public int count() {
	return count;
    }

    public static void main(String[] args) {
	In in = new In(args[0]);
	Graph G = new Graph(in);
	int s = Integer.parseInt(args[1]);
	DepthFirstSearch search = new DepthFirstSearch(G, s);
	for (int v = 0; v < G.V(); v++) {
		if (search.marked(v))
			StdOut.print(v + " ");
	}

	StdOut.println();
	if (search.count() != G.V()) StdOut.println("Not connected");
	else			   StdOut.println("connected");
   }
}