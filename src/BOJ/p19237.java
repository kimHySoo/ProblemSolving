package BOJ;
import java.io.*;
import java.util.*;

public class p19237{
	static class Shark implements Comparable<Shark>{
		int r, c , v;
		Shark(int r, int c, int v){
			this.r = r;
			this.c = c;
			this.v = v;
		}
		public int compareTo(Shark o) {
			return Integer.compare(this.v, o.v);
		}
	}
	static class Smell{
		int r, c, v, t;
		Smell(int r, int c, int v, int t){
			this.r = r;
			this.c = c;
			this.v = v;
			this.t = t;
		}
	}
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		List<Shark> shark = new ArrayList<>();
		List<Smell> smell = new ArrayList<>();
		List<Smell> next = new ArrayList<>();
		int[][][] sp = new int[m+1][5][4];
		int[] cur = new int[m+1];
		int[][] sharkmap, map;
		sharkmap = new int[n][n];
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int x = Integer.parseInt(st.nextToken());
				if(x==0) continue;
				map[i][j]=x;
				sharkmap[i][j]=x;
				shark.add(new Shark(i, j, x));
				smell.add(new Smell(i, j, x, k));
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<m+1;i++) cur[i]=Integer.parseInt(st.nextToken());
		for(int i=1;i<m+1;i++) {
			for(int j=1;j<5;j++) {
				st = new StringTokenizer(br.readLine());
				for(int l=0;l<4;l++) sp[i][j][l]=Integer.parseInt(st.nextToken()); 
			}
		}
		int time = 0;
		while(time++<1000) {
			Collections.sort(shark);
			for(Shark s:shark) {
				int r = s.r;
				int c = s.c;
				int v = s.v;
				int d = cur[v];
				sharkmap[r][c]=0;
				boolean pass = false;
				boolean eat = false;
				for(int i=0;i<4;i++) {
					int nr = r+dr[sp[v][d][i]];
					int nc = c+dc[sp[v][d][i]];
					if(nr<0||nc<0||nr>=n||nc>=n) continue;
					if(map[nr][nc]!=0) continue;
					if(sharkmap[nr][nc]!=0) {
						eat=true;
						break;
					}
					pass = true;
					sharkmap[nr][nc]=v;
					cur[v]=sp[v][d][i];
					break;
				}
				if(eat) continue;
				if(pass) continue;
				for(int i=0;i<4;i++) {
					int nr = r+dr[sp[v][d][i]];
					int nc = c+dc[sp[v][d][i]];
					if(nr<0||nc<0||nr>=n||nc>=n) continue;
					if(map[nr][nc]!=v) continue;
					pass = true;
					sharkmap[nr][nc]=v;
					cur[v]=sp[v][d][i];
					break;
				}
			}
			shark.clear();
			for(Smell s:smell) {
				if(--s.t==0) map[s.r][s.c]=0;
				else if(sharkmap[s.r][s.c]!=0) map[s.r][s.c]=0;
				else next.add(s);
			}
			for(int i=0;i<n;i++) for(int j=0;j<n;j++) {
				if(sharkmap[i][j]==0) continue;
				shark.add(new Shark(i, j, sharkmap[i][j]));
				map[i][j]=sharkmap[i][j];
				next.add(new Smell(i, j, map[i][j], k));
			}
			smell = new ArrayList<>(next);
			next.clear();
			if(shark.size()==1) break;
		}
		System.out.println(time>1000?-1:time);
	}
}