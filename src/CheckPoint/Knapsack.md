# 0/1 배낭문제(01 Knapsack) 정리

## 1. 문제 개념

0/1 배낭문제는 **각 물건을 한 번만 넣을 수 있을 때**, 제한된 가방 무게 안에서 **가치의 합을 최대화**하는 문제다.

* 물건마다 `무게(weight)`와 `가치(value)`가 있다.
* 가방은 최대 `K` 무게까지만 담을 수 있다.
* 각 물건은 **넣거나(1), 안 넣거나(0)** 둘 중 하나만 가능하다.
* 같은 물건을 여러 번 넣을 수 없다.

그래서 이름이 **0/1 배낭문제**다.

---

## 2. 핵심 아이디어

각 물건에 대해 선택지는 두 가지다.

1. 이 물건을 넣지 않는다.
2. 이 물건을 넣는다. (단, 무게가 허용될 때만)

즉, 매 물건마다 **선택/비선택**을 비교하면서 최댓값을 쌓아간다.

---

## 3. DP로 푸는 이유

브루트포스로 풀면 물건마다 넣을지 말지 전부 따져야 하므로 경우의 수가 `2^N`이 된다.
물건 수가 많아지면 너무 느리다.

하지만 같은 상태가 반복된다.
예를 들어:

* 앞의 몇 개 물건까지 고려했는지
* 현재 남은(또는 사용 가능한) 무게가 얼마인지

이 상태가 같으면 답도 같다.
그래서 **동적 계획법(DP)** 를 사용한다.

---

## 4. 2차원 DP 정의

가장 정석적인 정의는 다음과 같다.

`dp[i][w] = i번째 물건까지 고려했을 때, 가방 허용 무게가 w일 때 얻을 수 있는 최대 가치`

### 점화식

현재 물건의 무게를 `weight[i]`, 가치를 `value[i]`라고 하면:

* 현재 물건을 넣을 수 없는 경우

  * `dp[i][w] = dp[i-1][w]`
* 넣을 수 있는 경우

  * `dp[i][w] = max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i])`

즉,

* **안 넣는 경우**: 이전 물건들만으로 만든 값
* **넣는 경우**: 현재 물건 무게만큼 뺀 상태에서 현재 가치 추가

둘 중 더 큰 값을 선택한다.

---

## 5. 2차원 DP 예시 코드 (Java)

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 물건 개수
        int k = Integer.parseInt(st.nextToken()); // 최대 무게

        int[] weight = new int[n + 1];
        int[] value = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= k; w++) {
                dp[i][w] = dp[i - 1][w]; // 안 넣는 경우
                if (w >= weight[i]) {
                    dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - weight[i]] + value[i]);
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
```

---

## 6. 왜 1차원 DP로 줄일 수 있나?

점화식을 보면 `dp[i][w]`는 오직 **이전 행 `dp[i-1][...]`** 만 필요하다.
즉, 모든 행을 저장할 필요 없이 한 줄만 가지고 갱신할 수 있다.

그래서 다음처럼 압축 가능하다.

`dp[w] = 현재까지 고려한 물건들로 무게 w에서 얻을 수 있는 최대 가치`

---

## 7. 1차원 DP 점화식

```java
dp[w] = Math.max(dp[w], dp[w - weight[i]] + value[i]);
```

그런데 여기서 아주 중요한 점이 있다.

## 8. 왜 무게를 뒤에서 앞으로 순회해야 하나?

0/1 배낭에서는 **각 물건을 한 번만 써야 한다.**

그래서 1차원 DP에서는:

```java
for (int i = 1; i <= n; i++) {
    for (int w = k; w >= weight[i]; w--) {
        dp[w] = Math.max(dp[w], dp[w - weight[i]] + value[i]);
    }
}
```

처럼 **무게를 큰 쪽에서 작은 쪽으로** 돌아야 한다.

### 이유

앞에서 뒤로 가면, 같은 물건으로 방금 갱신한 값을 다시 참조해서
**같은 물건을 여러 번 사용한 효과**가 생긴다.

그건 0/1 배낭이 아니라 **완전 배낭(unbounded knapsack)** 방식이 된다.

### 한 줄 요약

* **0/1 배낭**: 물건 중복 사용 금지 → `w`를 **감소 방향**으로 순회
* **완전 배낭**: 물건 중복 사용 가능 → `w`를 **증가 방향**으로 순회

---

## 9. 1차원 DP 예시 코드 (Java)

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] weight = new int[n + 1];
        int[] value = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[k + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = k; w >= weight[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weight[i]] + value[i]);
            }
        }

        System.out.println(dp[k]);
    }
}
```

---

## 10. 직접 작은 예시로 이해하기

물건이 2개 있다고 하자.

* 1번 물건: 무게 3, 가치 6
* 2번 물건: 무게 4, 가치 8
* 가방 최대 무게: 7

가능한 선택:

* 아무것도 안 담음: 가치 0
* 1번만 담음: 가치 6
* 2번만 담음: 가치 8
* 둘 다 담음: 무게 7, 가치 14

따라서 정답은 14다.

DP는 이런 최적 선택을 작은 상태부터 쌓아가며 구한다.

---

## 11. 시간복잡도와 공간복잡도

### 2차원 DP

* 시간복잡도: `O(NK)`
* 공간복잡도: `O(NK)`

### 1차원 DP

* 시간복잡도: `O(NK)`
* 공간복잡도: `O(K)`

보통은 1차원 DP를 많이 사용하지만,
처음 공부할 때는 **2차원으로 점화식을 먼저 이해하는 것**이 더 중요하다.

---

## 12. 자주 헷갈리는 점

### 1) greedy로는 안 되는가?

항상 안 되는 것은 아니지만, 일반적인 0/1 배낭은 그리디로 최적해를 보장할 수 없다.

예를 들어 가치/무게 비율이 큰 것부터 고르는 방식이 항상 정답을 만들지는 않는다.
그래서 DP를 사용한다.

### 2) 부분합 문제와 비슷한가?

비슷하다.
특히 `무게를 만들 수 있는가?`, `최대 가치가 얼마인가?` 같은 식으로 DP 상태를 잡는다는 점에서 닮아 있다.
하지만 0/1 배낭은 **가치 최대화**가 핵심이다.

### 3) 0/1 배낭과 완전 배낭 차이

* **0/1 배낭**: 각 물건을 최대 1번 사용
* **완전 배낭**: 같은 물건 여러 번 사용 가능

이 차이가 반복문의 방향까지 바꾼다.

---

## 13. 문제를 보면 0/1 배낭인지 판단하는 법

다음 표현이 보이면 0/1 배낭일 가능성이 크다.

* 각 물건은 한 번만 선택 가능
* 각 원소를 사용하거나 사용하지 않는다
* 물건 수가 정해져 있고 중복 선택이 안 된다
* 제한된 용량/시간/비용 안에서 최대 이익을 구한다

대표적으로:

* 물건 담기
* 시간 제한 내 최대 점수
* 예산 제한 내 최대 만족도

이런 문제들이 0/1 배낭 형태로 바뀌는 경우가 많다.

---

## 14. 기억할 핵심 문장

### 꼭 외울 것

1. `dp[i][w] = i번째 물건까지 고려했을 때, 무게 w에서의 최대 가치`
2. 점화식은 **넣는다 / 안 넣는다** 비교다.
3. 1차원 압축 시 **0/1 배낭은 뒤에서 앞으로 순회**한다.
4. 앞에서부터 순회하면 같은 물건을 여러 번 쓰게 되어 완전 배낭이 된다.

---

## 15. 한 줄 정리

**0/1 배낭문제는 각 물건을 한 번만 사용할 수 있을 때, 제한된 무게 안에서 최대 가치를 구하는 DP 문제이며, 1차원 DP로 풀 때는 같은 물건의 중복 사용을 막기 위해 무게를 역순으로 순회해야 한다.**
