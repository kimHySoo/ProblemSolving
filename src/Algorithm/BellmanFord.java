package Algorithm;
import java.io.*;
import java.util.*;
public class BellmanFord {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	List<int[]> edge = new ArrayList<>();
    	for(int i=0;i<m;i++) {
    		st = new StringTokenizer(br.readLine());
    		int x = Integer.parseInt(st.nextToken());
    		int y = Integer.parseInt(st.nextToken());
    		int z = Integer.parseInt(st.nextToken());
    		edge.add(new int[] {x, y, z});
    	}
    	long[] dist = new long[n+1];
    	long INF = Long.MAX_VALUE;
    	Arrays.fill(dist, INF);
    	dist[1]=0;
    	for(int i=1;i<n;i++) {
    		for(int[] e:edge) {
    			if(dist[e[0]]!=INF&&dist[e[1]]>dist[e[0]]+e[2]) dist[e[1]]=dist[e[0]]+e[2];
    		}
    	}
		for(int[] e:edge) {
			if(dist[e[0]]!=INF&&dist[e[1]]>dist[e[0]]+e[2]) {
				System.out.println(-1);
				return;
			}
		}
    	for(int i=2;i<n+1;i++) {
    		sb.append(dist[i]==INF?-1:dist[i]).append('\n');
    	}
    	System.out.print(sb.toString());
    }
}
