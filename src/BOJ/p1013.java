package BOJ;
//정규표현식 사용문제
/* 이거 한줄이면 됐던거였음
          if (s.matches("(100+1+|01)+")) sb.append("YES\n");
*/

import java.io.*;
import java.util.*;
public class p1013 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test-->0) {
			String line = br.readLine();
			int n = line.length();
			boolean ok = true;
			int i = 0;
			while(i<n&&ok) {
				if(i+1<n&&line.charAt(i)=='0'&&line.charAt(i+1)=='1') i+=2;
				else if(i+2<n&&line.charAt(i)=='1'&&line.charAt(i+1)=='0'&&line.charAt(i+2)=='0') {
					i+=3;
					while(i<n&&line.charAt(i)=='0') i++;
					if(i>=n||line.charAt(i)!='1') {
						ok=false;
						break;
					}
					while(i<n&&line.charAt(i)=='1') {
						i++;
						if(i+2<n&&line.charAt(i+1)=='0'&&line.charAt(i+2)=='0') break;
					}
				}
				else ok=false;
			}
			sb.append(ok?"YES\n":"NO\n");
		}
		System.out.print(sb.toString());
	}
}
