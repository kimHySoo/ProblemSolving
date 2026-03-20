//에라스토텔레스의 체
package Algorithm;

import java.io.*;
import java.util.*;

public class isComposite {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String d = st.nextToken();

        boolean[] isComposite = new boolean[m + 1];

        if (m >= 0) isComposite[0] = true;
        if (m >= 1) isComposite[1] = true;

        for (int i = 2; i * i <= m; i++) {
            if (isComposite[i]) continue;
            for (int j = i * i; j <= m; j += i) {
                isComposite[j] = true;
            }
        }

        int count = 0;
        for (int i = Math.max(2, n); i <= m; i++) {
            if (!isComposite[i] && String.valueOf(i).contains(d)) {
                count++;
            }
        }

        System.out.println(count);
    }
}