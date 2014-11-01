
public class UnorderedArrayMaxPQ<Key extends Comparable<Key>>
{
	private Key[] pq;
	private int N = 0;
	
	public UnorderedArrayMaxPQ(int capacity)
	{ pq = (Key[]) new Comparable[capacity]; 
	  N = 0;
	}

	public boolean isEmpty()
	{ return N == 0; }

	public int size()
	{ return N;  }

	public void insert(Key v)
	{
		pq[N++] = v;
		//swim(N);
	}
	
	public Key delMax()
	{
		int max = 0;
		for (int i = 1; i < N; i++)
			if (less(max,i)) max = i;
		exch(max, N-1);

		return pq[--N];	
	}

	private boolean less(int i, int j)
	{ return (pq[i].compareTo(pq[j]) < 0); }

	private void exch(int i, int j)
	{ Key t = pq[i]; pq[i] = pq[j]; pq[j] = t; }

	private void swim(int k)
	{
		while (k > 1 && less(k/2, k))
		{
			exch(k/2, k);
			k = k/2;
		}
	}

	private void sink(int k)
	{
		while (2*k <= N)
		{
			int j = 2*k;
			if (j < N && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	private static void show(Comparable[] a)
        {
                for (int i = 0; i < a.length; i++)
                        StdOut.print(a[i] + " ");
                StdOut.println();
        }

	public static void main(String[] args) {
        UnorderedArrayMaxPQ<String> pq = new UnorderedArrayMaxPQ<String>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty()) 
            StdOut.println(pq.delMax());
    }	
}
