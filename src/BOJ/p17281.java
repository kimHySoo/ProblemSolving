//문제 핵심을 잘 구현한 정석 풀이지만, visited 같은 변수명과 중복 체크 방식을 개선하면 훨씬 더 좋은 코드가 된다.
package BOJ;
import java.util.*;
import java.io.*;

public class p17281 {
	static int[][] result;
	static int[] visited;
	static int n, max;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        visited = new int[9];
        max = 0;
        n = Integer.parseInt(br.readLine());
        result = new int[10][n];
        for(int i=0;i<n;i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j=0;j<9;j++) result[j+1][i] = Integer.parseInt(st.nextToken());
        }
        dfs(0);
        System.out.println(max);
    }
    static void dfs(int v) {
    	if(v==9) {
    		game();
    		return;
    	}
    	if(v==3) {
    		visited[v]=1;
    		dfs(v+1);
    		return;
    	}
    	for(int i=1;i<10;i++) {
    		boolean ok = false;
    		if(i==1) continue;
    		for(int j=0;j<v;j++) {
    			if(i==visited[j]) {
    				ok = true;
    				break;
    			}
    		}
    		if(ok) continue;
    		visited[v]=i;
    		dfs(v+1);
    	}
    }
    static void game() {
    	int score = 0;
    	int g = 0;
    	int player = 0;
    	while(g<n) {
    		boolean[] home = new boolean[3];
    		int out = 0;
    		while(out<3) {
    			int x = result[visited[player]][g];
    			if(x==0) out++;
    			else if(x==4) {
    				for(boolean b:home) if(b) score++;
    				score++;
    				home = new boolean[3];
    			}
    			else {
    				for(int i=2;i>=0;i--) {
    					if(!home[i]) continue;
    					home[i]=false;
    					if(i+x>=3) score++;
    					else home[i+x]=true;
    				}
    				home[x-1]=true;
    			}
    			player++;
    			if(player==9) player%=9;
    		}
    		g++;
    	}
    	max = Math.max(max, score);
    }
}