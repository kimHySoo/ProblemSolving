import java.util.*;
import java.io.*;

public class p2538 {
	public static void main(String[] args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[n][m];
		boolean[][] visited = new boolean[n][m];
		
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int r=y1;r<y2;r++) for(int c = x1;c<x2;c++) map[r][c] = true;
			
		}
		
		int cnt = 0;
		Queue<int[]> q= new ArrayDeque<>();
		List<Integer> ans = new ArrayList<>();
		
		int[] dr = {1, 0, -1, 0};
		int[] dc = {0, 1, 0, -1};
		for(int i=0;i<n;i++) for(int j=0;j<m;j++) {
			if(visited[i][j]||map[i][j]) continue;
			visited[i][j] = true;
			cnt++;
			q.add(new int[] {i,j});
			int w = 0;
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				w++;
				for(int d=0;d<4;d++) {
					int nr = dr[d]+cur[0];
					int nc = dc[d]+cur[1];
					if(nr<0||nc<0||nr>=n||nc>=m||visited[nr][nc]||map[nr][nc]) continue;
					q.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
				
			}
			ans.add(w);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt+"\n");
		Collections.sort(ans);
		for(int a:ans) sb.append(a+" ");
		System.out.println(sb.toString());
	}
}
