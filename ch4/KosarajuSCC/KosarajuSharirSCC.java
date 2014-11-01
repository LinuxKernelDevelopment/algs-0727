
public class KosarajuSharirSCC {
	private boolean[] marked;
	private int[] id;
	private int count;



	private KosarajuSharirSCC(Digraph G) {

		DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());

		marked = new boolean[G.V()];
		id = new int[G.V()];
		for (int v : dfs.reversePost()) {
			if (!marked[v]) {
				dfs(G, v);
				count++;
			}
		}

		assert check(G);
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v)) {
			if (!marked[w]) dfs(G, w);
		}
	}


	public int count() {
		return count;
	}


	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}

	public int id(int v) {
		return id[v];
	}

	private boolean check(Digraph G) {
		TransitiveClosure tc = new TransitiveClosure(G);
		for (int v = 0; v < G.V(); v++) {
			for (int w = 0; w < G.V(); w++) {
				if (stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
					return false;
			}
		}
		return true;
	}


	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		KosarajuSharirSCC scc = new KosarajuSharirSCC(G);

		int M = scc.count();
		StdOut.println(M + " components");

		Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
		for (int i = 0; i < M; i++) {
			components[i] = new Queue<Integer>();
		}
		for (int v = 0; v < G.V(); v++) {
			components[scc.id(v)].enqueue(v);
		}

		for (int i = 0; i < M; i++) {
			for (int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}
}
