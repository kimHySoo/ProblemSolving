//에라토스테네스의 체 -> boolean으로
// int -> String : String.valueOf(i)
package BOJ;

import java.io.*;
import java.util.*;

public class p6219 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String d = st.nextToken();
		int[] decimal = new int[m+1];
		for(int i=2;i*i<m+1;i++) {
			for(int j=i;j*i<m+1;j++) if(decimal[i*j]==0) decimal[i*j]=1;
		}
		int cnt = 0;
		for(int i=n;i<m+1;i++) {
			if(decimal[i]!=0) continue;
			String x = i+"";
			if(x.contains(d)) cnt++;
		}
		System.out.println(cnt);
	}
}

/*
import java.io.*;
import java.util.*;

public class Main {
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

*/