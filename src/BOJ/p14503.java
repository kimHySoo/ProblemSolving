//장점: 구현 흐름이 문제 조건과 거의 맞고, 코드도 짧고 명확함
//아쉬운 점: 상태 전이를 조금 더 분명하게 쓰면 실수 가능성이 줄어듦

package BOJ;
import java.util.*;
import java.io.*;
public class p14503 {
	static int n, m;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		int cnt = 0;
		while(true) {
			if(map[r][c]==0) {
				cnt++;
				map[r][c]=2;
				continue;
			}
			else if(!chk(r, c)) {
				int nd=(d+2)%4;
				int nr = r+dr[nd];
				int nc = c+dc[nd];
				if(map[nr][nc]==1) break;
				r=nr;
				c=nc;
			}
			else {
				d--;
				if(d<0) d=3;
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(map[nr][nc]==0) {
					r=nr;
					c=nc;
				}
			}
		}
		System.out.println(cnt);
	}
	static boolean chk(int r, int c) {
		for(int i=0;i<4;i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(nr<0||nc<0||nr>=n||nc>=m||map[nr][nc]!=0) continue;
			return true;
		}
		return false;
	}
}

