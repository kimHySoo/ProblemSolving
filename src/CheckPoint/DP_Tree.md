# 트리 DP (Tree Dynamic Programming)

## 개념

트리 DP는 **트리 구조 위에서 동적 프로그래밍**을 적용하는 기법입니다.  
트리의 루트부터 DFS를 수행하면서, **자식 노드의 결과를 이용해 부모 노드의 결과를 계산**합니다.

> 핵심 원리: 트리는 루트를 기준으로 **겹치지 않는 서브트리들**로 분리되므로, 각 서브트리를 독립적으로 계산할 수 있습니다.

---

## 일반 DP vs 트리 DP

| 구분 | 일반 DP | 트리 DP |
|------|---------|---------|
| 구조 | 1차원/2차원 배열 | 트리 (비선형) |
| 점화식 방향 | 왼쪽 → 오른쪽 | 자식 → 부모 (후위 순회) |
| 탐색 방법 | 반복문 | DFS |
| 순서 보장 | 인덱스로 자동 보장 | DFS 완료 순서로 보장 |

---

## 기본 구조

```java
static void dfs(int v, int parent) {
    // 1. 현재 노드 초기화
    dp[v] = 초기값;

    // 2. 자식 노드 재귀 처리
    for (int child : tree[v]) {
        if (child == parent) continue; // 부모 방향으로 올라가지 않도록
        dfs(child, v);

        // 3. 자식의 결과로 현재 노드 갱신
        dp[v] = f(dp[v], dp[child]);
    }
}
```

### 왜 `if (child == parent) continue` 가 필요한가?

트리를 **양방향 간선**으로 저장하기 때문에, 자식에서 부모 방향으로 다시 올라가는 것을 막아야 합니다.  
`visited[]` 배열 대신 부모 노드 번호를 인자로 넘기는 방식이 일반적입니다.

---

## 상태 설계

트리 DP의 핵심은 **dp 배열의 상태를 어떻게 정의하느냐**입니다.

### 단일 상태

```
dp[v] = 노드 v를 루트로 하는 서브트리에서의 최적값
```

예시: 서브트리의 노드 개수, 서브트리의 합

### 다중 상태 (선택/비선택)

```
dp[v][0] = 노드 v를 선택하지 않았을 때의 최적값
dp[v][1] = 노드 v를 선택했을 때의 최적값
```

예시: 독립 집합 문제 (백준 1949, 2533 등)

---

## 대표 예제: 독립 집합 최대 가중치 (백준 1949)

### 문제

트리에서 **인접한 두 노드를 동시에 선택할 수 없을 때**, 선택한 노드의 가중치 합 최대화

### 상태 정의

```
dp[v][0] = v를 선택하지 않았을 때, v의 서브트리에서 가중치 합 최대
dp[v][1] = v를 선택했을 때, v의 서브트리에서 가중치 합 최대
```

### 점화식

```
dp[v][0] += max(dp[child][0], dp[child][1])   // v 미선택 → 자식 자유롭게 선택
dp[v][1] += dp[child][0]                       // v 선택 → 자식은 반드시 미선택
```

### 코드

```java
import java.util.*;
import java.io.*;

public class Main {
    static List<Integer>[] tree;
    static int[] population;
    static int[][] dp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        population = new int[n + 1];
        dp = new int[n + 1][2];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            tree[x].add(y);
            tree[y].add(x);
        }

        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int v, int parent) {
        dp[v][0] = 0;
        dp[v][1] = population[v]; // v 선택 시 자신의 가중치 포함

        for (int child : tree[v]) {
            if (child == parent) continue;
            dfs(child, v);

            dp[v][0] += Math.max(dp[child][0], dp[child][1]);
            dp[v][1] += dp[child][0];
        }
    }
}
```

### 예시 트리 시각화

```
        1 (w=3)
       / \
    2(w=5) 3(w=2)
           |
         4(w=7)
```

| 선택 조합 | 유효 여부 | 합 |
|----------|-----------|-----|
| {1, 3} | ❌ (인접) | - |
| {2, 4} | ✅ | 12 |
| {1, 4} | ✅ | 10 |
| {2, 3} | ✅ | 7 |

→ 정답: **12** ({2, 4} 선택)

---

## 트리 DP 문제 유형 정리

| 유형 | 대표 문제 | dp 상태 |
|------|-----------|---------|
| 독립 집합 | 백준 1949, 2533 | dp[v][선택여부] |
| 트리의 지름 | 백준 1167, 1759 | dp[v] = 서브트리 최대 깊이 |
| 서브트리 크기 | 백준 1068 | dp[v] = 리프 노드 수 |
| 루트 재지정 | 백준 1967 | dp[v][0/1] (내려가기/올라가기) |

---

## 주의사항

1. **루트를 임의로 지정해도 됩니다** — 트리는 사이클이 없으므로 어느 노드를 루트로 해도 구조가 동일합니다.
2. **스택 오버플로 주의** — n이 클 경우(10만 이상) 재귀 대신 반복적 DFS를 사용하거나 스택 크기를 늘려야 합니다.
3. **플로이드-워셜은 트리 DP 문제에 부적합** — O(n³) 시간, O(n²) 공간으로 n이 조금만 커져도 한계에 부딪힙니다.

---

## 시간/공간 복잡도

| 항목 | 복잡도 |
|------|--------|
| 시간 | O(n) — 각 노드를 한 번씩 방문 |
| 공간 | O(n) — dp 배열 + 재귀 스택 |