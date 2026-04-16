package BOJ;
import java.io.*;
import java.util.*;

public class p2166 {
    static int[][] poly;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        poly = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            poly[i][0] = Integer.parseInt(st.nextToken());
            poly[i][1] = Integer.parseInt(st.nextToken());
        }
        long area = 0;
        int j = n - 1;
        for (int i = 0; i < n; i++) {
            area += (long)(poly[j][0] + poly[i][0]) * (poly[j][1] - poly[i][1]);
            j = i;
        }
        System.out.printf("%.1f%n", Math.abs(area) / 2.0);
    }
}