package BOJ;
import java.util.*;

public class p23435 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        long ans = 1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                ans *= (cnt[i] + 1);
            }
        }
        System.out.println(ans - 1);
    }
}

