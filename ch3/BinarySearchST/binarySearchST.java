import java.util.NoSuchElementException;

public class binarySearchST<Key extends Comparable<Key>, Value>
{
	private static final int INIT_CAPACITY = 2;	
	private Key[] keys;
	private Value[] vals;
	private int N = 0;

	public binarySearchST() { this(INIT_CAPACITY); }  	
	public binarySearchST(int capacity)
	{
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}
	public int size()
	{  return N;  }
	public Value get(Key key)
	{
		if (isEmpty()) return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) return vals[i];
		else					  return null;
	}
	
	public int rank(Key key)
	{
		int lo = 0, hi = N-1;
		while (lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if	(cmp < 0) hi = mid - 1;
			else if (cmp > 0) lo = mid + 1;
			else return mid;
		}
		return lo;
	}

	public void put(Key key, Value val)
	{
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0)
		{  vals[i] = val; return;  }
		for (int j = N; j > i; j--)
		{  keys[j] = keys[j-1]; vals[j] = vals[j-1];  }
		keys[i] = key; vals[i] = val;
		N++;
	}
	public boolean isEmpty()
	{	return (N == 0); }
	public Iterable<Key> keys() {
       		return keys(min(), max()); 
	}
	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		if (lo == null && hi == null) return queue;
		if (lo.compareTo(hi) > 0) return queue;
		for (int i = rank(lo); i < rank(hi); i++)
			queue.enqueue(keys[i]);
		if (contains(hi)) queue.enqueue(keys[rank(hi)]);
		return queue;
	}
	public Key min()
	{ return keys[0];  }

	public Key max()
	{ return keys[N - 1]; }
	public boolean contains(Key key) {
		return get(key) != null;
	}

	public static void main(String[] args) { 
        binarySearchST<String, Integer> st = new binarySearchST<String, Integer>(100);
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
