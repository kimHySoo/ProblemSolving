package BOJ;

import java.io.*;
import java.util.*;

public class p4604 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		int ans = 0;
		while(true) {
			String line = br.readLine();
			int n = line.length();
			if(line.charAt(0)=='#') break;
			if(line.charAt(0)=='*') {
			    if(idx > 0) {
			        ans <<= (5 - idx);
			        sb.append(decode(ans));
			    }
			    sb.append('\n');
			    idx = 0;
			    ans = 0;
			    continue;
			}
			for(int i=0;i<line.length();i++) {
				if(line.charAt(i)==' ') {
					int cnt=1;
					while(i+1<n&&line.charAt(i+1)==' ') {
					i++;
					cnt++;
					}
					idx++;
					int bit = (cnt % 2 == 0) ? 1 : 0;
					ans = (ans << 1) | bit;
					cnt = 0;
				}
				if(idx==5) {
					sb.append(decode(ans));
					ans = 0;
					idx = 0;
				}
			}
		}
		System.out.println(sb.toString());
	}
	static char decode(int x) {
		if(x==0) return ' ';
		else if(x>=1&&x<=26) return (char)('A'+(x-1));
		else if (x==27) return '\'';
		else if (x==28) return ',';
		else if (x==29) return '-';
		else if(x==30) return '.';
		else return '?';
	}
}

