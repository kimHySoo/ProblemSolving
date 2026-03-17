//https://www.acmicpc.net/problem/1197

package Algorithm;
import java.util.*;
import java.io.*;
//union-find base 길찾기
public class Kruskal {
	static int[] parent;
	static int n;
	static class Edge implements Comparable<Edge>{
		int u, v, w;
		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		//거리순으로 정렬하기 위해 오버라이드
		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int sum = 0;
		int cnt = 0;
		parent = new int[n+1];
		for(int i=1;i<n+1;i++) parent[i]=i;
		//경로 저장
		List<Edge> g = new ArrayList<>();
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			g.add(new Edge(x, y, z));
		}
		//최단 거리이기에 정렬 필수
		Collections.sort(g);
		//거리가 짧은 길이 순으로 연결
		//node-1수만큼 간선을 연결하면 종료
		for(Edge e:g) {
			if(find(e.u)!=find(e.v)) {
				union(e.u, e.v);
				sum+=e.w;
				cnt++;
				if(cnt==n-1) break;
			}
		}
		sb.append(sum);
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
