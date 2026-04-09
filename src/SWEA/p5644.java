package SWEA;

import java.io.*;
import java.util.*;
public class p5644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(br.readLine());
        int[][] player, map;
        int[] dr = {0, -1, 0, 1, 0};
        int[] dc = {0, 0, 1, 0, -1};
        List<int[]>[][] power;
        Queue<int[]> q = new ArrayDeque<>();
        for(int t=1;t<test+1;t++) {
        	power = new ArrayList[11][11];
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken());
        	int m = Integer.parseInt(st.nextToken());
        	player = new int[n+1][2];
        	for(int p=0;p<2;p++) {
            	st = new StringTokenizer(br.readLine());
            	for(int i=1;i<n+1;i++) player[i][p]=Integer.parseInt(st.nextToken());
        	}
        	for(int i=1;i<11;i++) for(int j=1;j<11;j++) power[i][j] = new ArrayList<>();
        	for(int i=0;i<m;i++) {
        		st = new StringTokenizer(br.readLine());
        		int c = Integer.parseInt(st.nextToken());
        		int r = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		int p = Integer.parseInt(st.nextToken());
        		q.add(new int[] {r, c, b});
            	map = new int[11][11];
            	map[r][c]=p;
            	power[r][c].add(new int[] {i, p});
        		while(!q.isEmpty()) {
        			int[] x = q.poll();
        			r = x[0];
        			c = x[1];
        			b = x[2]-1;
        			if(b==-1) continue;
        			for(int d=1;d<5;d++) {
        				int nr = r+dr[d];
        				int nc = c+dc[d];
        				if(nr<1||nc<1||nr>10||nc>10) continue;
        				if(map[nr][nc]==p) continue;
        				map[nr][nc]=p;
        				power[nr][nc].add(new int[] {i, p});
        				q.add(new int[] {nr, nc, b});
        			}
        		}
        	}
        	int r1, r2, c1, c2;
        	r1 = c1 = 1;
        	r2 = c2 = 10;
        	int charge = 0;
        	for(int i=0;i<n+1;i++) {
        		int x = -1;
        		int nr = r1 +dr[player[i][0]];
        		int nc = c1 +dc[player[i][0]];
        		if(power[nr][nc].size()>1) Collections.sort(power[nr][nc], (a,b)->Integer.compare(b[1], a[1]));
        		if(!power[nr][nc].isEmpty()) {
        			x = power[nr][nc].get(0)[0];
        			charge+=power[nr][nc].get(0)[1];
        		}
        		r1 = nr;
        		c1 = nc;
        		nr = r2+dr[player[i][1]];
        		nc = c2 +dc[player[i][1]];
        		if(power[nr][nc].size()>1) Collections.sort(power[nr][nc], (a,b)->Integer.compare(b[1], a[1]));
        		if(!power[nr][nc].isEmpty()) {
        			int y = -1;
        			y = power[nr][nc].get(0)[0];
        			if(x!=y) charge+=power[nr][nc].get(0)[1];
        			else {
        				if(power[r1][c1].size()>1&&power[nr][nc].size()>1) {
        					charge+=Math.max(power[r1][c1].get(1)[1], power[nr][nc].get(1)[1]);
        				}
        				else if(power[r1][c1].size()>1) charge+=power[r1][c1].get(1)[1];
        				else if(power[nr][nc].size()>1) charge+=power[nr][nc].get(1)[1];
        			}
        		}
        		r2 = nr;
        		c2 = nc;
        	}
        	sb.append('#').append(t).append(' ').append(charge).append('\n');
        }
        System.out.print(sb.toString());
    }
}