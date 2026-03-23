# BOJ 13549 숨바꼭질 3 정리

## 문제 핵심

수빈이의 현재 위치가 `n`, 동생의 위치가 `k`일 때 최소 시간을 구하는 문제다.

가능한 이동은 3가지다.

- `x -> x - 1` : 비용 1
- `x -> x + 1` : 비용 1
- `x -> x * 2` : 비용 0

즉, **모든 간선의 가중치가 같지 않다.**
그래서 일반 BFS로 바로 처리하면 안 되고, 다음 둘 중 하나로 풀어야 한다.

- **0-1 BFS**
- **Dijkstra**

---

# 1. 왜 일반 BFS가 아닌가

일반 BFS는 모든 간선 비용이 동일할 때만 최단거리를 보장한다.

하지만 이 문제는

- `+1`, `-1` 은 1초
- `*2` 는 0초

라서 같은 레벨 탐색으로 처리하면 순서가 꼬일 수 있다.

예를 들어 어떤 정점은 순간이동으로 바로 갈 수 있으므로
단순히 "한 번 이동했으니 한 레벨 증가"처럼 처리하면 안 된다.

---

# 2. 상태 정의

`dist[x]` = 시작점 `n`에서 위치 `x`까지 가는 최소 시간

범위는 보통 `0 ~ 100000` 또는 약간 여유 있게 잡는다.

이 문제에서는 `MAX = 100001` 정도로 두면 충분하다.

---

# 3. 정석 풀이 1: 0-1 BFS

## 핵심 아이디어

간선의 가중치가 **0 또는 1만 존재**하면 deque를 이용한 0-1 BFS를 사용할 수 있다.

- 비용 0인 간선은 `addFirst()`
- 비용 1인 간선은 `addLast()`

이렇게 하면 deque의 앞쪽에는 항상 더 짧은 거리의 정점이 오게 되어
다익스트라처럼 최단거리를 구할 수 있다.

---

## 흐름

현재 위치 `cur`에서 다음 3개를 본다.

- `cur * 2` : 비용 0
- `cur - 1` : 비용 1
- `cur + 1` : 비용 1

갱신 조건은 항상 동일하다.

```java
if (dist[next] > dist[cur] + cost)
```

즉,

- 아직 방문하지 않았거나
- 더 짧은 시간으로 갈 수 있으면

갱신한다.

---

## 0-1 BFS 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dist = new int[MAX];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> dq = new ArrayDeque<>();
        dist[n] = 0;
        dq.add(n);

        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();

            if (cur == k) {
                System.out.println(dist[cur]);
                return;
            }

            int next = cur * 2;
            if (next < MAX && dist[next] > dist[cur]) {
                dist[next] = dist[cur];
                dq.addFirst(next);
            }

            next = cur - 1;
            if (next >= 0 && dist[next] > dist[cur] + 1) {
                dist[next] = dist[cur] + 1;
                dq.addLast(next);
            }

            next = cur + 1;
            if (next < MAX && dist[next] > dist[cur] + 1) {
                dist[next] = dist[cur] + 1;
                dq.addLast(next);
            }
        }
    }
}
```

---

## 왜 `addFirst`, `addLast`를 쓰는가

0초 이동은 현재 거리와 동일하므로 **앞에 넣어야** 한다.

예를 들어 현재 `dist[cur] = 3`일 때

- `cur * 2` 는 `dist = 3`
- `cur - 1`, `cur + 1` 은 `dist = 4`

이므로 3짜리를 먼저 처리해야 최단거리 순서가 유지된다.

즉,

- 0비용 간선 -> 앞
- 1비용 간선 -> 뒤

이 규칙이 0-1 BFS의 핵심이다.

---

## 시간 복잡도

- 정점 수: 약 100001개
- 간선 수: 각 정점당 최대 3개

따라서 전체는 거의 `O(V + E)` 수준으로 동작한다.

이 문제에서는 매우 빠르다.

---

# 4. 정석 풀이 2: Dijkstra

## 핵심 아이디어

가중치가 0 이상이면 다익스트라를 사용할 수 있다.

이 문제도

- 0
- 1

가중치만 있으므로 당연히 다익스트라로 풀 수 있다.

우선순위 큐에는

- 현재 위치
- 그 위치까지의 최소 시간 후보

를 넣는다.

---

## 핵심 포인트

다익스트라에서 중요한 건 단순 boolean 방문이 아니라 **거리 배열 dist**다.

```java
if (dist[next] > time + cost)
```

일 때만 갱신한다.

그리고 우선순위 큐에서 꺼낸 값이 오래된 값일 수 있으므로

```java
if (dist[cur] < time) continue;
```

가 꼭 필요하다.

---

## Dijkstra 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dist = new int[MAX];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        dist[n] = 0;
        pq.add(new int[]{n, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int cur = now[0];
            int time = now[1];

            if (dist[cur] < time) continue;

            if (cur == k) {
                System.out.println(time);
                return;
            }

            int next = cur * 2;
            if (next < MAX && dist[next] > time) {
                dist[next] = time;
                pq.add(new int[]{next, time});
            }

            next = cur - 1;
            if (next >= 0 && dist[next] > time + 1) {
                dist[next] = time + 1;
                pq.add(new int[]{next, time + 1});
            }

            next = cur + 1;
            if (next < MAX && dist[next] > time + 1) {
                dist[next] = time + 1;
                pq.add(new int[]{next, time + 1});
            }
        }
    }
}
```

---

## 시간 복잡도

우선순위 큐를 사용하므로

- `O((V + E) log V)`

정도로 보면 된다.

이 문제에서는 충분히 통과하지만,
0-1 BFS가 더 직접적이고 조금 더 효율적이다.

---

# 5. 두 풀이 비교

## 공통점

둘 다 핵심은 같다.

- 상태는 위치 `x`
- `dist[x]`에 최소 시간 저장
- 더 짧은 시간일 때만 갱신

즉, 둘 다 본질적으로 **최단거리 문제**를 푸는 방식이다.

---

## 차이점

### 0-1 BFS
- 간선 가중치가 0과 1일 때만 사용 가능
- deque 사용
- 더 빠르고 간단함

### Dijkstra
- 가중치가 0 이상이면 일반적으로 사용 가능
- 우선순위 큐 사용
- 더 범용적임

---

## 이 문제에서 추천
이 문제는 간선이 딱 0과 1뿐이므로 **0-1 BFS가 가장 정석적**이다.

하지만 다익스트라로 풀어도 충분히 정답이다.

---

# 6. 자주 하는 실수 정리

## 1) boolean visited만 쓰는 실수

이 문제는 단순 방문 여부보다 **최소 시간 갱신**이 중요하다.

그래서 보통 boolean visited보다 `dist[]`가 맞다.

잘못된 예:
```java
if (!visited[next]) {
    visited[next] = true;
    pq.add(...);
}
```

이렇게 하면 나중에 더 짧은 경로가 나와도 막아버릴 수 있다.

올바른 방식:
```java
if (dist[next] > dist[cur] + cost)
```

---

## 2) 순간이동을 여러 번 압축하는 실수

예를 들어

```java
while (cur * 2 < k) cur *= 2;
```

처럼 중간 상태를 생략하면 안 된다.

이 문제는 중간 지점에서 `-1`, `+1`, `*2`를 다시 선택하는 것이 중요하므로
상태를 임의로 압축하면 최단경로가 깨질 수 있다.

---

## 3) `cur > k`일 때 무조건 걸어가는 식으로 압축하는 실수

예를 들어

```java
pq.add(new int[] {k, time + cur - k});
```

이런 식으로 바로 `k`에 도착시키는 것은
원래 그래프에 없는 간선을 만든 것이다.

최단거리 문제에서는 **문제에서 허용한 간선만** 사용해야 한다.

---

## 4) 범위 체크 안 하는 실수

다음 상태를 넣기 전에 반드시 체크해야 한다.

```java
0 <= next < MAX
```

특히

- `cur - 1`
- `cur * 2`

에서 자주 실수한다.

---

## 5) 다익스트라에서 오래된 상태를 처리하는 실수

우선순위 큐에는 같은 정점이 여러 번 들어갈 수 있다.

그래서 반드시

```java
if (dist[cur] < time) continue;
```

가 필요하다.

---

# 7. 어떤 풀이를 기억하면 좋은가

이 문제를 기준으로 기억하면 좋다.

## 1단계
간선 비용이 모두 같은가?

- 같으면 BFS 가능
- 다르면 최단거리 계열 생각

## 2단계
간선 비용이 0과 1뿐인가?

- 맞으면 0-1 BFS 우선 고려

## 3단계
가중치가 0 이상 일반형인가?

- 다익스트라 고려

즉, 이 문제는

- 일반 BFS X
- 0-1 BFS O
- Dijkstra O

로 정리하면 된다.

---

# 8. 한 줄 요약

이 문제의 본질은 **가중치가 0 또는 1인 최단거리 문제**다.

그래서 정석은

- **0-1 BFS**
- 또는 **Dijkstra**

이고,

핵심 구현은 항상

- `dist[]` 배열 사용
- 더 짧을 때만 갱신
- 범위 체크

이 3가지를 지키는 것이다.
