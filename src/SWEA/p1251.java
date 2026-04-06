package SWEA;
import java.io.*;
import java.util.*;
public class p1251{
	static class Edge implements Comparable<Edge>{
		int v;
		long w;
		Edge(int v, long w){
			this.v = v;
			this.w = w;
		}
		public int compareTo(Edge o) {
			return Long.compare(this.w, o.w);
		}
	}
	static int[][] island;
	static boolean[] visited;
	static int n;
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for(int t=1;t<=test;t++) {
			sb.append('#').append(t).append(' ');
			n = Integer.parseInt(br.readLine());
			island = new int[n+1][2];
			for(int i=0;i<2;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=1;j<n+1;j++) island[j][i]=Integer.parseInt(st.nextToken());
			}
			double e = Double.parseDouble(br.readLine());
			pq.add(new Edge(1, 0));
			int x = 0;
			long cost = 0;
			visited = new boolean[n+1];
			while(!pq.isEmpty()) {
				Edge cur = pq.poll();
				if(visited[cur.v]) continue;
				visited[cur.v]=true;
				cost+=cur.w;
				if(++x==n) break;
				for(int i=1;i<n+1;i++) {
					if(visited[i]) continue;
					pq.add(new Edge(i, dist(island[i][0], island[i][1], island[cur.v][0], island[cur.v][1])));
				}
			}
			pq.clear();
			cost=Math.round(cost*e);
			sb.append(cost).append('\n');
		}
		System.out.println(sb.toString());
	}
	static long dist(int x, int y, int x1, int y1) {
		long dx = x-x1;
		long dy = y-y1;
		return dx*dx+dy*dy;
	}
}
