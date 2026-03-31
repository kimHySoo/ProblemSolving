package BOJ;
import java.util.*;
import java.io.*;

public class p16236 {
	static int[][] map;
	static int n, m;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, -1, 0, 1};
	static class Fish implements Comparable<Fish>{
		int r, c, w;
		Fish(int r, int c, int w){
			this.r = r;
			this.c = c;
			this.w = w;
		}
		@Override
		public int compareTo(Fish f) {
			if(this.w!=f.w) return Integer.compare(this.w, f.w);
			else if(this.r!=f.r) return Integer.compare(this.r, f.r);
			else return Integer.compare(this.c, f.c);
		}
	}
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        int r = 0;
        int c = 0;
        m=2;
        for(int i=0;i<n;i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j=0;j<n;j++) {
        		map[i][j]=Integer.parseInt(st.nextToken());
        		if(map[i][j]==9) {
        			r=i;
        			c=j;
        			map[i][j]=0;
        		}
        	}
        }
        int time = 0;
        int cnt = 0;
    	Queue<int[]> q = new ArrayDeque<>();
    	List<Fish> fish = new ArrayList<>();
    	int[][] visited = new int[n][n];
        while(true) {
        	for(int i=0;i<n;i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
        	visited[r][c]=0;
        	fish.clear();
        	q.clear();
        	q.add(new int[] {r, c, 0});
        	int t = n*n+1;
        	while(!q.isEmpty()) {
        		int[] x = q.poll();
        		r = x[0];
        		c = x[1];
        		int w = x[2];
        		if(t<=w) break;
        		for(int i=0;i<4;i++) {
        			int nr = r+dr[i];
        			int nc = c+dc[i];
        			if(nr<0||nc<0||nr>=n||nc>=n||visited[nr][nc]<=w+1||map[nr][nc]>m) continue;
        			visited[nr][nc]=w+1;
        			if(map[nr][nc]<m&&map[nr][nc]>0) {
        				fish.add(new Fish(nr, nc, w+1));
        				t = w+1;
        			}
        			q.add(new int[] {nr, nc, w+1});
        		}
        	}
        	if(fish.isEmpty()) break;
        	Collections.sort(fish);
        	r = fish.get(0).r;
        	c = fish.get(0).c;
        	map[r][c]=0;
        	if(++cnt==m) {
        		cnt = 0;
        		m++;
        	}
        	time+=t;
        }
        System.out.println(time);
    }
}