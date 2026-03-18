# 📌 Java에서 long과 int 연산 시 주의사항 (Overflow)

## 🔥 핵심 결론

> **저장은 long으로 되지만, 계산 과정에서 이미 overflow가 발생할 수 있다.**

---

## 🧠 문제 상황

```java
long sum = 0;
int a = 2000000000;
int b = 2000000000;

sum = a + b;
```

### ❗ 실제 동작

* `a + b`는 **int + int 연산**
* 따라서 결과도 **int로 계산됨**
* 이 과정에서 이미 overflow 발생

```text
2000000000 + 2000000000 = 4000000000 (int 범위 초과)
→ overflow 발생 → 잘못된 값
→ 그 값을 long에 저장
```

👉 즉, **long에 저장해도 이미 늦음**

---

## 📏 int 범위

```text
-2,147,483,648 ~ 2,147,483,647
```

이 범위를 넘으면 **overflow 발생**

---

## ✅ 올바른 해결 방법

### 1️⃣ 하나를 long으로 캐스팅

```java
sum = (long)a + b;
```

👉 연산 자체가 long으로 수행됨 (안전)

---

### 2️⃣ 누적 방식 사용 (추천)

```java
sum += a;
```

👉 `sum`이 long이므로 자동으로 long 연산 수행

---

## ⚠️ 핵심 개념 정리

| 연산 형태       | 결과 타입 | 안전 여부         |
| ----------- | ----- | ------------- |
| int + int   | int   | ❌ overflow 가능 |
| long + int  | long  | ✅ 안전          |
| long += int | long  | ✅ 안전          |

👉 **저장 타입이 아니라 "연산 타입"이 중요하다**

---

## 🚨 알고리즘에서 자주 터지는 경우

다음 상황에서는 반드시 `long` 사용 고려

* 누적합 (Prefix Sum)
* 큰 수 합 계산
* n이 크고 값도 큰 입력

예:

```java
long[] prefix = new long[n+1];
prefix[i] = prefix[i-1] + arr[i];
```

---

## 💡 실전 팁

✔️ 항상 이렇게 습관화

```java
long sum = 0;
for(int i = 0; i < n; i++) {
    sum += arr[i];
}
```

✔️ 또는

```java
prefix[i] = prefix[i-1] + (long)arr[i];
```

---

## 🧩 한 줄 요약

> **int끼리 먼저 계산되면 이미 터진다 → 연산을 long으로 만들어라**

---

필요하면:

* prefix sum에서 long 기준
* long 써야 하는 문제 유형 정리

도 이어서 정리 가능 👍
