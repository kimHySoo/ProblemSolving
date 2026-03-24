package BOJ;
import java.util.*;
import java.io.*;
public class p12851 {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	int max = 200001;
    	int[] visited = new int[max];
    	Arrays.fill(visited, Integer.MAX_VALUE);
    	Deque<int[]> q = new ArrayDeque<int[]>();
    	q.add(new int[] {n, 0});
    	visited[n]=0;
    	int min = Math.abs(m-n);
    	int cnt = 0;
    	while(!q.isEmpty()&&q.peek()[1]<=min) {
    		int[] x = q.poll();
    		int v = x[0];
    		int t = x[1];
    		if(v==m) {
    			min=t;
    			cnt++;
    		}
    		t++;
    		if(max>2*v&&visited[2*v]>=t) {
    			visited[2*v]=t;
    			q.add(new int[] {2*v, t});
    		}
    		if(max>v+1&&visited[v+1]>=t) {
    			visited[v+1]=t;
    			q.add(new int[] {v+1, t});
    		}
    		if(v>0&&visited[v-1]>=t) {
    			visited[v-1]=t;
    			q.add(new int[] {v-1, t});
    		}
    	}
    	System.out.printf(min+"\n"+cnt);
    }
}
