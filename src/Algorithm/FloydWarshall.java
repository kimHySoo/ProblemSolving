//https://www.acmicpc.net/problem/21278

package Algorithm;
import java.util.*;
import java.io.*;
public class FloydWarshall {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n+1][n+1];
		int max = 100000;
		for(int i=1;i<n+1;i++) {
			Arrays.fill(map[i], max);
			map[i][i]=0;
		}
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y]=1;
			map[y][x]=1;
		}
		
		//이 부분이 플로이드 워셜
		for(int k=1;k<n+1;k++) for(int i=1;i<n+1;i++) for(int j=1;j<n+1;j++) {
			map[i][j]=Math.min(map[i][j], map[i][k]+map[k][j]);
		}
		//순서는 k, i, j순서로 for문 돌려야함
		
		int min = max;
		int x=0;
		int y=0;
		for(int i=1;i<n+1;i++) for(int j=1;j<n+1;j++) {
			if(i==j) continue;
			int sum = 0;
			for(int k=1;k<n+1;k++) {
				sum+=Math.min(map[i][k], map[k][j]);
			}
			if(sum<min) {
				x=i;
				y=j;
				min = sum;
			}
		}
		System.out.println(x+" "+y+" "+min*2);
	}
}

