//기약 분수는 최대공약수 찾아서 계산
package BOJ;
import java.io.*;
import java.util.*;
public class p30997 {
    static double max = 0;
    static int[] visited, choice;
    static long[] ans;
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        choice = new int[n+1];
        visited = new int[3];
        ans = new long[5];
        for(int i=0;i<m-1;i++) {   
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++) choice[Integer.parseInt(st.nextToken())]++;
        }
        dfs(0, 0);
        System.out.printf("%d %d\n%d %d %d", ans[0], ans[1], ans[2], ans[3], ans[4]);
    }

    static void dfs(int v, int depth) {
        if(depth==3) {
            winning();
            return;
        }
        for(int i=v+1;i<n+1;i++) {
            visited[depth]=i;
            dfs(i, depth+1);
        }
    }

    static void winning() {
        int[] c = choice.clone();
        int p = 0;
        for(int i:visited) {
            c[i]++;
            p+=c[i];
        }
        long q = 3L*m;
        long a = 1;                  
        long b = 1;           
        for(int i=0;i<k;i++) {  
            a *= (q-p);
            b *= q;
        }
        a *= p;                   
        b *= q;
        double x = (double)a/b;
        if(x>max) {
            max = x;
            for(int i=2;i<5;i++) ans[i]=visited[i-2];
            long y = gcd(a,b);
            ans[0]=a/y;
            ans[1]=b/y;
        }
    }

    static long gcd(long a, long b) {
        while(b!=0) {
            long tmp = a%b;
            a=b;
            b=tmp;
        }
        return a;
    }
}