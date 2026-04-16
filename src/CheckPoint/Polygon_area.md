# 다각형 넓이 구하기 (Polygon Area Algorithm)

## 공식

$$A = \frac{1}{2} \left| \sum_{i=0}^{n-1} (x_{i-1} + x_i)(y_{i-1} - y_i) \right|$$

> 출처: [Math Open Reference - Algorithm to find the area of a polygon](https://www.mathopenref.com/coordpolygonarea2.html)

---

## 원리

각 변에 대해 **Y축까지의 사다리꼴 넓이**를 누적합니다.

- 다각형을 시계 방향으로 순회할 때: 넓이가 **양수**로 누적
- 반시계 방향으로 순회할 때: 넓이가 **음수**로 누적
- 양수/음수가 서로 상쇄되어 **실제 다각형 넓이만 남음**
- 마지막에 `abs`를 취하면 순회 방향에 관계없이 정확한 넓이를 얻을 수 있음

---

## ⚠️ 주의사항

`abs`는 **반드시 최종 합산 후 한 번만** 적용해야 합니다.

```
// ❌ 잘못된 방식 - 매 항마다 abs 적용
for each edge:
    area += abs((x[j] + x[i]) * (y[j] - y[i]))
result = area / 2

// ✅ 올바른 방식 - 마지막에 abs 적용
for each edge:
    area += (x[j] + x[i]) * (y[j] - y[i])
result = abs(area) / 2
```

중간에 `abs`를 씌우면 음수 항이 양수로 바뀌어 상쇄가 일어나지 않고 잘못된 값이 나옵니다.

---

## 예제

꼭짓점: `(0,0) → (2,1) → (0,2) → (-2,1) → (1,1)`

| i | j | x[j]+x[i] | y[j]-y[i] | 곱  |
|---|---|-----------|-----------|-----|
| 0 | 4 | 0+1 = 1   | 0-1 = -1  | -1  |
| 1 | 0 | 2+0 = 2   | 1-0 = 1   | 2   |
| 2 | 1 | 0+2 = 2   | 2-1 = 1   | 2   |
| 3 | 2 | -2+0 = -2 | 1-2 = -1  | 2   |
| 4 | 3 | 1-2 = -1  | 1-1 = 0   | 0   |

합계 = -1 + 2 + 2 + 2 + 0 = **5**

넓이 = |5| / 2 = **2.5**

---

## Java 구현

```java
import java.io.*;
import java.util.*;

public class Main {
    static int[][] poly;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        poly = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            poly[i][0] = Integer.parseInt(st.nextToken());
            poly[i][1] = Integer.parseInt(st.nextToken());
        }

        long area = 0;
        int j = n - 1;
        for (int i = 0; i < n; i++) {
            area += (long)(poly[j][0] + poly[i][0]) * (poly[j][1] - poly[i][1]);
            j = i;
        }

        // abs는 마지막에 한 번만
        System.out.printf("%.1f%n", Math.abs(area) / 2.0);
    }
}
```

### 왜 `long`을 사용하는가?

좌표 절댓값 최대 100,000, N 최대 10,000일 때:

```
최대값 = (100,000 + 100,000) × 200,000 × 10,000 = 4 × 10^14
long 최대값 ≈ 9.2 × 10^18  →  long으로 충분
```

`BigDecimal`은 불필요하며, `long`이 더 빠르고 간단합니다.

---

## 제약 조건

- 자기 교차(self-intersecting) 다각형에는 적용 불가
- 꼭짓점은 순서대로(시계 또는 반시계) 입력되어야 함