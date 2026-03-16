//gpt평가: BFS가 더 나았을 것
/*
플로이드 워셜이 더 좋은 경우

모든 정점 쌍 최단거리가 필요할 때

N이 작을 때

구현을 단순하게 하고 싶을 때
*/
/*
BFS가 더 좋은 경우

간선 가중치가 전부 동일할 때

그래프가 희소할 때

문제 성질에 맞는 알고리즘을 쓰고 싶을 때
 */
package BOJ;
import java.util.*;
import java.io.*;
public class p21278 {
	
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
		for(int k=1;k<n+1;k++) for(int i=1;i<n+1;i++) for(int j=1;j<n+1;j++) {
			map[i][j]=Math.min(map[i][j], map[i][k]+map[k][j]);
		}
		int min = max;
		int x=0;
		int y=0;
		for(int i=1;i<n+1;i++) for(int j=1;j<n+1;j++) {
			//i와 j가 같은 경우는 스킵했어야함
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

