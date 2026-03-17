//https://www.acmicpc.net/problem/1717
package Algorithm;
import java.util.*;
import java.io.*;
public class UnionFind {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n+1];
		for(int i=1;i<n+1;i++) parent[i]=i;
		for(int t=0;t<m;t++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(c==1) sb.append(find(x)==find(y)?"YES":"NO").append('\n');
			else union(x, y);
		}
		System.out.print(sb.toString());
	}
	//조상 찾기
	static int find(int x) {
		if(parent[x]==x) return x;
		// 경로 압축: 찾는 과정에서 거친 모든 노드를 루트에 직접 연결하여
		// 이후 find 연산의 시간을 줄인다.
		return parent[x]=find(parent[x]);
	}
	// 두 집합의 루트를 찾아 하나의 집합으로 합친다
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa!=pb) parent[pb]=pa;
	}
}