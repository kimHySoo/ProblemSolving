import java.util.*;
import java.io.*;

public class p1765 {
	public static void main(String[] args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		parent = new int[n+1];
		List<Integer>[] l = new ArrayList[n+1];
		for(int i=1;i<n+1;i++) {
			l[i] = new ArrayList<>();
			parent[i] = i;
		}
		
		for(int i=0;i<m;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char r = st.nextToken().charAt(0);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(r=='F') union(x,y);
			else {
				l[x].add(y);
				l[y].add(x);
			}
		}
		
		for(int i=1;i<=n;i++) {
			for(int j:l[i]) union(l[i].get(0),j); 
		}
		
		Set<Integer> s = new HashSet<>();
		for(int i=1;i<=n;i++) s.add(find(i));
		System.out.println(s.size());
		
	}
	static int[] parent;
	static int find(int x) {
		if(parent[x]==x) return x;
		return parent[x] = find(parent[x]);
	}
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px!=py) parent[py] = px;
	}
}
