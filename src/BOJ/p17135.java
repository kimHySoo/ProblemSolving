//**“중복 제거와 구조화가 덜 되어 있어서 유지보수성과 안정성이 떨어지는 코드”**야.
package BOJ;
import java.util.*;
import java.io.*;

public class p17135 {
	static int n, m, d, max;
	static int[] visited;
	static boolean[][] map;
	static List<int[]> mon;
	static List<int[]> move;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        visited = new int[3];
        mon = new ArrayList<>();
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<m;j++) {
        		int x = Integer.parseInt(st.nextToken());
        		if(x==1) mon.add(new int[] {i, j});
        	}
        }
        max = 0;
        dfs(0, 0, -1);
        System.out.println(max);
   	}
    static void dfs(int v, int k, int l) {
    	if(k==3) {
    		game();
    		return;
    	}
    	for(int i=l+1;i<m;i++) {
    		boolean ok = false;
    		for(int j=0;j<k;j++) {
    			if(visited[j]==i) {
    				ok = true;
    				break;
    			}
    		}
    		if(ok) continue;
    		visited[k]=i;
    		dfs(v+1, k+1, i);
    	}
    }
    static void game() {
    	int score = 0;
    	move = new ArrayList<>(mon);
    	int d1, d2, d3, x1, x2, x3, y1, y2, y3;
    	while(!move.isEmpty()) {
    		d1=d2=d3=d+1;
    		x1=x2=x3=y1=y2=y3=0;
    		map = new boolean[n][m];
    		for(int[] x:move) {
        		map[x[0]][x[1]]=true;
        		int sd1 = dist(x[0], x[1], n,visited[0]);
        		if(d1>sd1) {
        			d1 = sd1;
        			x1=x[0];
        			y1=x[1];
        		}
        		else if(sd1<=d&&sd1==d1){
        			if(y1>x[1]) {
        				x1=x[0];
        				y1=x[1];
        			}
        		}
        		int sd2 = dist(x[0], x[1], n,visited[1]);
        		if(d2>sd2) {
        			d2 = sd2;
        			x2=x[0];
        			y2=x[1];
        		}
        		else if(sd2<=d&&sd2==d2){
        			if(y2>x[1]) {
        				x2=x[0];
        				y2=x[1];
        			}
        		}
        		int sd3 = dist(x[0], x[1], n,visited[2]);
        		if(d3>sd3) {
        			d3 = sd3;
        			x3=x[0];
        			y3=x[1];
        		}
        		else if(sd3<=d&&sd3==d3){
        			if(y3>x[1]) {
        				x3=x[0];
        				y3=x[1];
        			}
        		}
        	}
    		int before = move.size();
    		if(d1<=d) map[x1][y1]=false;
    		if(d2<=d) map[x2][y2]=false;
    		if(d3<=d) map[x3][y3]=false;
    		move.clear();
    		for(int i=0;i<n;i++) for(int j=0;j<m;j++) {
    			if(map[i][j]) {
    				before--;
    				if(i+1<n) move.add(new int[] {i+1, j});
    			}
    		}
    		score+=before;
    	}
    	max = Math.max(score, max);
    }
    static int dist(int x, int y, int x1, int y1) {
    	return Math.abs(x-x1)+Math.abs(y-y1);
    }
}
/*
import java.util.*;
import java.io.*;

public class Main {
    static int n, m, d;
    static int maxKill;

    static int[] archers = new int[3];
    static List<int[]> enemies = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) enemies.add(new int[]{i, j});
            }
        }

        chooseArchers(0, 0);
        System.out.println(maxKill);
    }

    // 궁수 3명 위치 조합
    static void chooseArchers(int idx, int start) {
        if (idx == 3) {
            simulate();
            return;
        }

        for (int col = start; col < m; col++) {
            archers[idx] = col;
            chooseArchers(idx + 1, col + 1);
        }
    }

    static void simulate() {
        List<int[]> currentEnemies = new ArrayList<>();
        for (int[] enemy : enemies) {
            currentEnemies.add(new int[]{enemy[0], enemy[1]});
        }

        int totalKill = 0;

        while (!currentEnemies.isEmpty()) {
            boolean[] dead = new boolean[currentEnemies.size()];
            Set<Integer> targets = new HashSet<>();

            // 각 궁수가 공격할 적 선택
            for (int a = 0; a < 3; a++) {
                int bestIdx = -1;
                int bestDist = Integer.MAX_VALUE;
                int bestCol = Integer.MAX_VALUE;

                for (int i = 0; i < currentEnemies.size(); i++) {
                    int[] enemy = currentEnemies.get(i);
                    int dist = getDistance(enemy[0], enemy[1], n, archers[a]);

                    if (dist > d) continue;

                    if (dist < bestDist || (dist == bestDist && enemy[1] < bestCol)) {
                        bestDist = dist;
                        bestCol = enemy[1];
                        bestIdx = i;
                    }
                }

                if (bestIdx != -1) {
                    targets.add(bestIdx);
                }
            }

            // 공격 처리
            for (int idx : targets) {
                dead[idx] = true;
            }
            totalKill += targets.size();

            // 살아남은 적들 이동
            List<int[]> nextEnemies = new ArrayList<>();
            for (int i = 0; i < currentEnemies.size(); i++) {
                if (dead[i]) continue;

                int nr = currentEnemies.get(i)[0] + 1;
                int nc = currentEnemies.get(i)[1];

                // 성에 도달한 적은 제거
                if (nr < n) {
                    nextEnemies.add(new int[]{nr, nc});
                }
            }

            currentEnemies = nextEnemies;
        }

        maxKill = Math.max(maxKill, totalKill);
    }

    static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
*/