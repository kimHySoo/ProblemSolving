//맨해튼 거리에 대한 수학적 지식 필요
package BOJ;
import java.io.*;
import java.util.*;

public class p34825 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int xa = Integer.parseInt(st.nextToken());
        int ya = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int xb = Integer.parseInt(st.nextToken());
        int yb = Integer.parseInt(st.nextToken());

        int dx = xb - xa;
        int dy = yb - ya;

        int dist = Math.abs(dx) + Math.abs(dy);

        // 두 점까지의 거리가 같으려면 전체 거리의 절반 지점이 필요
        if (dist % 2 == 1) {
            System.out.println(-1);
            return;
        }

        int half = dist / 2;
        int x = xa;
        int y = ya;

        // x 방향으로 먼저 이동
        int moveX = Math.min(Math.abs(dx), half);
        if (dx > 0) x += moveX;
        else x -= moveX;
        half -= moveX;

        // 남은 만큼 y 방향 이동
        int moveY = Math.min(Math.abs(dy), half);
        if (dy > 0) y += moveY;
        else y -= moveY;

        System.out.println(x + " " + y);
    }
}