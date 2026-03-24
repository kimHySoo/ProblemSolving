/*gpt평:
1. 시작 정점을 pq에 안 넣고, 시작 정점의 인접 정점만 넣는 방식
pq.add(new Edge(s, 0));
2. visited라는 이름이 부정확함 - prev로 바꾸는 게 맞아.
3. 경로가 없는 경우를 고려하지 않음
4. dist[v]=d;는 사실 불필요
5. 시작 정점의 인접 정점 초기화가 중복 역할
*/
//새로운 노드 추가할때 <로 하면 메모리 초과 나기에 <= 이걸로 해야함 
package BOJ;
import java.io.*;
import java.util.*;

public class p11779 {
	static class Edge implements Comparable<Edge>{
		int to, w;
		Edge(int to, int w) {
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
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		List<Edge>[] g = new ArrayList[n+1];
		for(int i=1;i<n+1;i++) g[i]= new ArrayList<>();
		for(int i=0;i<m;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			g[x].add(new Edge(y, z));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int[] visited = new int[n+1];
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[s]=0;
		Queue<Edge> pq = new PriorityQueue<>();
		for(Edge x:g[s]) {
			visited[x.to]=s;
			dist[x.to]=x.w;
			pq.add(x);
		}
		while(!pq.isEmpty()) {
			Edge x = pq.poll();
			int v = x.to;
			int d = x.w;
			if(dist[v]<d) continue;
			dist[v]=d; //이거 필요없음
			if(v==e) break;
			int z = g[v].size();
			for(int i=0;i<z;i++) {
				Edge y = g[v].get(i);
				int nd = d;
				nd+=y.w;
				if(dist[y.to]<=nd) continue;
				dist[y.to]=nd;
				visited[y.to]=v;
				pq.add(new Edge(y.to, nd));
			}
		}
		sb.append(dist[e]).append('\n');
		int cnt = 1;
		List<Integer> num = new ArrayList<Integer>();
		while(e!=s) {
			cnt++;
			num.add(0, e);
			e = visited[e];
			if(e==s) num.add(0, e);
		}
		sb.append(cnt).append('\n');
		for(int x:num) sb.append(x).append(' ');
		System.out.println(sb.toString());
	}
}