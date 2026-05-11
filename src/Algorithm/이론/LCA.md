# Binary Lifting (LCA)

## 개요
Binary Lifting은 트리에서 두 노드의 **최소 공통 조상(LCA, Lowest Common Ancestor)** 을 O(log N)에 구하는 알고리즘이다.

---

## 핵심 아이디어
모든 자연수는 2의 거듭제곱의 합으로 표현할 수 있다 (2진법).

```
6 = 4 + 2     = 110(2)
7 = 4 + 2 + 1 = 111(2)
```

따라서 1, 2, 4, 8, 16... 칸짜리 점프만 있으면 어떤 거리든 log N번 만에 이동 가능하다.

---

## 자료구조

### up[v][k] 테이블
```
up[v][k] = v의 2^k번째 위 조상

up[v][0] = 1칸 위 (직접 부모)
up[v][1] = 2칸 위
up[v][2] = 4칸 위
up[v][3] = 8칸 위
```

---

## 전처리 — O(N log N)

### 점화식
```
up[v][k] = up[up[v][k-1]][k-1]

"v의 2^k번째 조상 = v의 2^(k-1)번째 조상의 2^(k-1)번째 조상"
```

### 예시
```
        1 (depth 0)
        |
        2 (depth 1)
        |
        3 (depth 2)
        |
        4 (depth 3)
        |
        5 (depth 4)

up[5][0] = 4          (1칸)
up[5][1] = 3          (2칸) = up[up[5][0]][0] = up[4][0]
up[5][2] = 1          (4칸) = up[up[5][1]][1] = up[3][1]
```

### 코드
```java
// 직접 부모 세팅
up[1][0] = 1;  // 루트는 자기 자신
for (int i = 2; i <= n; i++)
    up[i][0] = parent;

// 나머지 채우기
for (int k = 1; k < LOG; k++)
    for (int v = 1; v <= n; v++)
        up[v][k] = up[up[v][k-1]][k-1];
```

---

## LCA 탐색 — O(log N)

### 구조식
```
1. u를 더 깊은 노드로 설정
   if dist[u] < dist[v] → swap(u, v)

2. depth 차이만큼 u 올리기
   diff = dist[u] - dist[v]
   diff를 2진수로 분해해서 필요한 칸만 점프
   for k = 0 to LOG:
       if (diff >> k) & 1 == 1 → u = up[u][k]

3. 같은 노드면 바로 반환
   if u == v → return u

4. 동시에 올라가며 LCA 바로 아래까지
   for k = LOG-1 to 0:
       if up[u][k] ≠ up[v][k] → u = up[u][k], v = up[v][k]
   return up[u][0]
```

### 4단계 핵심
```
"조상이 다르다" = 아직 LCA 아래 → 올라간다
"조상이 같다"  = LCA를 넘을 수 있다 → 스킵

큰 k부터 시도해야 LCA를 넘지 않는 최대한 올라갈 수 있다
루프가 끝나면 u, v는 LCA 바로 아래에 위치
→ up[u][0] = LCA
```

### 코드
```java
static int lca(int u, int v) {
    // 1. u를 더 깊은 노드로
    if (dist[u] < dist[v]) { int tmp = u; u = v; v = tmp; }

    // 2. depth 차이만큼 u 올리기
    int diff = dist[u] - dist[v];
    for (int k = 0; k < LOG; k++)
        if (((diff >> k) & 1) == 1) u = up[u][k];

    // 3. 같으면 바로 반환
    if (u == v) return u;

    // 4. 동시에 올라가며 LCA 바로 아래까지
    for (int k = LOG - 1; k >= 0; k--)
        if (up[u][k] != up[v][k]) { u = up[u][k]; v = up[v][k]; }

    return up[u][0];
}
```

---

## 거리 공식
```
dist(u, v) = depth[u] + depth[v] - 2 × depth[LCA(u, v)]
```

---

## 실행 예시

```
          1 (depth 0)
         / \
        2   3 (depth 1)
       / \
      4   5 (depth 2)
     /
    6 (depth 3)

lca(6, 3):

1단계: u=6(depth3), v=3(depth1)

2단계: diff=2=10(2)
       k=1: 2칸 점프 → u=up[6][1]=2
       결과: u=2, v=3 (같은 depth)

3단계: u≠v → 통과

4단계: k=0: up[2][0]=1, up[3][0]=1 → 같으니 스킵
       return up[2][0] = 1 ✅

거리: dist[6]+dist[3]-2×dist[1] = 3+1-0 = 4
```

---

## 복잡도

| | 시간 | 공간 |
|---|---|---|
| 전처리 | O(N log N) | O(N log N) |
| 쿼리 1회 | O(log N) | - |

---

## 다른 LCA 방식과 비교

| 방식 | 전처리 | 쿼리 | 온라인 | 난이도 |
|------|--------|------|--------|--------|
| 단순 탐색 | O(1) | O(N) | ✅ | ⭐ |
| Binary Lifting | O(N log N) | O(log N) | ✅ | ⭐⭐ |
| 오일러 투어 + RMQ | O(N log N) | O(1) | ✅ | ⭐⭐⭐ |
| Tarjan Offline | O(N+Q) | - | ❌ | ⭐⭐⭐ |

---

## LOG 값 설정
```
2^LOG > N 을 만족하는 최솟값으로 설정

N ≤ 100,000  → LOG = 17  (2^17 = 131,072)
N ≤ 200,000  → LOG = 18  (2^18 = 262,144)
N ≤ 1,000,000 → LOG = 20 (2^20 = 1,048,576)
```
