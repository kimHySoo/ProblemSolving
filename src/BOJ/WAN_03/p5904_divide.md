# 📘 Moo 게임 (BOJ 5904) 오답노트

## 🔥 문제 핵심
문자열을 직접 만들지 않고  
**n번째 문자가 무엇인지 찾는 문제**

---

## ❌ 내가 처음에 했던 잘못된 접근

### 1. 재귀 호출 순서 = 문자열 인덱스라고 착각
```java
solve(x+1, v-1);
solve(x+1, v);
solve(x+1, v-1);
```

👉 재귀 호출 1번 = 문자 1개가 아니라 큰 덩어리

---

### 2. 위치를 직접 세려고 한 것
```java
if(x+1 == n)
```

👉 x는 실제 인덱스가 아님

---

### 3. “찾았다” = “m이다”라고 생각
```java
if(ok) System.out.println('m');
```

👉 위치 판단과 문자 판단은 다름

---

## ✅ 정답 접근

```java
S(0) = "moo"
S(k) = S(k-1) + "m" + "o"*(k+2) + S(k-1)
```

---

## 🧠 핵심 사고방식
👉 구간 분할 (Divide & Conquer)

---

## 🔑 풀이 흐름

### 1️⃣ 길이 찾기
```java
while (len < n) {
    k++;
    len = len * 2 + (k + 3);
}
```

### 2️⃣ 구간 나누기
```java
prev = (len - (k+3)) / 2
```

### 3️⃣ 판별
```java
if (n <= prev)
    → 왼쪽
else if (n == prev + 1)
    → 'm'
else if (n <= prev + k + 3)
    → 'o'
else
    → 오른쪽
```

---

## 💻 최종 코드
```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int len = 3;
        int k = 0;

        while (len < n) {
            k++;
            len = len * 2 + k + 3;
        }

        System.out.println(solve(n, len, k));
    }

    static char solve(int n, int len, int k) {
        int prev = (len - k - 3) / 2;

        if (n <= prev) return solve(n, prev, k - 1);
        else if (n == prev + 1) return 'm';
        else if (n <= len - prev) return 'o';
        else return solve(n - prev - k - 3, prev, k - 1);
    }
}
```

---

## 🚀 한 줄 요약
👉 문자열 생성 ❌  
👉 구간 분할로 위치 추적 ⭕
