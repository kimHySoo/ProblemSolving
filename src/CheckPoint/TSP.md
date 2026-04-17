# TSP 오답노트

## 문제 정의

n개의 도시를 **모두 한 번씩 방문**하고 시작점으로 돌아오는 **최소 비용 경로**를 구하는 문제

---

## PS에서의 풀이 - 비트마스킹 DP

### dp 상태 정의
```
dp[cur][mask]
- cur  : 현재 위치한 도시
- mask : 방문한 도시들의 집합 (비트마스크)
```

### 점화식
```
dp[cur][mask] = min(cost[cur][i] + dp[i][mask|i])
                단, i는 아직 방문 안 한 도시
```

### 시간복잡도
```
(n-1)!  →  n² × 2^n  으로 단축
n=20    →  약 8000만 연산 → 가능
```

---

## 핵심 구현

```java
static int dfs(int v, int mask, int cur) {
    if(v == n-1) return cost[cur][0] == 0 ? inf : cost[cur][0]; // 시작점(0)으로 복귀
    if(dp[cur][mask] != -1) return dp[cur][mask];
    dp[cur][mask] = inf;
    for(int i=1;i<n;i++) {
        int x = 1<<i;
        if((x&mask) != 0 || cost[cur][i] == 0) continue;
        dp[cur][mask] = Math.min(dp[cur][mask],
            cost[cur][i] + dfs(v+1, mask|x, i));
    }
    return dp[cur][mask];
}

// 호출: 0번 도시 고정, mask에 0번 표시
dfs(0, 1, 0);
```

---

## 겪은 실수

### dp 상태를 dp[v][mask]로 설계
```java
// 잘못된 코드
dp[v][mask]  // v = 몇 번째 방문인지

// 문제점:
// 같은 mask여도 cur(현재 위치)가 다르면 결과가 다름
// cur이 상태에 없어서 잘못된 값을 재사용함

// 올바른 코드
dp[cur][mask]  // cur = 현재 위치한 도시
```

### start를 매개변수로 관리
```java
// 잘못된 코드
static int dfs(int v, int mask, int cur, int start) {
    if(mask == 0) start = i; // start가 dp 상태에 없는데 결과에 영향
    ...
    return cost[cur][start]; // start에 따라 결과가 달라짐
}
// 문제점:
// start가 dp 상태(dp[v][mask])에 포함 안 되어 있음
// start=1일 때와 start=2일 때 dp값이 달라야 하는데 같은 칸에 저장

// 해결:
// 시작점을 0으로 고정 → start 매개변수 불필요
```

### 시작점 고정이 답에 영향을 준다고 착각
```
TSP는 순환 경로이므로 어디서 시작해도 총 비용 동일
1→2→3→4→1 = 2→3→4→1→2 = 3→4→1→2→3

시작점 고정 시:
- 탐색 경우의 수 n배 감소
- start 매개변수 관리 불필요
- 메모이제이션 깔끔해짐
```

---

## PS vs 실무

| | PS | 실무 |
|--|----|----|
| n | ≤ 20 | 수백~수천 |
| 요구 | 정확한 최적해 | 근사해도 OK |
| 풀이 | 비트마스킹 DP | 휴리스틱, 유전 알고리즘, OR-Tools |
| 복잡도 | n² × 2^n | NP-hard (정확한 최적해 불가) |

실무에서 n이 크면 **근사 알고리즘**(2-opt, 3-opt)이나 **메타휴리스틱**(유전 알고리즘, 시뮬레이티드 어닐링)으로 "충분히 좋은 해"를 구합니다.

---

## 결론

> PS의 TSP는 **dp[cur][mask] + 시작점 고정** 이 표준 풀이  
> 실무의 TSP는 n이 커서 정확한 최적해 자체가 난제