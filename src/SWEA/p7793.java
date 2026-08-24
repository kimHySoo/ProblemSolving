import java.util.*;
import java.io.*;

public class p7793 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[] dr = {1, 0, -1, 0};
		int[] dc = {0, 1, 0, -1};
		for(int t = 1;t<=test;t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][] map = new int[n][m];
			boolean[][] visited = new boolean[n][m];
			List<int[]> su = new ArrayList<>();
			List<int[]> de = new ArrayList<>();
			for(int i=0;i<n;i++) {
				String line = br.readLine();
			
				for(int j=0;j<m;j++) {
					char c = line.charAt(j);
					if(c=='D') {
						map[i][j] = 1;
					} else if(c=='S') {
						map[i][j] = 0;
						visited[i][j] = true;
						su.add(new int[] {i, j});
					} else if(c=='.') {
						map[i][j] = 0;
					} else if(c=='X') {
						map[i][j] = -2;
					} else if(c=='*') {
						map[i][j] = 2;
						de.add(new int[] {i, j});
					}
				}
			}
			
			int ans = 0;
			int time = 0;
			while(ans==0) {
				time++;
				ArrayList<int[]> mid = new ArrayList<>(de);
				de.clear();
				
				for(int[] x:mid) {
					for(int i=0;i<4;i++) {
						int r = x[0]+dr[i];
						int c = x[1]+dc[i];
						if(r<0||c<0||r>=n||c>=m||map[r][c]!=0) continue;
						map[r][c]=2;
						de.add(new int[] {r, c});
					}
				}
				
				mid = new ArrayList<>(su);
				su.clear();
				for(int[] x:mid) {
					for(int i=0;i<4;i++) {
						int r = x[0]+dr[i];
						int c = x[1]+dc[i];
						if(r<0||c<0||r>=n||c>=m||visited[r][c]) continue;
						if(map[r][c]==1) {
							ans = time;
							break;
						}
						if(map[r][c]!=0) continue;
						su.add(new int[] {r, c});
						visited[r][c] = true;
					}
				}
				if(ans==0&&su.isEmpty()) ans = -1;
				
			}
			
			sb.append("#"+t+" ").append(ans==-1?"GAME OVER":ans).append('\n');
		}
		System.out.println(sb.toString());
	}
}



/*

10
5 5
S*..D
.....
.....
.....
.....
3 3
...
S*D
...
5 5
.....
.....
S....
..XXX
..X.D
5 5
*****
*****
S....
*****
....D
5 3
D*S
.X.
.X.
.X.
...
5 3
D*S
...
.X.
.X.
...
3 3
S.*
...
.D.
3 3
S.*
..*
.D.
3 3
S.*
.*.
.D.
2 3
S.*
..D


 */



