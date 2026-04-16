// 옛날 색종이 문제처럼 이중 for문안에 return 넣는건 너무 빡셈

package BOJ;
import java.io.*;
import java.util.*;

public class p2339 {
	static int[][] map;
	static boolean fin;
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        for(int i=0;i<9;i++) {
        	String line = br.readLine();
        	for(int j=0;j<9;j++) map[i][j]=line.charAt(j)-'0';
        }
        fin = false;
        dfs(0);
        
        System.out.println(sb.toString());
    }
    static void dfs(int v) {
    	if(v==81&&!fin) {
    		for(int i=0;i<9;i++) {
    			for(int j=0;j<9;j++) sb.append(map[i][j]);
    			sb.append('\n');
    		}
    		fin = true;
    		return;
    	}
    	if(v==81||fin) return;
    	int r = v/9;
    	int c = v%9;
    	if(map[r][c]==0) {
    		for(int i=1;i<10;i++) {
    			if(!check(r,c,i)) continue;
    			map[r][c]=i;
    			dfs(v+1);
    			if(fin) return;
    			map[r][c]=0;
    		}
    	}
    	else dfs(v+1);

    }
    static boolean check(int r, int c, int x) {
    	for(int i=0;i<9;i++) if(c!=i&&x==map[r][i]) return false;
    	for(int i=0;i<9;i++) if(r!=i&&x==map[i][c]) return false;
    	int dr = (r/3)*3;
    	int dc = (c/3)*3;
    	for(int i=0;i<3;i++) for(int j=0;j<3;j++) {
    		if(r==dr+i&&dc+j==c) continue;
    		if(map[dr+i][dc+j]==x) return false;
    	}
    	return true;
    }
}