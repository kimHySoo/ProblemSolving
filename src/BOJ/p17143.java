//돌리는 시간 최소화가 핵심
//제약식 왜 틀렸는지 모르곘음
package BOJ;
import java.io.*;
import java.util.*;
public class p17143 {
	static int n, m;
	static class Shark implements Comparable<Shark>{
		int r, c, s, d, z;
		Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		public int compareTo(Shark o) {
			return Integer.compare(o.z, this.z);
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][m+1];
        List<Shark> shark = new ArrayList<>();
        List<Shark> move = new ArrayList<>();
        for(int i=0;i<k;i++) {
        	st = new StringTokenizer(br.readLine());
        	int r = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	int s = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	int z = Integer.parseInt(st.nextToken());
        	shark.add(new Shark(r, c, s, d, z));
        	map[r][c]=z;
        }
        int ans = 0;
        for(int idx = 1; idx<m+1;idx++) {
        	for(int i=1;i<n+1;i++) {
        		if(map[i][idx]==0) continue;
        		ans+=map[i][idx];
        		map[i][idx]=0;
        		break;
        	}
        	int x = -1;
        	for(int i=0;i<shark.size();i++) {
        		Shark s = shark.get(i);
        		if(map[s.r][s.c]==0) {
        			x=i;
        			break;
        		}
        	}
        	if(x!=-1) shark.remove(x);
        	Collections.sort(shark);
        	for(Shark t:shark) {
        		if(map[t.r][t.c]==t.z) map[t.r][t.c]=0;
        		int[] next = sharkLocation(t.r, t.c, t.s, t.d);
        		if(map[next[0]][next[1]]>t.z) continue;
        		move.add(new Shark(next[0], next[1], t.s, next[2], t.z));
        		map[next[0]][next[1]]=t.z;
        	}
        	shark = new ArrayList<>(move);
        	move.clear();
        	if(shark.isEmpty()) break;
        }
        System.out.println(ans);
    }
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};
    static int[] sharkLocation(int r, int c, int s, int d) {
    	int[] shark = new int[3];
    	if(d<=2) s%=(n-1)*2;
    	else s%=(m-1)*2;
    	for(int i=0;i<s;i++) {
    		r+=dr[d];
    		c+=dc[d];
    		if(r==0||c==0) {
    			if(r==0) {
    				r=2;
    				d++;
    			}
    			else {
    				c=2;
    				d--;
    			}
    		}
    		else if(r==n+1||c==m+1) {
    			if(r==n+1) {
    				r=n-1;
    				d--;
    			}
    			else {
    				c=m-1;
    				d++;
    			}
    		}
    	}
    	shark[0]=r;
    	shark[1]=c;
    	shark[2]=d;
    	return shark;
    }
}