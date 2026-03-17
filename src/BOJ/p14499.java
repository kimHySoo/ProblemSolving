package BOJ;

//https://www.acmicpc.net/problem/14499
//gpt평가: 주사위 행렬 2개 만들지말고 6개짜리 한개가 더 관리가 좋다
//주사위 문제는 ‘암기’가 아니라 ‘면 이동 시뮬레이션 문제’다
import java.io.*;
import java.util.*;
public class p14499 {
	static int[] ds = {0, 0, 0, 0};
	static int[] df = {0, 0, 0, 0};
	static int[] dr = {0, 0, 0, -1, 1};
	static int[] dc = {0, 1, -1, 0, 0};
public static void main(String[] args) throws IOException{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	int n = Integer.parseInt(st.nextToken());
	int m = Integer.parseInt(st.nextToken());
	int r = Integer.parseInt(st.nextToken());
	int c = Integer.parseInt(st.nextToken());
	int t = Integer.parseInt(st.nextToken());
	int[][] map = new int[n][m];
	for(int i=0;i<n;i++) {
		st = new StringTokenizer(br.readLine());
		for(int j=0;j<m;j++) map[i][j]=Integer.parseInt(st.nextToken());
	}
	st = new StringTokenizer(br.readLine());
	for(int i=0;i<t;i++) {
		int d = Integer.parseInt(st.nextToken());
		int nr = r+dr[d];
		int nc = c+dc[d];
		if(nr>=n||nc>=m||nr<0||nc<0) continue;
		r=nr;
		c=nc;
		roll(d);
		System.out.println(ds[2]);
		if(map[r][c]==0) {
			map[r][c]=df[0];
		}
		else {
			ds[0]=map[r][c];
			df[0]=map[r][c];
			map[r][c]=0;
		}
	}
	}
	static void roll(int x) {
		if(x==1) {
			int tmp = ds[0];
			for(int i=0;i<3;i++) ds[i]=ds[i+1];
			ds[3]=tmp;
			df[0]=ds[0];
			df[2]=ds[2];
		}
		if(x==2) {
			int tmp = ds[3];
			for(int i=3;i>0;i--) ds[i]=ds[i-1];
			ds[0]=tmp;
			df[0]=ds[0];
			df[2]=ds[2];
		}
		if(x==3) {
			int tmp = df[3];
			for(int i=3;i>0;i--) df[i]=df[i-1];
			df[0]=tmp;
			ds[0]=df[0];
			ds[2]=df[2];
		}
		if(x==4) {
			int tmp = df[0];
			for(int i=0;i<3;i++) df[i]=df[i+1];
			df[3]=tmp;
			ds[0]=df[0];
			ds[2]=df[2];
		}
	}
}
