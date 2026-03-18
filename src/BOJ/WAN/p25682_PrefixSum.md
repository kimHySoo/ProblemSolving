# 🧠 Problem Solving (PS)

알고리즘 문제 풀이와 학습 내용을 정리하는 저장소입니다.

목표
- 알고리즘 문제 해결 능력 향상
- 틀린 이유 및 핵심 개념 기록
- 코드 및 풀이 과정 정리

---

# 📝 Mistakes & Learnings

틀렸거나 새로 배운 내용을 기록합니다.

---

# BOJ 1018 체스판 다시 칠하기

문제  
https://www.acmicpc.net/problem/1018

사용 알고리즘  
2D Prefix Sum

---

# 처음 풀이

행 누적합을 이용해서 체스판의 부분 영역을 계산하려고 했다.

```java
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] va = new int[n+1][m+1];

		for(int i=1;i<1+n;i++) {
			String line = br.readLine();
			int a = 0;

			for(int j=1;j<m+1;j++) {
				char c= line.charAt(j-1);

				if(((i+j)&1)==1) { 
					if(c=='W') a++;
				}
				else {
					if(c=='B') a++;
				}

				va[i][j]=va[i-1][j]+a;
			}
		}

		int ans = Integer.MAX_VALUE;

		for(int i=0;i<n-k+1;i++) {
			for(int j=0;j<m-k+1;j++) {
				int sa = va[k+i][k+j]-va[i][j];

				sa=Math.min(sa, k*k-sa);
				ans=Math.min(sa, ans);
			}
		}

		System.out.println(ans);
	}
}

# 2D Prefix Sum (2차원 누적합)

직사각형 영역의 합을 빠르게 계산하기 위한 알고리즘.

------------------------------------------------------------------------

## 기본 아이디어

`(1,1)`부터 `(i,j)`까지의 값을 미리 더해 저장한다.

ps\[i\]\[j\] = (1,1) \~ (i,j) 영역의 합

이 값을 이용하면 직사각형 합을 O(1)에 계산할 수 있다.

------------------------------------------------------------------------

## 누적합 공식

ps\[i\]\[j\] = ps\[i-1\]\[j\] + ps\[i\]\[j-1\] - ps\[i-1\]\[j-1\] +
value

### 의미

-   위쪽 영역
-   왼쪽 영역
-   겹치는 영역 제거
-   현재 값 추가

------------------------------------------------------------------------

## 직사각형 합 공식

직사각형 `(x1, y1)` \~ `(x2, y2)` 의 합

sum = ps\[x2\]\[y2\] - ps\[x1-1\]\[y2\] - ps\[x2\]\[y1-1\] +
ps\[x1-1\]\[y1-1\]

### 직관적 설명

전체 영역에서

-   위쪽 영역 제거
-   왼쪽 영역 제거
-   두 번 제거된 영역 복구

------------------------------------------------------------------------

## 시간복잡도

누적합 생성

O(nm)

직사각형 계산

O(1)

전체

O(nm)

------------------------------------------------------------------------
