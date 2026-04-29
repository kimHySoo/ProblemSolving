package SWEA

import java.io.*;
import java.util.*;
public class SWEA_1855 {
	
	static StringBuilder sb = new StringBuilder();
	
	static class Node{
		List<Node> child;
		int num;
		Node(int num) {
			this.num = num;
			child = new ArrayList<>();
		}
	}
	
	static int[] dist;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		
		int test = Integer.parseInt(br.readLine());
		
		Queue<Node> q;
		for(int t = 1; t<test+1; t++) {
			sb.append('#').append(t).append(' ');
			
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			q = new ArrayDeque<>();
			int[] turn = new int[n+1];
			int[][] parent = new int[n+1][17];
			
			dist = new int[n+1];
			
			Node[] node = new Node[n+1];
			for(int i=1;i<n+1;i++) node[i] = new Node(i);
			
			for(int i=2;i<n+1;i++) {
				int x = Integer.parseInt(st.nextToken());
				node[x].child.add(node[i]);
				parent[i][0] = x;
			}
			
			parent[1][0]=1;
			
			for(int i=1;i<17;i++) {
				for(int j=1;j<=n;j++) parent[j][i] = parent[parent[j][i-1]][i-1];
			}
			
			q.add(node[1]);
			
			int idx = 1;
			while(!q.isEmpty()) {
				
				Node cur = q.poll();
				turn[idx++] = cur.num;
				
				for(Node next:cur.child) {
			        dist[next.num] = dist[cur.num] + 1;
					q.add(next);
				}
				
			}
			

			long sum = 0;
			
			for(int i=1;i<n;i++) {
				int x = turn[i];
				int y = turn[i+1];
				
				if(dist[x]<dist[y]) {
					int tmp = x;
					x = y;
					y=tmp;
				}
				
				int diff = dist[x] - dist[y];
				
				for(int j=0;j<17;j++) {
					if(((diff>>j)&1)==1) x = parent[x][j];
				}
				
				if(x != y) {
				    for(int j=16; j>=0; j--)
				        if(parent[x][j] != parent[y][j]) {
				            x = parent[x][j];
				            y = parent[y][j];
				        }          
				    x = parent[x][0];
				}
				
				sum+= dist[turn[i]]+dist[turn[i+1]]-dist[x]*2;
				
			}
			
			sb.append(sum).append('\n');
		}
		System.out.print(sb.toString());
	}
}
