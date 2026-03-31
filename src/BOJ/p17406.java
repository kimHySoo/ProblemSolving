//풀이 방향은 매우 좋고 정답 접근도 맞는데, 구현이 조금 위험한 스타일이야.
package BOJ;
import java.util.*;
import java.io.*;

public class p17406 {
	static int n, m, k, min;
	static int[][] map, spin, sim;
	static int[] cho;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        cho = new int[k];
        spin = new int[k][3];
        map = new int[n+1][m+1];
        sim = new int[n+1][m+1];
        for(int i=1;i<n+1;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=1;j<m+1;j++) map[i][j]=Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<k;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<3;j++) spin[i][j]=Integer.parseInt(st.nextToken());
        }
        dfs(0);
        System.out.println(min);
    }
    static void dfs(int v) {
    	if(v==k) {
    		kspin();
    		return;
    	}
    	for(int i=0;i<k;i++) {
    		boolean ok = true;
    		for(int j=0;j<v;j++) {
    			if(cho[j]==i) {
    				ok=false;
    				break;
    			}
    		}
    		if(!ok) continue;
    		cho[v]=i;
    		dfs(v+1);
    	}
    }
    static void kspin() {
    	for(int i=0;i<n+1;i++) sim[i]=map[i].clone();
    	for(int i=0;i<k;i++) {
    		int sr = spin[cho[i]][0]-spin[cho[i]][2];
    		int sc = spin[cho[i]][1]-spin[cho[i]][2];
    		int er = spin[cho[i]][0]+spin[cho[i]][2];
    		int ec = spin[cho[i]][1]+spin[cho[i]][2];
    		while(sr<er) {
    			int r = sr;
    			int c = sc;
    			int d = 0;
    			int tmp = sim[r][c];
    			while(true) {
    				int nr = r+dr[d];
    				int nc = c+dc[d];
    				if(nr<sr||nc<sc||nr>er||nc>ec) {
    					d++;
    					continue;
    				}
    				sim[r][c]=sim[nr][nc];
    				r=nr;
    				c=nc;
    				if(nr==sr&&nc==sc) break;
    			}
    			sim[r][c+1]=tmp;
    			sr++;
    			sc++;
    			ec--;
    			er--;
    		}
    	}
    	for(int i=1;i<n+1;i++) {
    		int sum = 0;
    		for(int j=0;j<m+1;j++) sum+=sim[i][j];
    		min = Math.min(sum, min);
    	}
    }
}