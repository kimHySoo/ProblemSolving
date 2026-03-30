package BOJ;
import java.util.*;
import java.io.*;
public class p17471 {
	static int n, min, total;
	static List<Integer>[] g;
	static int[] num;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		g = new ArrayList[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		num = new int[n+1];
		for(int i=1;i<n+1;i++) {
			num[i]=Integer.parseInt(st.nextToken());
			total+=num[i];
		}
		for(int i=1;i<n+1;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			g[i]= new ArrayList<>();
			for(int j=0;j<x;j++) {
				int y = Integer.parseInt(st.nextToken());
				g[i].add(y);
			}
		}
		int inf = Integer.MAX_VALUE; 
		visited = new boolean[n+1];
		min = inf;
		dfs(0, 0);
		System.out.println(min==inf?-1:min);
	}
	static void dfs(int v, int k) {
		if(v==n) return;
		if(v>0) {
			if(chk1()&&chk2()) {
				int sum = 0;
				for(int i=1;i<n+1;i++) if(visited[i]) sum+=num[i];
				min = Math.min(min, Math.abs(total-2*sum));
			}
		}
		for(int i=k+1;i<n+1;i++) {
			visited[i]=true;
			dfs(v+1, i);
			visited[i]=false;
		}
	}
	static boolean chk1() {
		boolean[] v1 = visited.clone();
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1;i<n+1;i++) if(!v1[i]) {
			q.add(i);
			v1[i]=true;
			break;
		}
		while(!q.isEmpty()) {
			int x = q.poll();
			for(int v:g[x]) {
				if(v1[v]) continue;
				v1[v]=true;
				q.add(v);
			}
		}
		for(int i=1;i<n+1;i++) if(!v1[i]) return false;
		return true;
	}
	static boolean chk2() {
		boolean[] v1 = visited.clone();
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1;i<n+1;i++) if(v1[i]) {
			q.add(i);
			v1[i]=false;
			break;
		}
		while(!q.isEmpty()) {
			int x = q.poll();
			for(int v:g[x]) {
				if(!v1[v]) continue;
				v1[v]=false;
				q.add(v);
			}
		}
		for(int i=1;i<n+1;i++) if(v1[i]) return false;
		return true;
	}
}
