package BOJ;

import java.io.*;
import java.util.*;
public class p32685 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		for(int i=0;i<3;i++) {
			int n = Integer.parseInt(br.readLine());
			StringBuilder s = new StringBuilder();
			for(int j=0;j<4;j++) {
				s.append(n%2);
				n/=2;
			}
			sb.append(s.reverse());
		}
		int x = Integer.parseInt(sb.toString(), 2);
		System.out.printf("%04d", x);
	}	
}

/*

가장 좋은 풀이

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int ans = 0;
        for (int i = 0; i < 3; i++) {
            int n = Integer.parseInt(br.readLine());
            ans = (ans << 4) | n;
        }

        System.out.printf("%04d%n", ans);
    }
}


*/