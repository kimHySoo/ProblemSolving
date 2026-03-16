package BOJ;
import java.util.*;
import java.io.*;
public class p6497 {
	static int n;
	static int[] parent;
	static class Edge implements Comparable<Edge>{
		int u, v, w;
		Edge(int u, int v, int w){
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
	}
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			List<Edge> g = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if(n==0&&m==0) break;
			int sum = 0;
			for(int i=0;i<m;i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				sum+=z;
				g.add(new Edge(x, y, z));
			}
			parent = new int[n];
			for(int i=0;i<n;i++) parent[i]=i;
			Collections.sort(g);
			int cnt = 0;
			for(Edge e:g) {
				if(find(e.u)!=find(e.v)) {
					union(e.u, e.v);
					sum-=e.w;
					if(++cnt==n) break;
				}
			}
			sb.append(sum).append('\n');
		}
		System.out.print(sb.toString());
	}
	static int find(int x) {
		if(parent[x]==x) return x;
		return parent[x]=find(parent[x]);
	}
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px!=py) parent[py]=px;
	}
}
