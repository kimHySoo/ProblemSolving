
public class DFSBFS {
	static List<Integer>[] map;
	static int n;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	static int idx = 1;
	public static void main(String[] args) throws IOException {
		n=4;
		int m=5;
		int r=1;
		map = new ArrayList[n+1];
		for(int i=1;i<n+1;i++) map[i] = new ArrayList<>();
		int[][] arr = {{1, 2}, {1, 3}, {1, 4}, {2, 4}, {3, 4}};
		for(int i=0;i<m;i++) {
			int u = arr[i][0];
			int v = arr[i][1];
			map[u].add(v);
			map[v].add(u);
		}
		for(int i=1;i<n+1;i++) Collections.sort(map[i]);

		visited = new boolean[n+1];
		dfs(r);
		sb.append('\n');
		visited = new boolean[n+1];
		bfs(r);
		System.out.print(sb.toString());
	}
	static void dfs(int v) {
		sb.append(v).append(' ');
		visited[v]=true;
		for(int i:map[v]) if(!visited[i]) dfs(i); 
	}
	static void bfs(int v) {
		Deque<Integer> q = new ArrayDeque<>();
		visited[v]=true;
		q.add(v);
		while(!q.isEmpty()) {
			int x = q.poll();
			sb.append(x).append(' ');
			for(int i:map[x]) {
				if(!visited[i]) {
					visited[i]=true;
					q.add(i);
				}
			}
		}
	}
}