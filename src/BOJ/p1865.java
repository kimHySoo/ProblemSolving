//이 문제는 벨만 포드를 이용한 음수사이클 확인하는 문제
package BOJ;
import java.io.*;
import java.util.*;
public class p1865 {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	int test = Integer.parseInt(br.readLine());
    	long INF = Long.MAX_VALUE;
    	while(test-->0) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int n = Integer.parseInt(st.nextToken());
    		int m = Integer.parseInt(st.nextToken());
    		int w = Integer.parseInt(st.nextToken());
    		long[][] map = new long[n+1][n+1];
    		for(int i=1;i<n+1;i++) {
    			Arrays.fill(map[i], INF);
    			map[i][i]=0;
    		}
    		for(int i=0;i<m;i++) {
    			st = new StringTokenizer(br.readLine());
    			int x = Integer.parseInt(st.nextToken());
    			int y = Integer.parseInt(st.nextToken());
    			int z = Integer.parseInt(st.nextToken());
    			map[x][y]=z;
    			map[y][x]=z;
    		}
    		for(int i=0;i<w;i++) {
    			st = new StringTokenizer(br.readLine());
    			int x = Integer.parseInt(st.nextToken());
    			int y = Integer.parseInt(st.nextToken());
    			int z = Integer.parseInt(st.nextToken());
    			map[x][y]=-z;
    		}
    		for(int k=1;k<n+1;k++) {
    			for(int i=1;i<n+1;i++) {
    				for(int j=1;j<n+1;j++) {
    					if(map[i][k]==INF||map[k][j]==INF) continue;
    					map[i][j]=Math.min(map[i][k]+map[k][j], map[i][j]);
    				}
    			}
    		}
    		boolean ok = false;
    		for(int i=1;i<n+1;i++) if(map[i][i]<0) ok=true;
    		sb.append(ok?"YES\n":"NO\n");
    	}
    	System.out.print(sb.toString());
    }
}
