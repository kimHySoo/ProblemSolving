# 다익스트라(Dijkstra) 정리

## 1. 다익스트라란?

다익스트라는 **음수 가중치가 없는 그래프**에서 한 시작점으로부터 다른 모든 정점까지의 **최단거리**를 구하는 알고리즘이다.

핵심 아이디어는 다음과 같다.

* 현재까지 알려진 최단거리가 가장 작은 정점을 하나 꺼낸다.
* 그 정점을 거쳐 가는 경로로 다른 정점들의 거리를 더 줄일 수 있는지 확인한다.
* 이 과정을 반복하면 시작점에서 각 정점까지의 최단거리를 구할 수 있다.

보통 **우선순위 큐(PriorityQueue)** 를 사용해서 구현한다.

---

## 2. 언제 쓰는가?

다익스트라는 보통 이런 상황에서 사용한다.

* 그래프의 간선 가중치가 모두 **0 이상**일 때
* 한 정점에서 다른 정점까지의 **최단거리**를 구하고 싶을 때
* 특정 도착점까지의 최단거리만 필요하거나, 전체 정점까지의 최단거리가 모두 필요할 때
* 최단거리뿐 아니라 **경로 복원**까지 필요할 때

---

## 3. 사용할 수 없는 경우

다음 경우에는 일반적인 다익스트라를 사용하면 안 된다.

### 3-1. 음수 간선이 있는 경우

다익스트라는 “현재 가장 짧은 거리의 정점을 먼저 확정해도 된다”는 성질을 이용한다.
그런데 음수 간선이 있으면 나중에 더 짧아질 수 있어서 이 성질이 깨진다.

이 경우에는 보통:

* 벨만-포드(Bellman-Ford)
* 플로이드-워셜(Floyd-Warshall)

같은 알고리즘을 고려해야 한다.

### 3-2. 모든 간선 가중치가 1인 경우

이 경우는 다익스트라도 가능하지만, 보통 **BFS가 더 정석적**이다.

예:

* 숨바꼭질
* 정점 이동이 모두 1초/1칸인 문제

### 3-3. 간선 가중치가 0 또는 1만 있는 경우

이 경우는 **0-1 BFS**가 더 효율적일 수 있다.

---

## 4. 시간복잡도

우선순위 큐를 사용한 다익스트라의 시간복잡도는 일반적으로 다음과 같이 본다.

* **O((V + E) log V)**
* 흔히 **O(E log V)** 로도 표현한다.

여기서

* `V` = 정점 수
* `E` = 간선 수

---

## 5. 핵심 개념

## 5-1. dist 배열

`dist[x]`는 시작점에서 `x`까지 가는 현재까지 알려진 최단거리이다.

처음에는:

* 시작점은 0
* 나머지는 무한대(INF)

로 둔다.

```java
Arrays.fill(dist, INF);
dist[start] = 0;
```

---

## 5-2. relax(완화)

어떤 정점 `cur`를 거쳐서 `next`로 가는 경로가 더 짧다면 갱신한다.

```java
if (dist[next] > dist[cur] + cost) {
    dist[next] = dist[cur] + cost;
}
```

이 갱신 과정을 보통 **완화(relaxation)** 라고 부른다.

---

## 5-3. 우선순위 큐에는 같은 정점이 여러 번 들어갈 수 있다

이게 다익스트라에서 매우 중요하다.

예를 들어 어떤 정점 5가:

* 거리 10으로 한 번 들어가고
* 나중에 더 좋은 경로를 찾아 거리 7로 또 들어갈 수 있다.

그래서 pq에서 꺼낼 때, 현재 정보가 최신인지 확인해야 한다.

```java
if (dist[cur.v] < cur.w) continue;
```

이 한 줄이 매우 중요하다.

---

## 6. 방문 처리에 대한 정확한 이해

이 부분을 많이 헷갈린다.

### BFS와 다익스트라의 차이

#### BFS

가중치가 모두 같을 때는 먼저 도착한 경로가 곧 최단거리이므로,
**큐에 넣을 때 방문처리**를 해도 된다.

```java
visited[next] = true;
q.add(next);
```

#### 다익스트라

다익스트라는 어떤 정점이 **나중에 더 짧은 거리로 다시 발견될 수 있다.**
그래서 큐에 넣을 때 단순 방문처리를 하면 안 된다.

즉,

* **넣기 전에 방문처리 X**
* **꺼냈을 때 최단거리 확정**

라고 이해하면 된다.

---

## 7. 다익스트라의 방문 처리 방식 2가지

## 7-1. 방문배열 없이 하는 방식

가장 많이 쓰는 방식이다.

```java
while (!pq.isEmpty()) {
    Node cur = pq.poll();

    if (dist[cur.v] < cur.w) continue;

    for (Node next : graph[cur.v]) {
        if (dist[next.v] > dist[cur.v] + next.w) {
            dist[next.v] = dist[cur.v] + next.w;
            pq.add(new Node(next.v, dist[next.v]));
        }
    }
}
```

### 장점

* 구현이 간단하다.
* 낡은 정보만 건너뛰면 된다.
* 실전 코테에서 자주 쓰인다.

---

## 7-2. 방문배열을 사용하는 방식

이 방식도 가능하다.

```java
while (!pq.isEmpty()) {
    Node cur = pq.poll();
    int v = cur.v;

    if (visited[v]) continue;
    visited[v] = true;

    for (Node next : graph[v]) {
        if (!visited[next.v] && dist[next.v] > dist[v] + next.w) {
            dist[next.v] = dist[v] + next.w;
            pq.add(new Node(next.v, dist[next.v]));
        }
    }
}
```

### 주의

방문배열을 쓰더라도 **pq에 넣기 전에 방문처리하면 안 된다.**
반드시 **poll 후 확정**이어야 한다.

---

## 8. 왜 enqueue 시 방문처리하면 안 되는가?

예를 보자.

* 1 → 2 : 비용 10
* 1 → 3 : 비용 1
* 3 → 2 : 비용 1

처음 1에서 출발하면:

* 2는 거리 10으로 pq에 들어감
* 3은 거리 1로 pq에 들어감

만약 2를 pq에 넣는 순간 방문처리하면,
나중에 1 → 3 → 2 경로의 거리 2를 반영할 수 없게 된다.

즉, 다익스트라는 **한 번 발견된 정점이 끝이 아니라, 더 짧게 다시 발견될 수 있다.**
그래서 큐 삽입 시 방문확정이 불가능하다.

---

## 9. 기본 구현 틀

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int v, w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
        }

        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.v] < cur.w) continue;

            for (Node next : graph[cur.v]) {
                if (dist[next.v] > dist[cur.v] + next.w) {
                    dist[next.v] = dist[cur.v] + next.w;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) sb.append("INF\n");
            else sb.append(dist[i]).append('\n');
        }
        System.out.print(sb);
    }
}
```

---

## 10. 경로 복원까지 하는 방법

최단거리만 구하는 게 아니라 실제 경로를 출력하려면 `prev[]` 배열을 둔다.

* `prev[next] = cur`
* 즉, `next`로 오기 직전 정점을 저장한다.

```java
if (dist[next.v] > dist[cur.v] + next.w) {
    dist[next.v] = dist[cur.v] + next.w;
    prev[next.v] = cur.v;
    pq.add(new Node(next.v, dist[next.v]));
}
```

복원은 도착점에서 시작점으로 거꾸로 따라간다.

```java
List<Integer> path = new ArrayList<>();
int cur = end;
while (cur != start) {
    path.add(cur);
    cur = prev[cur];
}
path.add(start);
Collections.reverse(path);
```

---

## 11. 도착점만 필요할 때 early break

다익스트라는 pq에서 꺼낸 순간 그 정점의 최단거리가 확정된다.
그래서 특정 도착점 `end`만 필요하면 다음처럼 중간 종료가 가능하다.

```java
while (!pq.isEmpty()) {
    Node cur = pq.poll();

    if (dist[cur.v] < cur.w) continue;
    if (cur.v == end) break;

    for (Node next : graph[cur.v]) {
        if (dist[next.v] > dist[cur.v] + next.w) {
            dist[next.v] = dist[cur.v] + next.w;
            pq.add(new Node(next.v, dist[next.v]));
        }
    }
}
```

### 주의

낡은 정보를 먼저 걸러낸 뒤 목적지 여부를 보는 습관이 좋다.

---

## 12. 자주 하는 실수

### 12-1. 방문을 pq에 넣을 때 처리함

이건 BFS 습관 때문에 가장 많이 나오는 실수다.
다익스트라는 poll 시 확정이다.

### 12-2. `if (dist[cur] < w) continue;`를 빼먹음

그러면 pq에 들어간 낡은 정보들을 그대로 처리해서 비효율이 커지거나,
코드를 잘못 짜면 오답 원인이 된다.

### 12-3. INF를 너무 작게 잡음

최단거리 합이 INF보다 커질 수 있으면 안 된다.
문제 조건 보고 충분히 큰 값으로 잡아야 한다.

### 12-4. `int` 오버플로우를 고려하지 않음

가중치와 간선 수가 크면 최단거리 합이 `int` 범위를 넘을 수 있다.
그럴 때는 `long`을 써야 한다.

```java
long[] dist = new long[n + 1];
```

### 12-5. 간선 방향을 잘못 넣음

* 방향 그래프인데 양방향으로 넣는 경우
* 무방향 그래프인데 한쪽만 넣는 경우

이것도 매우 흔하다.

---

## 13. BFS / 다익스트라 / 0-1 BFS 구분

### BFS

* 모든 간선 가중치가 같음
* 보통 모두 1
* 큐 사용
* enqueue 시 방문처리 가능

### 다익스트라

* 가중치가 0 이상이고 서로 다를 수 있음
* 우선순위 큐 사용
* poll 시 최단거리 확정

### 0-1 BFS

* 가중치가 0 또는 1만 있음
* 덱(Deque) 사용
* 0 비용은 앞에, 1 비용은 뒤에 넣음

---

## 14. 네 코드 같은 경우의 해석

예를 들어 숨바꼭질류 문제에서:

* `x-1`
* `x+1`
* `2*x`

이 세 이동의 비용이 모두 1이면,
다익스트라로 풀 수는 있지만 **BFS가 더 적절**하다.

반면 비용이 예를 들어:

* `x*2`는 0초
* `x-1`, `x+1`은 1초

이런 식이면 일반 BFS가 아니라 **0-1 BFS** 또는 다익스트라를 고려해야 한다.

즉, 문제의 핵심은
**이동 방법이 여러 개라는 점이 아니라, 각 이동의 비용이 어떻게 되느냐**이다.

---

## 15. 다익스트라를 볼 때 체크할 것

문제를 보자마자 다음을 확인하면 좋다.

1. 간선 가중치에 음수가 있는가?
2. 모든 가중치가 1인가?
3. 가중치가 0 또는 1뿐인가?
4. 최단거리만 필요한가, 경로도 필요한가?
5. 거리 합이 `int` 범위를 넘을 가능성이 있는가?
6. 방향 그래프인가, 무방향 그래프인가?

이걸 먼저 판단하면 알고리즘 선택이 빨라진다.

---

## 16. 한 줄 요약

* 다익스트라는 **음수 가중치가 없는 최단거리 문제**에서 사용한다.
* 우선순위 큐를 사용하며, **pq에서 꺼낸 순간 최단거리가 확정**된다.
* **큐에 넣기 전에 방문처리하면 안 된다.**
* 낡은 정보는 `if (dist[cur] < w) continue;`로 버린다.
* 경로 복원이 필요하면 `prev[]`를 같이 관리한다.
* 가중치가 전부 1이면 BFS, 0/1이면 0-1 BFS도 함께 떠올려야 한다.

---

## 17. 외우기 좋은 문장

**BFS는 들어갈 때 방문, 다익스트라는 나올 때 확정.**

**다익스트라는 같은 정점이 더 짧은 거리로 다시 pq에 들어올 수 있다.**

**최단거리 갱신이 일어나면 dist와 prev를 함께 갱신한다.**
