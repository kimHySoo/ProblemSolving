package SWEA;
import java.util.*;
import java.io.*;

public class p1267 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<11;t++) {
			sb.append('#').append(t).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			List<Integer>[] g = new ArrayList[v+1];
			for(int i=1;i<v+1;i++) g[i]=new ArrayList<>();
			int[] indeg = new int[v+1];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<e;i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				g[x].add(y);
				indeg[y]++;
			}
			boolean[] visited = new boolean[v+1];
			Queue<Integer> q = new ArrayDeque<Integer>();
			for(int i=1;i<v+1;i++) if(indeg[i]==0) {
				visited[i]=true;
				q.add(i);
			}
			while(!q.isEmpty()) {
				int x = q.poll();
				sb.append(x).append(' ');
				for(int y:g[x]) {
					if(visited[y]) continue;
					if(--indeg[y]==0) {
						visited[y]=true;
						q.add(y);
					}
				}
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}
