# 📝 오답노트 - 백준 11062 카드 게임 (게임 이론)

---

## 📌 문제 정보

- **문제**: 백준 11062 - 카드 게임
- **유형**: 게임 이론 + DP (메모이제이션)
- **핵심 개념**: Minimax, 두 플레이어 최적 전략

---

## ❌ 내가 처음 접근했던 방식

### 코드 (첫 번째 시도)

```java
static int dfs(int turn, int left, int right) {
    if (turn == n) return 0;
    else if (turn + 1 == n) return game[left];
    if (left > right) return 0;
    if (dp[left][right] != -1) return dp[left][right];

    int res = 0;
    if (left + 3 <= n && game[left + 2] > game[right])
        res = Math.max(res, game[left + 1] + dfs(turn + 2, left + 3, right));
    else if (left + 2 <= n && game[left + 2] <= game[right])
        res = Math.max(res, game[left + 1] + dfs(turn + 2, left + 2, right - 1));
    if (right - 3 >= left && game[left + 1] < game[right - 1])
        res = Math.max(res, game[right] + dfs(turn + 2, left, right - 2));
    else if (right - 2 >= left + 1 && game[left + 1] >= game[right - 1])
        res = Math.max(res, game[right] + dfs(turn + 2, left + 1, right - 1));

    return dp[left][right] = res;
}
```

### 코드 (두 번째 시도)

```java
static int dfs(int left, int right) {
    if (left > right) return 0;
    if (left == right) return game[left];
    if (dp[left][right] != -1) return dp[left][right];

    int res = 0;
    if (left + 2 <= right) {
        if (game[left + 2] > game[right])
            res = Math.max(res, game[left + 1] + dfs(left + 2, right));
        else
            res = Math.max(res, game[left + 1] + dfs(left + 1, right - 1));
    }
    if (left <= right - 1) {
        if (game[left + 1] > game[right - 2])
            res = Math.max(res, game[right - 1] + dfs(left, right - 2));
        else
            res = Math.max(res, game[right - 1] + dfs(left + 1, right - 1));
    }
    return dp[left][right] = res;
}
```

---

## 🚫 잘못 생각한 점

### 1. 상대방 행동을 직접 예측하려 했다

```
game[left+2] > game[right] 이면 상대가 왼쪽을 선택할 것이다 (❌)
```

- **왜 틀렸나?**  
  상대방은 눈앞의 카드 하나만 보고 결정하지 않는다.  
  남은 전체 범위를 고려해서 재귀적으로 최적 선택을 한다.  
  즉, 바로 옆 카드 하나를 비교하는 것만으로는 절대 상대의 행동을 예측할 수 없다.

- **예시**:  
  카드가 `[3, 1, 4, 1, 5]` 일 때, 상대가 왼쪽을 선택할지 오른쪽을 선택할지는  
  이후 펼쳐질 전체 게임 트리에 달려 있어서, 옆 카드 비교로 판단 불가능하다.

### 2. turn 파라미터가 불필요했다

- `turn`을 추가로 관리했는데, `left`와 `right`만 있으면 현재 턴 정보는 자동으로 파악된다.
- `(n - (right - left + 1))`로 몇 장 뽑혔는지 계산 가능하므로 turn은 중복 정보다.

### 3. dp 상태를 두 플레이어 따로 관리하려 했다

- 근우 턴, 명우 턴을 구분해서 생각했는데,  
  **"현재 플레이어" 기준으로 통일**하면 하나의 dp 배열로 충분하다.

---

## ✅ 올바른 풀이

### 핵심 아이디어

> `dp[left][right]` = **현재 플레이어**가 `[left ~ right]` 범위에서 최적으로 플레이했을 때 얻는 최대 점수

```
내가 왼쪽 선택 → 상대가 [left+1, right]에서 최적으로 가져감
→ 내 몫 = 전체합 - 상대 최대

내가 오른쪽 선택 → 상대가 [left, right-1]에서 최적으로 가져감
→ 내 몫 = 전체합 - 상대 최대

→ 둘 중 내 몫이 큰 걸 선택
```

### 올바른 코드

```java
import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] game;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        while (test-- > 0) {
            n = Integer.parseInt(br.readLine());
            game = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) game[i] = Integer.parseInt(st.nextToken());
            dp = new int[n + 1][n + 1];
            for (int[] row : dp) Arrays.fill(row, -1);
            System.out.println(dfs(1, n));
        }
    }

    static int dfs(int left, int right) {
        if (left > right) return 0;
        if (left == right) return game[left];
        if (dp[left][right] != -1) return dp[left][right];

        int total = 0;
        for (int i = left; i <= right; i++) total += game[i];

        int pickLeft  = total - dfs(left + 1, right);  // 내가 왼쪽 선택
        int pickRight = total - dfs(left, right - 1);  // 내가 오른쪽 선택

        return dp[left][right] = Math.max(pickLeft, pickRight);
    }
}
```

### 점화식 정리

```
dp[left][right] = max(
    total(left, right) - dp[left+1][right],   // 왼쪽 선택
    total(left, right) - dp[left][right-1]    // 오른쪽 선택
)
```

---

## 🎯 게임 이론 문제 접근법 정리

### 판별 기준

다음 특징이 있으면 게임 이론 문제다:
- 두 플레이어가 번갈아 행동
- 둘 다 최적으로 플레이
- 한 명의 점수를 최대화하는 것이 목표

### 접근 순서

```
1. dp 상태 정의
   → "현재 플레이어" 기준으로 통일 (턴 구분 X)

2. 점화식
   → total - dfs(상대 범위) 패턴

3. 기저 조건
   → 카드 없으면 0, 카드 1장이면 그 값

4. 메모이제이션
   → dp 배열로 중복 계산 방지
```

### 핵심 패턴

```java
// 내가 선택지 A를 고르면
int pickA = total - dfs(상대 범위 A);

// 내가 선택지 B를 고르면
int pickB = total - dfs(상대 범위 B);

// 내 몫이 더 큰 선택
return Math.max(pickA, pickB);
```

### 자주 하는 실수

| 실수 | 이유 |
|------|------|
| 상대 행동을 직접 예측 | 상대도 재귀적으로 최적 행동 → 한 단계만 봐서는 알 수 없음 |
| turn 파라미터 추가 | left, right만으로 현재 상태 파악 가능 |
| 플레이어별 dp 분리 | 현재 플레이어 기준 통일 시 하나로 충분 |

---

## 💡 배운 점

- **`total - dfs()`** 패턴이 Minimax를 가장 간결하게 표현한다
- 상대방의 행동을 내가 직접 예측하는 것이 아니라, **상대방도 같은 함수(최적 전략)로 행동한다고 가정**하면 된다
- dp 상태를 "현재 플레이어" 기준으로 통일하면 복잡도가 크게 줄어든다