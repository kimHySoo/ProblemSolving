# Lazy Deletion (지연 삭제)

## 개념

자료구조에서 원소를 **즉시 삭제하지 않고**, 나중에 해당 원소가 필요한 시점에 **유효하지 않으면 그때 버리는** 기법.

> "지금 당장 지우기 어려우면, 꺼낼 때 확인하고 버린다"

---

## 언제 쓰나

우선순위 큐(힙)는 **원하는 위치의 원소를 바로 삭제할 수 없다.**  
중간에 있는 원소를 지우려면 힙 전체를 재구성해야 해서 비효율적이다.

이럴 때 즉시 삭제 대신, **peek/poll 시점에 유효성을 검사**해서 무효한 원소를 걸러낸다.

---

## 슬라이딩 윈도우 최솟값 예시

### 문제
길이 l인 슬라이딩 윈도우에서 각 위치의 최솟값을 구하라.

### 잘못된 방법
```java
// 추가 전에 제거 시도
while(!pq.isEmpty() && i-pq.peek().time+1-l>0) pq.poll();
pq.add(new Tae(x, i));
sb.append(pq.peek().x);
```
peek()은 항상 **최솟값**을 반환하는데, 윈도우를 벗어난 원소가 최솟값이 **아니라면** 제거가 안 된다.  
범위 밖 원소들이 pq 안에 계속 쌓여서 크기가 O(n)까지 증가한다.

### Lazy Deletion 적용
```java
pq.add(new Tae(x, i));
while(!pq.isEmpty() && i - pq.peek().time >= l) pq.poll(); // 최솟값이 범위 밖이면 그때 버림
sb.append(pq.peek().x);
```
- 추가는 무조건 하고, **꺼낼 때 범위 밖이면 버린다**
- peek()의 최솟값이 유효할 때까지 반복해서 제거
- 유효한 최솟값이 나오면 바로 사용

---

## 동작 원리

```
arr = [3, 1, 2, 5, 4], l = 3

i=0: pq=[{3,0}]                      peek → 3
i=1: pq=[{1,1},{3,0}]                peek → 1
i=2: pq=[{1,1},{2,2},{3,0}]          peek={1,1} → i-1=1 < 3, 유효 → 1
i=3: pq=[{1,1},{2,2},{3,0},{5,3}]    peek={1,1} → i-1=2 < 3, 유효 → 1
i=4: pq에 {4,4} 추가
     peek={1,1} → i-1=3 >= 3, 범위 밖 → poll
     peek={2,2} → i-2=2 < 3, 유효 → 2

결과: 3 1 1 1 2
```

---

## 우선순위 큐의 한계 - O(n) 불가능

슬라이딩 윈도우 최솟값을 **O(n)으로 풀려면 단조 덱이 유일한 방법**이다.

| | 단조 덱 | 우선순위 큐 (Lazy Deletion) |
|---|---|---|
| 시간복잡도 | **O(n)** | **O(n log n)** |
| 삽입/삭제 | O(1) | O(log n) |
| 구현 난이도 | 약간 복잡 | 단순 |
| 정확성 | 완벽 | 완벽 |

우선순위 큐는 힙 구조라서 삽입/삭제가 항상 **O(log n)** 이다.  
n개의 원소를 처리하면 최소 **O(n log n)** 이 되므로 O(n)은 구조적으로 불가능하다.

단조 덱은 각 원소가 **딱 한 번씩만 들어가고 나오므로** O(n)이 가능하다.

### 단조 덱 풀이
```java
ArrayDeque<int[]> dq = new ArrayDeque<>(); // {값, 인덱스}

for (int i = 0; i < n; i++) {
    // 뒤에서 현재값보다 크거나 같은 것 제거 (단조 증가 유지)
    while (!dq.isEmpty() && dq.peekLast()[0] >= arr[i]) dq.pollLast();
    dq.addLast(new int[]{arr[i], i});
    // 앞에서 윈도우 벗어난 것 제거
    while (i - dq.peekFirst()[1] >= l) dq.pollFirst();
    sb.append(dq.peekFirst()[0]).append(' ');
}
```

---

## 다른 활용 예시 - 다익스트라

```java
PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]); // {노드, 거리}

while (!pq.isEmpty()) {
    int[] cur = pq.poll();
    int node = cur[0], dist = cur[1];

    if (dist > visited[node]) continue; // ← Lazy Deletion: 이미 더 짧은 경로면 버림

    for (int[] next : graph[node]) {
        if (visited[next[0]] > dist + next[1]) {
            visited[next[0]] = dist + next[1];
            pq.add(new int[]{next[0], dist + next[1]});
        }
    }
}
```
노드를 pq에서 즉시 갱신하는 대신, **꺼낼 때 이미 처리된 노드면 버린다.**

---

## 핵심 정리

1. 우선순위 큐는 **중간 원소를 바로 지울 수 없다**
2. 대신 **꺼낼 때 유효성을 검사**해서 무효하면 버린다
3. 유효한 원소가 나올 때까지 poll을 반복한다
4. 슬라이딩 윈도우에서 O(n)이 필요하면 **단조 덱을 사용**해야 한다
5. 다익스트라, 슬라이딩 윈도우 등에서 자주 사용된다
