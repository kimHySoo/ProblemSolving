//gpt평: 정석적인 풀이+시간 단축위해 각 칸별 점수를 dp로 저장
//주사위 2개 열로 하는게 개인적으로 안정적인것 같다. 1개로 하니까 너무 어지럽고 실수가 잦다.

package BOJ;

import java.io.*;
import java.util.*;

public class p23288 {
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int[] df = {6, 5, 1, 2};
	static int[] ds = {6, 3, 1, 4};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int r = 1;
		int c = 1;
		int[][] map = new int[n+1][m+1];
		boolean[][] visited = new boolean[n+1][m+1];
		for(int i=1;i<n+1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<m+1;j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		int d = 0;
		int ans = 0;
		for(int i=0;i<k;i++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<=0||nc<=0||nr>n||nc>m) {
				d=(d+2)%4;
				nr=r+dr[d];
				nc=c+dc[d];
			}
			roll(d);
			int B = map[nr][nc];
			if(df[0]>B) d++;
			else if(df[0]<B) d--;
			if(d==4) d=0;
			if(d==-1) d=3;
			Deque<int[]> q = new ArrayDeque<>();
			visited = new boolean[n+1][m+1];
			r=nr;
			c=nc;
			visited[r][c]=true;
			q.add(new int[] {r, c});
			int cnt = 1;
			while(!q.isEmpty()) {
				int[] a = q.poll();
				int ar = a[0];
				int ac = a[1];
				for(int j=0;j<4;j++) {
					int nar = ar+dr[j];
					int nac = ac+dc[j];
					if(nar<=0||nac<=0||nar>n||nac>m) continue;
					if(B!=map[nar][nac]) continue;
					if(visited[nar][nac]) continue;
					visited[nar][nac] = true;
					cnt++;
					q.add(new int[] {nar, nac});
				}
			}
			ans+=cnt*B;
		}
		System.out.println(ans);
	}
	static void roll(int x) {
		if(x==0) {
			int tmp = ds[0];
			for(int i=0;i<3;i++) ds[i]=ds[i+1];
			ds[3]=tmp;
			df[0]=ds[0];
			df[2]=ds[2];
		}
		if(x==2) {
			int tmp = ds[3];
			for(int i=3;i>0;i--) ds[i]=ds[i-1];
			ds[0]=tmp;
			df[0]=ds[0];
			df[2]=ds[2];
		}
		if(x==3) {
			int tmp = df[3];
			for(int i=3;i>0;i--) df[i]=df[i-1];
			df[0]=tmp;
			ds[0]=df[0];
			ds[2]=df[2];
		}
		if(x==1) {
			int tmp = df[0];
			for(int i=0;i<3;i++) df[i]=df[i+1];
			df[3]=tmp;
			ds[0]=df[0];
			ds[2]=df[2];
		}
	}
}
