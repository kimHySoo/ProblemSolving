//이 코드는 아이디어는 맞는데, 초기 map을 안 채운 것 때문에 반례가 생깁니다.
package BOJ;

import java.util.*;
import java.io.*;
public class p17144 {
	static int n, m;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static List<Dust> dust;
	static int[][] air;
	static int[][] map;
	static class Dust{
		int r, c, w;
		Dust(int r, int c, int w){
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int idx = 0;
		map = new int[n][m];
		air = new int[2][2];
		dust = new ArrayList<>();
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				int x = Integer.parseInt(st.nextToken());
				if(x==-1) {
					air[idx][0]=i;
					air[idx++][1]=j;
				}
				else if(x!=0) {
					dust.add(new Dust(i, j, x));
				}
			}
		}
		while(t-->0) {
			exp();
			upwind();
			downwind();
			dust.clear();
			for(int i=0;i<n;i++) for(int j=0;j<m;j++) if(map[i][j]!=0) dust.add(new Dust(i, j, map[i][j]));
		}
		int ans = 0;
		for(int i=0;i<n;i++) for(int j=0;j<m;j++) if(map[i][j]!=0) ans+=map[i][j];
		System.out.println(ans);
	}
	static void exp(){
		int[][] nmap = new int[n][m];
		for(int i=dust.size()-1;i>=0;i--) {
			Dust id = dust.get(i);
			int r = id.r;
			int c = id.c;
			int w = id.w;
			int nw = w/5;
			for(int j=0;j<4;j++) {
				int nr = r+dr[j];
				int nc = c+dc[j];
				if(nr<0||nc<0||nr>=n||nc>=m||nw<=0) continue;
				if(air[0][0]==nr&&air[0][1]==nc) continue;
				if(air[1][0]==nr&&air[1][1]==nc) continue;
				w-=nw;
				nmap[nr][nc]+=nw;
			}
			nmap[id.r][id.c]+=w;
		}
		for(int i=0;i<n;i++) map[i]=nmap[i].clone();
	}
	static void upwind() {
		int r = air[0][0];
		int c = air[0][1];
		int d = 0;
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		while(true) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0||nc<0||nr>air[0][0]||nc>=m) {
				d++;
				d%=4;
				continue;
			}
			
			if(nr==air[0][0]&&nc==air[0][1]) {
				map[r][c]=0;
				break;
			}
			if(r==air[0][0]&&c==air[0][1]) map[nr][nc]=0;
			map[r][c]=map[nr][nc];
			r=nr;
			c=nc;
		}
	}
	static void downwind() {
		int r = air[1][0];
		int c = air[1][1];
		int d = 3;
		int[] dr = {0, -1, 0, 1};
		int[] dc = {-1, 0, 1, 0};
		while(true) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0||nc<0||nr>=n||nc>=m||nr<air[1][0]) {
				d--;
				if(d<0) d=3;
				continue;
			}
			if(nr==air[1][0]&&nc==air[1][1]) {
				map[r][c]=0;
				break;
			}
			if(r==air[1][0]&&c==air[1][1]) map[nr][nc]=0;
			map[r][c]=map[nr][nc];
			r=nr;
			c=nc;
		}
	}
}

