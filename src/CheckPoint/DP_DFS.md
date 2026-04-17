# DP + DFS 오답노트

## void dfs vs int dfs

### void dfs - 전역 배열에 값을 저장
```java
static int[][] dp;

static void dfs(int v, int mask) {
    if (v > n) return;
    for (int i = 0; i < n; i++) {
        int x = 1 << i;
        if ((x & mask) != 0) continue;
        if (dp[v-1][mask] + work[v][i] < dp[v][x|mask]) {
            dp[v][x|mask] = dp[v-1][mask] + work[v][i];
            dfs(v+1, x|mask);
        }
    }
}
```
- 반환값 없이 전역 dp 배열을 직접 갱신
- 메모이제이션 없이 쓰면 같은 상태를 여러 번 방문 → 시간초과 위험
- 갱신될 때마다 재귀 호출 → 중복 호출 폭발 가능

---

### int dfs - 반환값으로 메모이제이션 (탑다운 DP)
```java
static int[][] dp; // -1로 초기화 = 미방문

static int dfs(int v, int mask) {
    if (v > n) return 0;
    if (dp[v][mask] != -1) return dp[v][mask]; // 이미 계산된 상태면 바로 반환
    dp[v][mask] = inf;
    for (int i = 0; i < n; i++) {
        int x = 1 << i;
        if ((x & mask) != 0) continue;
        dp[v][mask] = Math.min(dp[v][mask], work[v][i] + dfs(v+1, mask|x));
    }
    return dp[v][mask];
}
```
- dp값 자체가 방문 여부 역할 (-1이면 미방문)
- 같은 상태는 딱 한 번만 계산
- visited 배열 따로 필요 없음
- 코드가 직관적이고 버그가 적음

---

## 비교 정리

| | void dfs | int dfs |
|--|---------|---------|
| 메모이제이션 | 구현이 까다로움 | dp값으로 자연스럽게 처리 |
| 재방문 방지 | 불안정 | 안정적 |
| 코드 복잡도 | 높음 | 낮음 |
| 사용 시점 | 상태 전파가 복잡할 때 | 탑다운 DP 표준 |

---

## 이 문제에서 겪은 실수

### void dfs에서 메모이제이션 없이 사용
- 갱신될 때마다 재귀 호출 → 중복 호출 폭발 → 시간초과
- visited로 해결하려 했지만 void dfs 구조상 깔끔하게 처리 불가
- **int dfs로 바꾸는 게 근본적인 해결책**

---

## 결론

> 비트마스킹 DP처럼 상태가 명확한 문제는  
> **int dfs + dp=-1 메모이제이션** 이 가장 깔끔하고 안전하다.

```java
// 탑다운 DP 템플릿
static int dfs(int v, int mask) {
    if (/* 기저 조건 */) return 0;
    if (dp[v][mask] != -1) return dp[v][mask];
    dp[v][mask] = /* 초기값 (inf or 0) */;
    for (/* 선택지 순회 */) {
        dp[v][mask] = Math.min(dp[v][mask], /* 비용 */ + dfs(/* 다음 상태 */));
    }
    return dp[v][mask];
}
```