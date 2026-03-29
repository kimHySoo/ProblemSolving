//gpt평: 문제 접근은 잘했는데 다익스트라 디테일에서 감점되는 코드예요.
package BOJ;
import java.io.*;
import java.util.*;
public class p1238 {
	static class Edge implements Comparable<Edge>{
		int to, w;
		Edge(int to, int w){
			this.to = to;
			this.w = w;
		}
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	int s = Integer.parseInt(st.nextToken());
    	long INF = Long.MAX_VALUE;
    	List<Edge>[] map = new ArrayList[n+1];
    	List<Edge>[] rev = new ArrayList[n+1];
    	for(int i=1;i<n+1;i++) map[i]= new ArrayList<>();
    	for(int i=1;i<n+1;i++) rev[i]= new ArrayList<>();
    	long[] dist1 = new long[n+1];
    	long[] dist2 = new long[n+1];
    	Arrays.fill(dist1, INF);
    	Arrays.fill(dist2, INF);
    	dist1[s]=0;
    	dist2[s]=0;
    	for(int i=0;i<m;i++) {
    		st = new StringTokenizer(br.readLine());
    		int x = Integer.parseInt(st.nextToken()); 
    		int y = Integer.parseInt(st.nextToken()); 
    		int z = Integer.parseInt(st.nextToken()); 
    		map[x].add(new Edge(y, z));
    		rev[y].add(new Edge(x, z));
    	}
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	pq.add(new Edge(s, 0));
    	while(!pq.isEmpty()) {
    		Edge x = pq.poll();
    		int v = x.to;
    		int d = x.w;
			if(d>dist1[v]) continue;
    		for(Edge e:map[v]) {
    			int nv = e.to;
    			int nd = d+e.w;
    			if(nd>=dist1[nv]) continue;
    			pq.add(new Edge(nv, nd));
    			dist1[nv]=nd;
    		}
    	}
    	pq.add(new Edge(s, 0));
    	while(!pq.isEmpty()) {
    		Edge x = pq.poll();
    		int v = x.to;
    		int d = x.w;
			if(d>dist2[v]) continue;
    		for(Edge e:rev[v]) {
    			int nv = e.to;
    			int nd = d+e.w;
    			if(nd>=dist2[nv]) continue;
    			pq.add(new Edge(nv, nd));
    			dist2[nv]=nd;
    		}
    	}
    	long max = 0;
    	for(int i=1;i<n+1;i++) max = Math.max(max, dist1[i]+dist2[i]);
    	System.out.print(max);
    }
}
