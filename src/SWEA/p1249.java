package SWEA;
import java.util.*;
import java.io.*;

public class p1249 {
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		for(int t=1;t<test+1;t++) {
			sb.append('#').append(t).append(' ');
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			int[][] visited = new int[n][n];
			for(int i=0;i<n;i++) {
				String line = br.readLine();
				Arrays.fill(visited[i], -1);
				for(int j=0;j<n;j++) map[i][j]=line.charAt(j)-'0';
			}
			Queue<int[]> q = new ArrayDeque<int[]>();
			q.add(new int[] {0, 0});
			visited[0][0]=0;
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				for(int i=0;i<4;i++) {
					int nr = r+dr[i];
					int nc = c+dc[i];
					if(nr<0||nc<0||nr>=n||nc>=n) continue;
					if(visited[nr][nc]!=-1&&visited[nr][nc]<=visited[r][c]+map[nr][nc]) continue;
					visited[nr][nc]=visited[r][c]+map[nr][nc];
					q.add(new int[] {nr, nc});
				}
			}
			System.out.println(Arrays.deepToString(visited));
			sb.append(visited[n-1][n-1]).append('\n');
		}
		System.out.print(sb.toString());
	}
}
