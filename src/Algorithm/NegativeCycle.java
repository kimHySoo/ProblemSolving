package Algorithm;

import java.util.*;
import java.io.*;
public class NegativeCycle {
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
    		List<int[]> edge = new ArrayList<int[]>();
    		for(int i=0;i<m;i++) {
    			st = new StringTokenizer(br.readLine());
    			int x = Integer.parseInt(st.nextToken());
    			int y = Integer.parseInt(st.nextToken());
    			int z = Integer.parseInt(st.nextToken());
    			edge.add(new int[] {x, y, z});
    			edge.add(new int[] {y, x, z});
    		}
    		for(int i=0;i<w;i++) {
    			st = new StringTokenizer(br.readLine());
    			int x = Integer.parseInt(st.nextToken());
    			int y = Integer.parseInt(st.nextToken());
    			int z = Integer.parseInt(st.nextToken());
    			edge.add(new int[] {x, y, -z});
    		}
    		long[] dist = new long[n+1];
    		boolean ok = false;
    		for(int i=0;i<n;i++) {
    			for(int[] e:edge) {
    				if(dist[e[1]]>dist[e[0]]+e[2]) {
    					dist[e[1]]=dist[e[0]]+e[2];
    					if(i==n-1) ok = true;
    				}
    			}
    		}
    		sb.append(ok?"YES\n":"NO\n");
    	}
    	System.out.print(sb.toString());
    }
}
