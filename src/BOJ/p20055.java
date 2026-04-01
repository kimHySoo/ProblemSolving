package BOJ;
import java.util.*;
import java.io.*;

public class p20055 {
	static int n;
	static int[][] cb;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int cnt = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        cb = new int[2][n];
        for(int i=0;i<n;i++) cb[0][i]=Integer.parseInt(st.nextToken());
        for(int i=n-1;i>=0;i--) cb[1][i]=Integer.parseInt(st.nextToken());
        int k = 0;
        boolean[] robot = new boolean[n];
        int time = 1;
        while(true) {
        	cycle();
        	for(int i=n-2;i>=0;i--) {
        		if(!robot[i]) continue;
        		if(i+2<n&&!robot[i+2]&&cb[0][i+2]>0) {
        			if(--cb[0][i+2]==0) k++;
        			robot[i]=false;
        			if(i+2!=n-1) robot[i+2]=true;
        		}
        		else{
        			if(i+1!=n-1) robot[i+1]=true;
        			robot[i]=false;
        		}
        	}
        	if(cb[0][0]>0) {
        		if(--cb[0][0]==0)k++;
        		robot[0]=true;
        	}
        	if(k>=cnt) break;
        	time++;
        }
        System.out.println(time);
   	}
    static void cycle() {
    	int tmp = cb[0][0];
    	int r = 0;
    	int c = 0;
    	int d = 0;
    	while(true) {
    		int nr = dr[d]+r;
    		int nc = dc[d]+c;
    		if(nr<0||nc<0||nr>=2||nc>=n) {
    			d++;
    			d%=4;
    			continue;
    		}
    		cb[r][c]=cb[nr][nc];
    		r=nr;
    		c=nc;
    		if(nr==0&&nc==0) break;
    	}
    	cb[0][1]=tmp;
    }
}