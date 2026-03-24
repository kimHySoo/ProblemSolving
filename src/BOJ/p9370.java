//gpt평가:다익스트라 구현은 좋고 풀이 방향도 정석이지만, 
//g-h 최단거리를 쓴 것이 아니라 문제의 특정 g-h 간선 가중치를 써야 해서 현재 코드는 오답 가능성이 있다.
package BOJ;

import java.io.*;
import java.util.*;

public class p9370 {
	static int n;
	static List<Edge>[] lst;

	static class Edge implements Comparable<Edge>{
		int to, w;
		Edge(int to, int w){
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			lst = new ArrayList[n+1];
			for(int i=1;i<n+1;i++) lst[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				lst[x].add(new Edge(y, z));
				lst[y].add(new Edge(x, z));
			}
			int[] end = new int[t];
			for(int i=0;i<t;i++) end[i]=Integer.parseInt(br.readLine());
			int[] d1 = dijkstra(s);
			int[] d2 = dijkstra(g);
			int[] d3 = dijkstra(h);
			Arrays.sort(end);
			for(int e:end) {
				int x = Math.min(d1[g]+d3[g]+d3[e], d1[h]+d2[h]+d2[e]);
				if(x==d1[e]) sb.append(e).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	static int[] dijkstra(int s) {
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(s, 0));
		dist[s]=0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int v = cur.to;
			int d = cur.w;
			if(dist[v]<d) continue;
			for(Edge x:lst[v]) {
				int nd = x.w+d;
				if(dist[x.to]<=nd) continue;
				dist[x.to]=nd;
				pq.add(new Edge(x.to, nd));
			}
		}
		return dist;
	}
}