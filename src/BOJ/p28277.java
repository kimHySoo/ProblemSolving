//크기가 큰건 내비두고 작은걸 이동시키기
package BOJ;
import java.io.*;
import java.util.*;

public class p28277 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        Set<Integer>[] set = new HashSet[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            set[i] = new HashSet<>();
            for (int j = 0; j < x; j++) {
                set[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (set[a].size() < set[b].size()) {
                    Set<Integer> temp = set[a];
                    set[a] = set[b];
                    set[b] = temp;
                }

                for (int x : set[b]) {
                    set[a].add(x);
                }
                set[b].clear();

            } else {
                int y = Integer.parseInt(st.nextToken());
                sb.append(set[y].size()).append('\n');
            }
        }

        System.out.print(sb);
    }
}