# 📘 SPFA (Shortest Path Faster Algorithm) 정리

## 🔥 한 줄 핵심

> **SPFA = 벨만포드를 “필요한 정점만” 처리하도록 최적화한 알고리즘**

---

## 📌 기본 개념

SPFA는 벨만포드 알고리즘을 개선한 방식으로,
**거리 갱신이 발생한 정점만 큐에 넣어서 탐색**한다.

* 벨만포드: 모든 간선을 n-1번 반복
* SPFA: 갱신된 정점만 큐에 넣어 처리

👉 불필요한 연산을 줄여 평균적으로 더 빠름

---

## ⚙️ 동작 과정

1. 시작 정점을 큐에 넣는다
2. 큐에서 정점을 하나 꺼낸다
3. 해당 정점의 모든 간선을 relax 한다
4. 값이 갱신되면 해당 정점을 큐에 넣는다
5. 큐가 빌 때까지 반복

---

## 💻 기본 코드 (Java)

```java
import java.util.*;

class Edge {
    int to, cost;
    Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

public class SPFA {
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) {
        int n = 5;
        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        // 예시 그래프
        graph[1].add(new Edge(2, 2));
        graph[1].add(new Edge(3, 4));
        graph[2].add(new Edge(3, -1));
        graph[3].add(new Edge(4, 2));
        graph[4].add(new Edge(5, 3));

        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);

        boolean[] inQueue = new boolean[n + 1];
        int[] count = new int[n + 1];

        Queue<Integer> q = new ArrayDeque<>();

        int start = 1;
        dist[start] = 0;
        q.add(start);
        inQueue[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            inQueue[cur] = false;

            for (Edge e : graph[cur]) {
                if (dist[cur] != INF && dist[e.to] > dist[cur] + e.cost) {
                    dist[e.to] = dist[cur] + e.cost;

                    if (!inQueue[e.to]) {
                        q.add(e.to);
                        inQueue[e.to] = true;
                        count[e.to]++;

                        // 음수 사이클 체크
                        if (count[e.to] >= n) {
                            System.out.println("Negative Cycle");
                            return;
                        }
                    }
                }
            }
        }

        System.out.println(Arrays.toString(dist));
    }
}
```

---

## 🔑 핵심 포인트

### 1️⃣ inQueue 배열

* 같은 정점이 큐에 여러 번 들어가는 것을 방지
* 성능에 매우 중요

---

### 2️⃣ “갱신될 때만” 큐에 넣는다

* SPFA 성능의 핵심
* 불필요한 탐색 제거

---

### 3️⃣ 음수 사이클 판별

* 특정 정점이 **n번 이상 큐에 들어가면**
* 👉 음수 사이클 존재

---

## ⚡ 시간복잡도

| 알고리즘    | 시간복잡도   |
| ------- | ------- |
| 벨만포드    | O(VE)   |
| SPFA 평균 | O(E) 수준 |
| SPFA 최악 | O(VE)   |

👉 최악에서는 벨만포드와 동일

---

## ⚠️ 주의사항 (중요)

### ❗ SPFA는 최악 케이스에서 매우 느리다

* 특정 입력에서 **시간 초과 유도 가능**
* BOJ 일부 문제에서 SPFA 저격 테스트 존재

👉 따라서 코테에서는 **무조건 사용 금지 아님, 하지만 신중히 사용**

---

## 🎯 사용 기준

### ✅ 사용 추천

* 간선이 sparse한 경우
* 랜덤 데이터
* 음수 간선 존재

---

### ❌ 사용 비추천

* 시간 제한 빡센 문제
* 최악 케이스 유도 가능 문제

---

## 📊 알고리즘 선택 기준

| 상황                | 추천 알고리즘    |
| ----------------- | ---------- |
| 음수 간선 없음          | 다익스트라      |
| 음수 간선 있음          | 벨만포드       |
| 최적화 필요            | SPFA (조건부) |
| 정점 수 적음 (n ≤ 400) | 플로이드 워셜    |

---

## 💡 실전 팁

### ✔ 거리 배열은 항상 long 사용

```java
long[] dist
```

---

### ✔ INF 체크 필수

```java
if (dist[cur] != INF)
```

---

### ✔ SPFA는 “옵션”이다

* 기본은 다익스트라 / 벨만포드
* SPFA는 상황 보고 선택

---

## 🧠 한 줄 정리

> **SPFA = 벨만포드의 큐 최적화 버전이지만, 최악 케이스 때문에 무조건 쓰면 위험한 알고리즘**
