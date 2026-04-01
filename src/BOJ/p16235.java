//“정답 지향 구현은 괜찮은데, 자료구조 선택 때문에 비효율이 크다”
//**각 칸마다 나무 나이를 deque로 관리하면서, 어린 나무 순서를 유지하는 풀이**야.
package BOJ;
import java.util.*;
import java.io.*;

public class p16235 {
	static int[] dr = {1, 0, -1, 0, 1, -1, -1, 1};
	static int[] dc = {0, 1, 0, -1, 1, 1, -1, -1};
	static class Tree implements Comparable<Tree>{
		int x, y, z;
		public Tree(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.z, o.z);
		}
	}
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] val = new int[n+1][n+1];
        for(int i=1;i<1+n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=1;j<1+n;j++) val[i][j]=Integer.parseInt(st.nextToken());
        }
        List<Tree> ltree = new ArrayList<>();
        List<Tree> dtree = new ArrayList<>();
        List<Tree> grow = new ArrayList<>();
        for(int i=0;i<m;i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	int z = Integer.parseInt(st.nextToken());
        	ltree.add(new Tree(x, y, z));
        }
        int[][] map = new int[n+1][n+1];
        for(int i=1;i<n+1;i++) Arrays.fill(map[i], 5);
        while(k-->0) {
        	Collections.sort(ltree);
        	grow.clear();
        	for(Tree t:ltree) {
        		if(map[t.x][t.y]>=t.z) {
        			map[t.x][t.y]-=t.z;
        			grow.add(new Tree(t.x, t.y, t.z+1));
        		}
        		else dtree.add(new Tree(t.x, t.y, t.z));
        	}
        	ltree.clear();
        	
        	for(Tree t:dtree) map[t.x][t.y]+=t.z/2;
        	dtree.clear();
        	
        	for(Tree t:grow) {
        		ltree.add(t);
        		if(t.z%5!=0) continue;
        		for(int i=0;i<8;i++) {
        			int nr = t.x+dr[i];
        			int nc = t.y+dc[i];
        			if(nr<1||nc<1||nr>n||nc>n) continue;
        			ltree.add(new Tree(nr, nc, 1));
        		}
        	}
        	for(int i=1;i<n+1;i++) for(int j=1;j<n+1;j++) map[i][j]+=val[i][j];
        }
        System.out.println(ltree.size());
    }
}
/*
import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static int[][] food;      // 현재 양분
    static int[][] add;       // 겨울에 추가할 양분
    static ArrayDeque<Integer>[][] trees;

    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        food = new int[n][n];
        add = new int[n][n];
        trees = new ArrayDeque[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(food[i], 5);
            for (int j = 0; j < n; j++) {
                trees[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 나무 입력
        // 같은 칸에 여러 나무가 있을 수 있으므로 일단 리스트에 모아서 정렬 후 넣음
        List<Integer>[][] init = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                init[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            init[x][y].add(age);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Collections.sort(init[i][j]);
                for (int age : init[i][j]) {
                    trees[i][j].addLast(age);
                }
            }
        }

        while (k-- > 0) {
            springAndSummer();
            autumn();
            winter();
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer += trees[i][j].size();
            }
        }

        System.out.println(answer);
    }

    static void springAndSummer() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (trees[r][c].isEmpty()) continue;

                ArrayDeque<Integer> next = new ArrayDeque<>();
                int deadFood = 0;

                while (!trees[r][c].isEmpty()) {
                    int age = trees[r][c].pollFirst();

                    if (food[r][c] >= age) {
                        food[r][c] -= age;
                        next.addLast(age + 1);
                    } else {
                        deadFood += age / 2;
                    }
                }

                food[r][c] += deadFood;
                trees[r][c] = next;
            }
        }
    }

    static void autumn() {
        List<int[]> breed = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (trees[r][c].isEmpty()) continue;

                for (int age : trees[r][c]) {
                    if (age % 5 == 0) {
                        breed.add(new int[]{r, c});
                    }
                }
            }
        }

        for (int[] pos : breed) {
            int r = pos[0];
            int c = pos[1];

            for (int d = 0; d < 8; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                trees[nr][nc].addFirst(1);
            }
        }
    }

    static void winter() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                food[r][c] += add[r][c];
            }
        }
    }
}
*/