package BOJ;
//gpt평: 아이디어는 좋고 충분히 통과 가능한 수준의 DFS이지만,
//문제 조건상 "정확히 m개 선택"을 반영하지 않은 점과 부분집합 전체를 도는 비효율은 아쉽다.
import java.util.*;
import java.io.*;
public class p15686 {
	static int n, m, h, c, min;
	static List<int[]> home, chicken;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int inf = Integer.MAX_VALUE;
		min = inf;
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int x = Integer.parseInt(st.nextToken());
				if(x==1) home.add(new int[] {i, j});
				if(x==2) chicken.add(new int[] {i, j});
			}
		}
		h=home.size();
		c=chicken.size();
		int[] dist = new int[h];
		Arrays.fill(dist, inf);
		dfs(0, 0, dist);
		System.out.println(min);
	}
	static void dfs(int v, int k, int[] dist) {
		if(v==c) {
			if(k==0) return;
			int sum = 0;
			for(int i:dist) sum+=i;
			min = Math.min(min, sum);
			return;
		}
		
		int[] x = chicken.get(v);
		dfs(v+1, k, dist);
		int[] tmp = dist.clone();
		for(int i=0;i<h;i++) {
			int[] y = home.get(i);
			tmp[i] = Math.min(tmp[i], cal(x[0], x[1], y[0], y[1]));
		}
		if(k<m) dfs(v+1, k+1, tmp);

		
	}
	static int cal(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2)+Math.abs(c1-c2);
	}
}
/*
static void dfs(int v, int k, int[] dist) {
    if(k == m) {
        int sum = 0;
        for(int i : dist) sum += i;
        min = Math.min(min, sum);
        return;
    }

    if(v == c) return;

    if(k + (c - v) < m) return;

    dfs(v + 1, k, dist);

    int[] x = chicken.get(v);
    int[] tmp = dist.clone();
    for(int i = 0; i < h; i++) {
        int[] y = home.get(i);
        tmp[i] = Math.min(tmp[i], cal(x[0], x[1], y[0], y[1]));
    }
    dfs(v + 1, k + 1, tmp);
}
*/