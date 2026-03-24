package BOJ;
import java.io.*;
import java.util.*;

public class p17136 {
	static int[][] map;
	static boolean[][] visited;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new int[10][10];
		visited = new boolean[10][10];
		for(int i=0;i<10;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<10;j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		cnt=101;
		int[] x = {5, 5, 5, 5, 5}; 
		dfs(x);
		System.out.println(cnt==101?-1:cnt);
	}
	static boolean chk(int r, int c, int k) {
		for(int i=0;i<k;i++) {
			for(int j=0;j<k;j++) if(r+i>=10||c+j>=10||map[r+i][c+j]==0||visited[r+i][j+c]) return false;
		}
		return true;
	}
	static void cover(int r, int c, int k, boolean ok) {
		for(int i=0;i<k;i++) {
			for(int j=0;j<k;j++) visited[r+i][j+c]=ok;
		}
	}
	static void dfs(int[] x) {
		int sum = 0;
		for(int i:x) sum+=i;
		if(cnt<25-sum) return;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(!visited[i][j]&&map[i][j]==1) {
					for(int k=1;k<6;k++) {
						if(chk(i, j, k)) {
							if(x[k-1]==0) continue;
							cover(i, j, k, true);
							x[k-1]--;
							dfs(x);
							x[k-1]++;
							cover(i, j, k, false);
						}
						else break;
					}
					return;
				}
			}
		}
		cnt=Math.min(cnt, 25-sum);
	}
}