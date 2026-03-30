package SWEA;
import java.util.*;
import java.io.*;

public class p2383 {
	static int n, h, min;
	static int[] value;
	static boolean[] visited;
	static List<int[]> ple;
	static List<int[]> str;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		for(int t=1;t<test+1;t++) {
			n = Integer.parseInt(br.readLine());
			value = new int[2];
			int idx = 0;
			ple = new ArrayList<>();
			str = new ArrayList<>();
			for(int i=0;i<n;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					int x = Integer.parseInt(st.nextToken());
					if(x==1) ple.add(new int[] {i, j});
					if(x>=2) {
						value[idx++]=x;
						str.add(new int[] {i, j});
					}
				}
			}
			min = Integer.MAX_VALUE;
			h=ple.size();
			visited = new boolean[h];
			dfs(0,-1);
			sb.append('#').append(t).append(' ').append(min).append('\n');
		}
		System.out.print(sb.toString());
	}
	static void dfs(int v, int k) {
		if(v>h) return;
		min = Math.min(min,simul());
		for(int i=k+1;i<h;i++) {
			visited[i]=true;
			dfs(v+1, i);
			visited[i]=false;
		}
	}
	static int simul() {
		int x = 0;
		for(int i=0;i<h;i++) if(visited[i]) x++; 
		int[] d1 = new int[x];
		int[] d2 = new int[h-x];
		int i1 = 0;
		int i2 = 0;
		for(int i=0;i<h;i++) {
			int[] p = ple.get(i);
			if(visited[i]) d1[i1++]=dist(p[0], p[1], str.get(0)[0], str.get(0)[1]);
			else d2[i2++]=dist(p[0], p[1], str.get(1)[0], str.get(1)[1]);
		}
		Arrays.sort(d1);
		for(int i=3;i<x;i++) d1[i]=Math.max(d1[i], d1[i-3]+value[0]);
		Arrays.sort(d2);
		for(int i=3;i<h-x;i++) d2[i]=Math.max(d2[i], d2[i-3]+value[1]);
		if(x==0) return d2[h-x-1]+value[1]+1;
		else if(x==h) return d1[x-1]+value[0]+1;
		else return Math.max(d1[x-1]+value[0]+1, d2[h-x-1]+value[1]+1);

	}
	static int dist(int pr, int pc, int sr, int sc) {
		return Math.abs(pr-sr)+Math.abs(pc-sc);
	}
}
