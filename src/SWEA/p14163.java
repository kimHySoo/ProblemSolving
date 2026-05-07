import java.util.*;

class p14163
{
	static HashMap<String, int[]> dict;
	static int[][] ori;
	static int odx, cdx, ver;
	static StringBuilder sb;
	static LinkedList[] ll;
	static int[] par, nver;
	
	static class Node{
		Node next;
		int idx, value, version;
		Node() {
		}
		Node(int idx, int value, int version) {
			this.idx = idx;
			this.value = value;
			this.version = version;
			next = null;
		}
	}
	static class LinkedList{
		Node head, tail;
		LinkedList() {
			head = new Node();
			tail = head;
		}
		void add(int idx, int value, int version) {
			Node update = new Node(idx, value, version);
			tail.next = update;
			tail = update;
		}
	}
	public void init()
	{
		dict = new HashMap<>();
		
		ori = new int[11][200001];
		
		ll = new LinkedList[5011];
		for(int i=1;i<5011;i++) ll[i] = new LinkedList();
		
		odx = 1;
		cdx = 11;
		ver = 1;
		par = new int[5011];
		nver = new int[5011];
	}

	public void makeList(char mName[], int mLength, int mListValue[])
	{
		
		sb = new StringBuilder();
		for(char c:mName) {
			if(c == '\0') break;
			sb.append(c);
		}		
		
		String x = sb.toString();
		
		dict.put(x, new int[] {0, odx, ver});
		for(int i=0;i<mLength;i++) ori[odx][i] = mListValue[i];
		nver[odx] = ver++;
		odx++;
	}

	public void copyList(char mDest[], char mSrc[], boolean mCopy)
	{
		sb = new StringBuilder();
		for(char c:mDest) {
			if(c == '\0') break;
			sb.append(c);
		}		
		
		String pst = sb.toString();
		
		sb = new StringBuilder();
		for(char c:mSrc) {
			if(c == '\0') break;
			sb.append(c);
		}		
		
		String bef = sb.toString();
		
		int[] arr = dict.get(bef);
		
		
		if(mCopy) {
			nver[cdx] = ver;
			par[cdx] = arr[1];
			dict.put(pst, new int[] {arr[1], cdx++, ver++});

		}
		else dict.put(pst, arr);
		
	}

	public void updateElement(char mName[], int mIndex, int mValue)
	{
		sb = new StringBuilder();
		for(char c:mName) {
			if(c == '\0') break;
			sb.append(c);
		}	
		String x = sb.toString();
		
		int[] arr = dict.get(x);
		
		ll[arr[1]].add(mIndex, mValue, ver++);
	
	}

	public int element(char mName[], int mIndex)
	{
		int ans = 0;
		sb = new StringBuilder();
		for(char c:mName) {
			if(c == '\0') break;
			sb.append(c);
		}	
		String x = sb.toString();
		
		int[] arr = dict.get(x);
		int v = arr[1];
		int made = nver[v];
		
		while(par[v]!=0) v=par[v];
		
		ans = ori[v][mIndex];
		v=arr[1];
		
        int chainLen = 0;
        int tmp = v;
        while (tmp != 0) {
            chainLen++;
            tmp = par[tmp];
        }
        int[] chain = new int[chainLen];
        tmp = v;
        for (int i = 0; i < chainLen; i++) {
            chain[i] = tmp;
            tmp = par[tmp];
        }
        for (int i = chainLen - 1; i >= 0; i--) {
            int nodeIdx = chain[i];
            int versionLimit = (i == 0) ? Integer.MAX_VALUE : nver[chain[i - 1]];

            Node cur = ll[nodeIdx].head.next;
            while (cur != null && cur.version < versionLimit) {
                if (cur.idx == mIndex) ans = cur.value;
                cur = cur.next;
            }
        }
		
		return ans;
	}
}
