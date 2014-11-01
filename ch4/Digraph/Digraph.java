import java.util.InputMismatchException;
import java.util.NoSuchElementException;


public class Digraph {
   private  final int V;
   private  int E;
   private  Bag<Integer>[] adj;


   public Digraph(int V) {
	if (V < 0) throw new IllegalArgumentException("number negative");
	this.V = V;
	this.E = 0;
	adj = (Bag<Integer>[]) new Bag[V];
	for (int v = 0; v < V; v++) {
	    adj[v] = new Bag<Integer>();
	}
   }

   public Digraph(In in) {
	try {
	    this.V = in.readInt();
	    if (V < 0) throw new IllegalArgumentException("number negative");
	    adj = (Bag<Integer>[]) new Bag[V];
	    for (int v = 0; v < V; v++) {
		adj[v] = new Bag<Integer>();
	    }
	    int E = in.readInt();
	    if (E < 0) throw new IllegalArgumentException("number negative");
	    for (int i = 0; i < E; i++) {
		int v = in.readInt();
		int w = in.readInt();
		addEdge(v, w);
	    }
	}
	catch (NoSuchElementException e) {
		throw new InputMismatchException("Invalid input format in Digraph constructor");
	}
   }

   public Digraph(Digraph G) {
	this(G.V());
	this.E = G.E();
	for (int v = 0; v < G.V(); v++) {
	
	    Stack<Integer> reserve = new Stack<Integer>();
	    for (int w : G.adj[v]) {
		reserve.push(w);
	    }
	    for (int w : reserve) {
		adj[v].add(w);
	    }
	}
   }


   public int V() {
	return V;
   }


   public int E() {
	return E;
   }


   public void addEdge(int v, int w) {
       if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
        if (w < 0 || w >= V) throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V-1));
	adj[v].add(w);
	E++;
   }

   public Iterable<Integer> adj(int v) {
	if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
	return adj[v];
   }

   public Digraph reverse() {
	Digraph R = new Digraph(V);
	for (int v = 0; v < V; v++) {
	    for (int w : adj(v)) {
		R.addEdge(w, v);
	    }
	}
	return R;
   }


   public String toString() {
       StringBuilder s = new StringBuilder();
       String NEWLINE = System.getProperty("line.separator");
       s.append(V + " vertices, " + E + " edges	" +NEWLINE);
       for (int v = 0; v < V; v++) {
	   s.append(String.format("%d: ", v));
	   for (int w : adj[v]) {
		s.append(String.format("%d ", w));
	   }
	   s.append(NEWLINE);
	}
	return s.toString();
   }


   public static void main(String[] args) {
	In in = new In(args[0]);
	Digraph G = new Digraph(in);
	StdOut.println(G);
   }
}
