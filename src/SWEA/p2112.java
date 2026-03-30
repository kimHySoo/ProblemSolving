package SWEA;
import java.util.*;
import java.io.*;

public class p2112 {
	static int d, w, k;
	static boolean[][] map, mc;
	static int[] visited;
	static boolean ok;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		for(int t=1;t<test+1;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new boolean[d][w];
			mc = new boolean[d][w];
			for(int i=0;i<d;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<w;j++) map[i][j]=(Integer.parseInt(st.nextToken())==0);
			}
			visited = new int[d];
			ok=chk();
			int idx = 0;
			while(!ok) {
				idx++;
				visited = new int[d];
				dfs(0, -1, idx);
			}
			sb.append('#').append(t).append(' ').append(idx).append('\n');
		}
		System.out.print(sb.toString());
	}
	static void dfs(int v, int k, int c) {
		if(ok) return;
		if(v==c) {
			if(chk()) ok=true;
			return;
		}
		for(int i=k+1;i<d;i++) {
			visited[i]=2;
			dfs(v+1, i, c);
			visited[i]=1;
			dfs(v+1, i, c);
			visited[i]=0;
			
		}
	}
	static boolean chk() {
		for(int i=0;i<d;i++) {
			if(visited[i]==0) mc[i]=map[i].clone();
			else if(visited[i]==1) Arrays.fill(mc[i], false);
			else Arrays.fill(mc[i], true);
		}
		for(int i=0;i<w;i++) {
			boolean isok = false;
			int i1 = 0;
			int i2 = 0;
			for(int j=0;j<d;j++) {
				if(mc[j][i]) {
					i1++;
					i2=0;
					if(i1==k) {
						isok=true;
						break;
					}
				}
				else {
					i1=0;
					i2++;
					if(i2==k) {
						isok=true;
						break;
					}
				}
			}
			if(!isok) return false;
		}
		return true;
	}
}
