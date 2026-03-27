//gpt평: 1. 가장 큰 문제: 루트 위치를 매번 선형 탐색함
// 풀이 방향은 매우 좋고 정답에 가깝지만, inorder에서 루트 위치를 매번 찾는 부분 때문에 최악 O(n^2)라 실전에서는 인덱스 배열을 써서 O(n)으로 고치는 게 좋다.
package BOJ;
import java.util.*;
import java.io.*;
public class p2263 {
	static int n;
	static int[] in, post;
	static boolean[] pre;
	static int idx=0;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		in = new int[n];
		post = new int[n];
		pre = new boolean[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) in[i]=Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) post[i]=Integer.parseInt(st.nextToken());
		solve(0, n-1, 0, n-1);
		System.out.print(sb.toString());
	}
	static void solve(int ii, int ij, int pi, int pj) {
//		System.out.println(ii+" "+ij+" "+pi+" "+pj);
		if(ii==ij&&pi==pj) {
			sb.append(in[ii]).append(' ');
			return;
		}
		if(ii>=ij||pi>=pj) return;
		int pivot =post[pj];
		sb.append(pivot).append(' ');
		int idx = ii;
		while(in[idx]!=pivot) idx++;
		solve(ii, idx-1, pi, pi+(idx-ii)-1);
		solve(idx+1, ij, pi+(idx-ii), pj-1);
	}
}
/*
import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] in, post, pos;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        in = new int[n];
        post = new int[n];
        pos = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            in[i] = Integer.parseInt(st.nextToken());
            pos[in[i]] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, n - 1, 0, n - 1);
        System.out.print(sb);
    }

    static void solve(int inL, int inR, int postL, int postR) {
        if (inL > inR || postL > postR) return;

        int root = post[postR];
        sb.append(root).append(' ');

        int rootIdx = pos[root];
        int leftSize = rootIdx - inL;

        solve(inL, rootIdx - 1, postL, postL + leftSize - 1);
        solve(rootIdx + 1, inR, postL + leftSize, postR - 1);
    }
}
*/