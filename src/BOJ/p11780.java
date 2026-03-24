//진짜 재귀써야하나 했는데 반복문으로 처리가능
package BOJ;
import java.io.*;
import java.util.*;

public class p11780 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] map = new int[n+1][n+1];
		g = new int[n+1][n+1];
		int max = 100000001;
		for(int i=1;i<1+n;i++) {
			Arrays.fill(map[i], max);
			map[i][i]=0;
		}
		for(int i=0;i<m;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[x][y]=Math.min(map[x][y],z);
		}
		for(int k=1;k<n+1;k++) {
			for(int i=1;i<n+1;i++) for(int j=1;j<n+1;j++) {
				if(map[i][j]>map[i][k]+map[k][j]) {
					map[i][j]=map[i][k]+map[k][j];
					g[i][j]=k;
				}
			}
		}
		for(int i=1;i<n+1;i++) {
			for(int j=1;j<n+1;j++) sb.append(map[i][j]!=max?map[i][j]:0).append(' ');
			sb.append('\n');
		}
		for(int i=1;i<n+1;i++) {
			for(int j=1;j<n+1;j++) {
				root = new StringBuilder();
				cnt = 2;
				if(map[i][j]==0||map[i][j]==max) root.append(0);
				else {
					dfs(i, j);
					sb.append(cnt);
					root.append(' ').append(j);
				}
				sb.append(root).append('\n');
			}
		}
		System.out.print(sb.toString());
	}
	static int cnt;
	static int[][] g;
	static StringBuilder root;
	static void dfs(int x, int y) {
		if(g[x][y]==0) {
			root.append(' ').append(x);
			return;
		}
		int t = g[x][y];
		cnt++;
		dfs(x, t);
		dfs(t, y);
	}
}

/* 재귀 대신 반복문으로 복원
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(map[i][j] == INF ? 0 : map[i][j]).append(' ');
            }
            sb.append('\n');
        }

        // 경로 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j || map[i][j] == INF) {
                    sb.append(0).append('\n');
                    continue;
                }

                path = new ArrayList<>();
                restore(i, j);
                path.add(j);

                sb.append(path.size()).append(' ');
                for (int x : path) {
                    sb.append(x).append(' ');
                }
                sb.append('\n');
            }
        }

*/