//로직은 잘 짰고 출력 배열 크기만 바로잡으면 훨씬 완성도 높아져.
package BOJ;
import java.io.*;
import java.util.*;

public class p2448 {
	static boolean[][] star;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		star = new boolean[2*n][2*n];
		dfs(0, n-1, n);
		for(int i=0;i<2*n;i++) {
			for(int j=0;j<2*n;j++) sb.append(star[i][j]?'*':' ');
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	
	static void dfs(int r, int c, int v) {
		if(v==3) {
			star[r][c]=star[r+1][c-1]=star[r+1][c+1] = true;
			for(int i=0;i<5;i++) star[r+2][c-2+i]=true;
			return;
		}
		dfs(r, c, v/2);
		dfs(r+v/2, c-v/2, v/2);
		dfs(r+v/2, c+v/2, v/2);
	}
}