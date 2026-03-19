package BOJ;
import java.io.*;
import java.util.*;

public class p6553 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String line = br.readLine();
			if(line==null||line.isEmpty()) break;
			StringTokenizer st = new StringTokenizer(line);
			int n = Integer.parseInt(st.nextToken());
			int x= Integer.parseInt(st.nextToken());
			int y= Integer.parseInt(st.nextToken());
			int x1 = x;
			int y1 = y;
			sb.append(n);
			for(int i=0;i<n-1;i++) {
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				sb.append(' ')
				  .append(String.format("%.6f", (x1 + x2) / 2.0))
				  .append(' ')
				  .append(String.format("%.6f", (y1 + y2) / 2.0));
				x1=x2;
				y1=y2;
			}
			sb.append(' ')
			  .append(String.format("%.6f", (x1 + x) / 2.0))
			  .append(' ')
			  .append(String.format("%.6f", (y1 + y) / 2.0))
			  .append('\n');
		}
		System.out.println(sb.toString());
	}
}