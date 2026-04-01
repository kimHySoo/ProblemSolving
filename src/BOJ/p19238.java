//풀이 방향은 맞고 거의 다 왔는데, 구현형 문제에서 가장 중요한 “상태 변경 순서”와 “변수 분리”가 아직 약하다.
package BOJ;
import java.util.*;
import java.io.*;

public class p19238 {
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	static class Customer{
		int x, y, gx, gy;
		Customer(int x, int y, int gx, int gy){
			this.x = x;
			this.y = y;
			this.gx = gx;
			this.gy = gy;
		}
	}
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][n+1];
        for(int i=1;i<1+n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=1;j<1+n;j++) map[i][j]=Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        boolean[][] visited = new boolean[n+1][n+1];
        List<Customer> cus = new ArrayList<>();
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	map[x][y]=2;
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	cus.add(new Customer(x, y, a, b));
        }
        boolean ok = true;
        Queue<int[]> q = new ArrayDeque<>();
        while(ok) {
        	int nx = n;
        	int ny = n;
        	q.add(new int[] {r, c, 0});
        	visited = new boolean[n+1][n+1];
        	visited[r][c]=true;
        	boolean pass = false;
        	int dist = n*n;
        	while(!q.isEmpty()) {
        		int[] x = q.poll();
        		r = x[0];
        		c = x[1];
        		int l = x[2];
        		if(l>dist||l>k) break;
        		if(map[r][c]==2) {
        			pass=true;
        			dist = Math.min(dist, l);
        			if(r<nx) {
        				nx=r;
        				ny=c;
        			}
        			else if(r==nx) ny = Math.min(ny, c);
        		}
        		for(int i=0;i<4;i++) {
        			int nr = r+dr[i];
        			int nc = c+dc[i];
        			if(nr<1||nc<1||nr>n||nc>n) continue;
        			if(visited[nr][nc]||map[nr][nc]==1) continue;
        			visited[nr][nc]=true;
        			q.add(new int[] {nr, nc, l+1});
        		}
        	}
        	k-=dist;
        	map[nx][ny]=0;
        	q.clear();
        	ok=pass;
        	if(!ok) break;
        	visited = new boolean[n+1][1+n];
        	visited[nx][ny]=true;
        	q.add(new int[] {nx, ny, 0});
        	for(int i=0;i<cus.size();i++) {
        		Customer cs = cus.get(i);
        		if(cs.x==nx&&cs.y==ny) {
        			nx = cs.gx;
        			ny = cs.gy;
        			cus.remove(i);
        			break;
        		}
        	}
        	pass = false;
        	while(!q.isEmpty()) {
        		int[] x = q.poll();
        		r = x[0];
        		c = x[1];
        		int l = x[2];
        		if(l>k) break;
        		if(r==nx&&c==ny) {
        			pass=true;
        			dist = l;
        		}
        		for(int i=0;i<4;i++) {
        			int nr = r+dr[i];
        			int nc = c+dc[i];
        			if(nr<1||nc<1||nr>n||nc>n) continue;
        			if(visited[nr][nc]||map[nr][nc]==1) continue;
        			visited[nr][nc]=true;
        			q.add(new int[] {nr, nc, l+1});
        		}
        	}
        	q.clear();
        	ok=pass;
        	if(!ok) break;
        	k+=dist;
        	r=nx;
        	c=ny;
        	if(cus.size()==0) break;
        }
        System.out.println(ok?k:-1);
    }
}