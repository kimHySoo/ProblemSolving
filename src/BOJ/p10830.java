//gpt평: 홀수 처리 방식이 아주 정석적이진 않음, long 처리 방식이 아쉬움
package BOJ;
import java.util.*;
import java.io.*;
public class p10830 {
	static int n;
	static int[][] x;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		x = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) x[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] ans = solve(m);
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) sb.append(ans[i][j]%1000).append(' ');
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	static int[][] solve(long m){
		if(m==1) return x;
		else if((m&1)==1) {
			int[][] y = solve(m-1);
			return cal(y, x);
		}
		else {
			int[][] y = solve(m/2);
			return cal(y, y);
		}
	}
	static int[][] cal(int[][] x, int[][] y){
		int[][] z = new int[n][n];
		for(int i=0;i<n;i++) for(int j=0;j<n;j++) {
			for(int k=0;k<n;k++) z[i][j]=(z[i][j]+x[i][k]*y[k][j])%1000;
		}
		return z;
	}
}
/*
import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] base;
    static final int MOD = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        base = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                base[i][j] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }

        int[][] ans = pow(base, b);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(ans[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static int[][] pow(int[][] matrix, long exp) {
        if (exp == 1) return matrix;

        int[][] half = pow(matrix, exp / 2);
        int[][] result = multiply(half, half);

        if ((exp & 1) == 1) {
            result = multiply(result, base);
        }

        return result;
    }

    static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] = (int)((c[i][j] + (long)a[i][k] * b[k][j]) % MOD);
                }
            }
        }

        return c;
    }
}
*/