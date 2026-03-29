//gpt평:이제는 다익스트라 기본 구현은 안정적으로 된 코드다.
package BOJ;

import java.io.*;
import java.util.*;
public class p1916 {
	static class Edge implements Comparable<Edge>{
		int to;
		long w;
		Edge(int to, long w){
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.w, o.w);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		long INF = Long.MAX_VALUE;
		List<Edge>[] map = new ArrayList[n+1];
		long[] dist = new long[n+1];
		Arrays.fill(dist, INF);
		for(int i=1;i<n+1;i++) map[i] = new ArrayList<>();
		for(int i=0;i<m;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[x].add(new Edge(y, z));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[s]=0;
		pq.add(new Edge(s, 0));
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(cur.w>dist[cur.to]) continue;
			for(Edge next:map[cur.to]) {
				int nv = next.to;
				long nd = cur.w+next.w;
				if(nd>=dist[nv]) continue;
				dist[nv]=nd;
				pq.add(new Edge(nv, nd));
			}
		}
		System.out.println(dist[e]);
	}
}

