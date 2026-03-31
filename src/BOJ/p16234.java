//풀이 발상은 맞는데, 상태 변수 하나를 잘못 공유해서 정답 코드로 보기엔 위험하다.
package BOJ;
import java.util.*;
import java.io.*;

public class p16234 {
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<n;j++) map[i][j]=Integer.parseInt(st.nextToken());
        }
        int time = 0;
        List<int[]> uni = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];
        boolean ok = true;
        Queue<int[]> q = new ArrayDeque<>();
        while(ok) {
        	ok=false;
        	visited = new boolean[n][n];
        	for(int i=0;i<n;i++) {
        		for(int j=0;j<n;j++) {
        			if(visited[i][j]) continue;
        			visited[i][j]=true;
        			for(int d=0;d<4;d++) {
        				if(ok) break;
        				int nr = i+dr[d];
        				int nc = j+dc[d];
        				if(nr<0||nc<0||nr>=n||nc>=n) continue;
        				int g = Math.abs(map[i][j]-map[nr][nc]);
        				if(g<min||g>max) continue;
        				ok=true;
        			}
        			if(!ok) continue;
        			int sum = 0;
        			q.add(new int[] {i, j});
        			while(!q.isEmpty()) {
        				int[] x = q.poll();
        				int r = x[0];
        				int c = x[1];
        				sum+=map[r][c];
        				uni.add(new int[] {r, c});
            			for(int d=0;d<4;d++) {
            				int nr = r+dr[d];
            				int nc = c+dc[d];
            				if(nr<0||nc<0||nr>=n||nc>=n||visited[nr][nc]) continue;
            				int g = Math.abs(map[r][c]-map[nr][nc]);
            				if(g<min||g>max) continue;
            				visited[nr][nc]=true;
            				q.add(new int[] {nr, nc});
            			}
        			}
        			int cnt = uni.size();
        			int re = sum/cnt;
        			for(int[] x:uni) {
        				map[x[0]][x[1]]=re;
        			}
        			uni.clear();
        		}
        	}
        	if(!ok) break;
        	time++;
        }
        System.out.println(time);
   	}
}