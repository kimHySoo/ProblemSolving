//DAG에서 DP는 위상정렬로 해결

package BOJ;
import java.io.*;
import java.util.*;

public class p2637 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        boolean[] fnd = new boolean[n+1];
        int[] indeg = new int[n+1];
        int[] dp = new int[n+1];
        List<int[]>[] g = new ArrayList[n+1];
        for(int i=1;i<n+1;i++) g[i] = new ArrayList<>();
        for(int i=0;i<m;i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	int z = Integer.parseInt(st.nextToken());
        	g[x].add(new int[] {y, z});
        	indeg[y]++;
        	fnd[x]=true;
        }
        dp[n]=1;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(n);
        while(!q.isEmpty()) {
        	int x = q.poll();
        	for(int[] v:g[x]) {
        		dp[v[0]]+=dp[x]*v[1];
        		if(--indeg[v[0]]==0) q.add(v[0]);
        	}
        }
        for(int i=1;i<n+1;i++) if(!fnd[i]) sb.append(i).append(' ').append(dp[i]).append('\n');
        System.out.print(sb.toString());
    }
}