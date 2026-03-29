# 🚀 Trie 사용 판단 가이드 (코딩테스트용)

## 📌 개요

Trie는 문자열을 효율적으로 저장하고 탐색하기 위한 **자료구조**이다.
하지만 코딩테스트에서는 무조건 사용하는 것이 아니라, **언제 써야 하는지 판단하는 것이 핵심**이다.

---

# ✅ 1. Trie란?

* 문자열을 문자 단위로 나눠 트리 형태로 저장
* 주요 기능

  * 삽입 (insert)
  * 탐색 (search)
  * 접두사 검사 (startsWith)

👉 **문자열 전용 트리 구조**

---

# ⚖️ 2. Trie vs 알고리즘

| 구분   | 의미           | 예시                 |
| ---- | ------------ | ------------------ |
| 자료구조 | 데이터를 저장하는 방법 | Trie, Stack, Queue |
| 알고리즘 | 데이터를 처리하는 방법 | BFS, DFS, 다익스트라    |

👉 Trie는 **자료구조**지만, 코테에서는 **풀이 패턴처럼 사용됨**

---

# 🔥 3. Trie 사용 여부 3초 판별법

## STEP 1️⃣: 접두사 문제인가?

다음 키워드가 나오면 후보

* 접두사
* 시작 문자열
* 자동완성
* 문자열 사전

👉 YES → 다음 단계
👉 NO → Trie 필요 없음

---

## STEP 2️⃣: 정렬로 해결 가능한가?

👉 가능하면 Trie 쓰지 말 것

예: 전화번호 목록

```java
Arrays.sort(arr);
for(int i=0;i<n-1;i++){
    if(arr[i+1].startsWith(arr[i])) return false;
}
```

👉 **정렬이 더 빠르고 구현도 쉬움**

---

## STEP 3️⃣: 반복 탐색 / 쿼리가 많은가?

다음 조건이면 Trie 고려

* insert + search 반복
* prefix 탐색이 여러 번 발생
* 문자열 개수 많고 길이도 김

👉 YES → Trie 사용
👉 NO → 일반 문자열 처리

---

# 🧠 4. 의사결정 트리

```
접두사 문제인가?
  → NO → Trie 필요 없음

YES →
  정렬로 해결 가능?
    → YES → 정렬 사용 (Trie X)
    → NO →
        쿼리 많음?
          → YES → Trie 사용
          → NO → 일반 구현
```

---

# ⚡ 5. 언제 Trie 쓰면 좋은가

* 자동완성 기능
* 문자열 검색 시스템
* 접두사 기반 탐색이 반복될 때
* 문자열 길이 길고 데이터 많을 때

---

# ❌ 6. Trie 쓰면 손해인 경우

* 단순 접두사 비교 (전화번호 목록)
* 단순 존재 여부 확인 (문자열 집합)

👉 대체 방법

* 정렬 + 비교
* HashSet

---

# 🧩 7. 필수 구현 템플릿

## 기본 구조

```java
class Node {
    Node[] child = new Node[26];
    boolean isEnd;
}
```

## insert

```java
void insert(String s) {
    Node cur = root;
    for (char c : s.toCharArray()) {
        int idx = c - 'a';
        if (cur.child[idx] == null) cur.child[idx] = new Node();
        cur = cur.child[idx];
    }
    cur.isEnd = true;
}
```

## search

```java
boolean search(String s) {
    Node cur = root;
    for (char c : s.toCharArray()) {
        int idx = c - 'a';
        if (cur.child[idx] == null) return false;
        cur = cur.child[idx];
    }
    return cur.isEnd;
}
```

## startsWith

```java
boolean startsWith(String s) {
    Node cur = root;
    for (char c : s.toCharArray()) {
        int idx = c - 'a';
        if (cur.child[idx] == null) return false;
        cur = cur.child[idx];
    }
    return true;
}
```

---

# 🔥 8. 핵심 요약

* Trie는 자료구조다
* 코테에서는 **선택 도구**다
* 정렬로 되면 Trie 쓰지 말 것
* 반복 prefix 탐색 많으면 Trie 사용

---

# 🎯 최종 한 줄 정리

👉 **"정렬로 되면 Trie 쓰지 말고, 반복 탐색 많으면 Trie 써라"**
