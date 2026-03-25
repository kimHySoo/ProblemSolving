//gpt평:정석
package BOJ;
import java.io.*;
import java.util.*;

public class p6603 {
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int[] arr;
	static int[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	if(n==0) break;
        	arr = new int[n];
        	for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());
        	visited = new int[6];
        	dfs(0, -1);
        	sb.append('\n');
        }
        System.out.print(sb.toString());
    }
    static void dfs(int v, int x) {
    	if(v==6) {
    		for(int i:visited) sb.append(i).append(' ');
    		sb.append('\n');
    		return;
    	}
    	for(int i=x+1;i<n;i++) {
    		visited[v]=arr[i];
    		dfs(v+1, i);
    	}
    }
}