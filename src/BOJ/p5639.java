//gpt평: 최악의 경우 시간복잡도는 O(n^2)
package BOJ;
import java.util.*;
import java.io.*;
public class p5639 {
	static StringBuilder sb = new StringBuilder();
	static List<Integer> lst = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = br.readLine()) != null) {
		    if (line.isEmpty()) break;
		    int x = Integer.parseInt(line);
		    lst.add(x);
		}
		solve(0, lst.size()-1);
		System.out.println(sb.toString());
	}
	static void solve(int p, int q) {
		if(p==q) {
			sb.append(lst.get(p)).append('\n');
			return;
		}
		else if(p>q) return;
		int pivot = lst.get(p);
		int idx = p+1;
		while(idx<=q&&pivot>lst.get(idx)) idx++;
		solve(p+1, idx-1);
		solve(idx, q);
		sb.append(pivot).append('\n');
	}
}
/*
import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> pre = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static int idx = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) break;
            pre.add(Integer.parseInt(line));
        }

        postOrder(Long.MIN_VALUE, Long.MAX_VALUE);
        System.out.print(sb);
    }

    static void postOrder(long min, long max) {
        if (idx >= pre.size()) return;

        int cur = pre.get(idx);

        // 현재 값이 이 서브트리 범위에 속하지 않으면 종료
        if (cur <= min || cur >= max) return;

        idx++; // 현재 값을 사용

        // 왼쪽 서브트리: (min, cur)
        postOrder(min, cur);

        // 오른쪽 서브트리: (cur, max)
        postOrder(cur, max);

        // 후위순회이므로 마지막에 출력
        sb.append(cur).append('\n');
    }
}
*/