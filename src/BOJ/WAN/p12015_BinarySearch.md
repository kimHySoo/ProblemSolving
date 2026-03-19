# LIS (Longest Increasing Subsequence) - 이진탐색 풀이 정리

## 1. 개념

LIS (최장 증가 부분 수열) → 수열에서 **strictly 오름차순**으로 증가하는 부분 수열 중
가장 긴 것의 길이 (연속일 필요 없음)

예시: `10 20 15 30 25 40` → LIS = `10 15 25 40`, 길이 = **4**

---

## 2. 핵심 아이디어

`lis[i]` = 길이가 `i+1`인 증가 부분 수열의 **마지막 값의 최솟값**을 저장

> ⚠️ 실제 LIS 수열이 아니라, 각 길이별 "가장 유리한 끝값"을 관리하는 배열

| 원소 추가 | lis 배열 상태 |
|-----------|--------------|
| 10        | [10]         |
| 20        | [10, 20]     |
| 15        | [10, 15]     |
| 30        | [10, 15, 30] |
| 25        | [10, 15, 25] |
| 40        | [10, 15, 25, 40] |

→ 최종 배열 길이 = **LIS 길이**

---

## 3. 알고리즘 흐름

각 원소 `x`에 대해:

1. `x`가 `lis` 마지막 값보다 크면 → **뒤에 추가** (len++)
2. 아니면 → **lower bound 위치를 찾아 교체** (길이 유지, 끝값만 갱신)
```java
int idx = lowerBound(0, len, x);
lis[idx] = x;
if (idx == len) len++;
```

---

## 4. Lower Bound

**"key 이상이 처음 나오는 위치"** 를 이진탐색으로 찾는다.
```java
static int lowerBound(int left, int right, int key) {
    while (left < right) {
        int mid = (left + right) / 2;
        if (lis[mid] >= key) right = mid;
        else left = mid + 1;
    }
    return left;
}
```

> ✅ `>=` 조건 → 같은 값이 있으면 그 위치로 교체 (중복 원소 처리)
> ✅ 찾은 위치의 값을 x로 덮어써도 이전 결과에 영향 없음 (단조증가 유지)

---

## 5. 실제 LIS 수열 복원 (길이만이 아닌 수열 자체가 필요할 때)

기본 풀이는 **길이만** 구할 수 있다. 수열 복원은 별도 배열이 필요하다.
```java
int[] trace = new int[n]; // trace[i] = i번째 원소가 lis 몇 번 인덱스에 들어갔는지
int[] parent = new int[n]; // 역추적용

for (int i = 0; i < n; i++) {
    int idx = lowerBound(0, len, arr[i]);
    lis[idx] = arr[i];
    trace[i] = idx;
    if (idx == len) len++;
}

// 역추적: 뒤에서부터 trace[i] == 현재 목표 인덱스인 것을 찾아 수집
List<Integer> result = new ArrayList<>();
int target = len - 1;
for (int i = n - 1; i >= 0; i--) {
    if (trace[i] == target) {
        result.add(arr[i]);
        target--;
    }
}
Collections.reverse(result);
```

---

## 6. 엣지 케이스 & 주의사항

| 상황 | 처리 |
|------|------|
| **중복 원소** (예: `1 2 2 3`) | `lower_bound` 사용 → 같은 값은 교체, LIS에 포함 안 됨 |
| **감소 수열** (예: `5 4 3 2 1`) | LIS 길이 = 1 |
| **원소 1개** | LIS 길이 = 1 |
| **non-strictly increasing 허용** 시 | `lower_bound` → `upper_bound`로 변경 (`>` 조건) |

---

## 7. 정석 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static int[] lis;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        lis = new int[n];
        int len = 0;

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            int idx = lowerBound(0, len, x); // x 이상이 처음 나오는 위치
            lis[idx] = x;
            if (idx == len) len++;           // 새 길이 갱신
        }

        System.out.println(len);
    }

    static int lowerBound(int left, int right, int key) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] >= key) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}
```

---

## 8. DP 풀이와 비교

| 항목 | DP (O(N²)) | 이진탐색 (O(N log N)) |
|------|-----------|----------------------|
| 시간복잡도 | O(N²) | O(N log N) |
| 구현 난이도 | 쉬움 | 중간 |
| 수열 복원 | 쉬움 | 별도 처리 필요 |
| N 범위 | ~5,000 | ~1,000,000 |

> 코테에서 N이 크면 (≥ 10,000) 반드시 이진탐색 풀이 사용

---

## 9. 핵심 요약

- `lis[i]` = 길이 `i+1`인 부분 수열의 **마지막 최솟값** (실제 수열 아님)
- 각 원소마다 **lower bound 위치에 덮어쓰기**
- 최종 `len` = LIS 길이
- 수열 복원이 필요하면 **trace 배열** 별도 관리
- 중복 허용 여부에 따라 `lower_bound` / `upper_bound` 선택