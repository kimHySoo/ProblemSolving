//https://www.acmicpc.net/problem/1013
package Algorithm;
import java.io.*;

/* 이메일 정규식
s.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
 */

public class RegularExpression {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String s = br.readLine();
            if (s.matches("(100+1+|01)+")) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.print(sb);
    }
}

/*
3
10010111
011000100110001
0110001011001
*/