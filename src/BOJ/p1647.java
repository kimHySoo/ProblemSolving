// find 함수에서 parent[x]=find(parent[x]) 로 작성할것
package BOJ;
import java.io.*;
import java.util.*;

public class p1647 {
	static class Edge implements Comparable<Edge>{
		int u, v, w;
		Edge(int u, int v, int w){
			this.u = u;
			this.v = v;
			this.w = w;
		}
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Edge> edge = new ArrayList<>();
        
        for(int i = 0;i<m;i++) {
        	st =  new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	int z = Integer.parseInt(st.nextToken());
        	edge.add(new Edge(x, y, z));
        }
        
        Collections.sort(edge);
        
        parent = new int[n+1];
        size = new int[n+1];
        for(int i=1;i<n+1;i++) {
        	parent[i]=i;
        	size[i]=1;
        }
        
        int idx = 1;
        long dist = 0;
        
        for(Edge e:edge) {

        	if(idx>=n-1) break;
        	if(find(e.u)!=find(e.v)) {
        		dist+=e.w;
        		union(e.u, e.v);
        		idx++;
        	}
        }

        System.out.println(dist);
    }
    static int[] parent;
    static int[] size;
    
    static int find(int x) {
    	if(parent[x]==x) return x;
    	return parent[x]=find(parent[x]);
    }
    
    static void union(int x, int y) {
    	int px = find(x);
    	int py = find(y);
    	if(px!=py) {
    		if(size[px]>size[py]) {
    			parent[py]=px;
    			size[px]+=size[py];
				size[py]=0;
    		}
    		else {
    			parent[px]=py;
    			size[py]+=size[px];
    			size[px]=0;
    		}
    	}
    }
}