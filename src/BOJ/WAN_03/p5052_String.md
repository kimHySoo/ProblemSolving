# 전화번호 목록 오답노트

## 문제 핵심

전화번호 목록에서 어떤 번호가 다른 번호의 **접두사(prefix)** 인지 검사하는 문제다.

예를 들어:

- `119`
- `1195524421`

여기서는 `119`가 `1195524421`의 접두사이므로 일관성이 없는 목록이다.

즉, 하나라도 접두사 관계가 있으면 `NO`, 없으면 `YES`를 출력해야 한다.

---

## 내가 작성한 코드

```java
import java.util.*;
import java.io.*;
public class Main {
    static int n;
    static List<Integer>[] lst;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(br.readLine());
        while(test-->0) {
            n = Integer.parseInt(br.readLine());
            lst = new ArrayList[n];
            for(int i=0;i<n;i++) {
                String line = br.readLine();
                lst[i]=new ArrayList<>();
                for(int j=0;j<line.length();j++) lst[i].add(line.charAt(j)-'0');
            }
            boolean ok = chk();
            sb.append(!ok?"YES\n":"NO\n");
        }
        System.out.print(sb.toString());
    }
    static boolean chk() {
        for(int i=0;i<n-1;i++) for(int j=i+1;j<n;j++) {
            int x = lst[i].size();
            int y = lst[j].size();
            x=Math.min(x, y);
            int idx = 0;
            for(int k=0;k<x;k++) {
                if(lst[i].get(k)==lst[j].get(k)) idx++;
                else break;
            }
            if(idx==x) return true;
        }
        return false;
    }
}
```

---

## 내 코드 해설

이 코드는 모든 전화번호 쌍 `(i, j)`를 직접 비교해서 접두사 여부를 검사한다.

### 동작 방식

1. 각 전화번호를 문자열이 아니라 `List<Integer>`로 저장
2. 두 번호를 비교할 때 더 짧은 길이까지만 앞에서부터 비교
3. 짧은 쪽 길이만큼 전부 같으면 접두사라고 판단

예를 들어:

- `119`
- `1195524421`

앞 3자리까지 모두 같고, 더 짧은 문자열 길이가 3이므로 접두사 관계라고 판정한다.

---

## 내 코드의 장점

- 접두사 판정을 직접 구현해서 원리는 명확하다.
- 문자열 비교 원리를 이해하는 데 도움이 된다.
- 처음 문제 접근으로는 충분히 가능하다.

---

## 내 코드의 아쉬운 점

### 1. 모든 쌍을 비교한다
전화번호가 `n`개라면 모든 쌍을 비교하므로:

- 쌍 비교: `O(n^2)`
- 각 비교에서 최대 길이 `L`만큼 비교

전체 시간복잡도는 대략:

```text
O(n^2 * L)
```

입력이 커지면 비효율적이다.

### 2. `List<Integer>` 사용이 비효율적이다
전화번호는 원래 문자열인데, 각 숫자를 `Integer`로 박싱해서 저장하고 있다.

즉:

- 메모리 낭비
- `get()` 호출 비용
- 문자열 메서드를 활용하지 못함

이 문제에서는 `String` 그대로 저장하는 것이 더 자연스럽고 빠르다.

### 3. 문제의 핵심 성질을 활용하지 못했다
이 문제는 문자열 정렬이나 Trie를 쓰면 훨씬 효율적으로 풀 수 있다.

---

## 핵심 개념 1: 문자열 정렬

자바에서 `String` 정렬은 **사전순(lexicographical order)** 으로 정렬된다.

비교 방식:

1. 앞에서부터 한 글자씩 비교
2. 처음 다른 글자가 나오면 그 문자 기준으로 정렬
3. 끝까지 같으면 더 짧은 문자열이 앞

예시:

```text
"119" < "1195524421"
"12" < "123"
"10" < "2"   // 숫자 크기가 아니라 문자 비교
```

이 성질 때문에 접두사 관계인 문자열은 정렬 후 서로 가까이 붙는다.

---

## 정렬 풀이 아이디어

전화번호 목록을 정렬하면, 접두사 관계가 있는 문자열은 반드시 인접하게 나타난다.

예시:

```text
입력:
911
97625999
91125426

정렬 후:
911
91125426
97625999
```

이제 모든 쌍을 볼 필요 없이, **인접한 두 문자열만 비교**하면 된다.

즉:

```java
if (arr[i + 1].startsWith(arr[i]))
```

이면 접두사 충돌이다.

---

## 정렬 풀이 정석 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test = Integer.parseInt(br.readLine());

        while (test-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] arr = new String[n];

            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine();
            }

            Arrays.sort(arr);

            boolean ok = true;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i + 1].startsWith(arr[i])) {
                    ok = false;
                    break;
                }
            }

            sb.append(ok ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
    }
}
```

---

## 왜 인접한 것만 보면 되는가?

정렬 후에는 앞부분이 같은 문자열들이 연속으로 모인다.

예를 들어 `119`를 접두사로 가지는 문자열들이 있다면:

```text
119
1191
11923
1195524421
```

이런 식으로 붙어 있게 된다.

따라서 어떤 문자열이 다른 문자열의 접두사라면, 그 문자열 바로 뒤쪽 어딘가에 반드시 이어진다.  
그리고 그 중 가장 가까운 문자열과의 비교만으로도 접두사 여부를 발견할 수 있다.

그래서 전체 쌍 비교가 필요 없다.

---

## 정렬 풀이 시간복잡도

- 정렬: `O(n log n)`
- 인접 비교: `O(n * L)`

전체적으로 매우 효율적이고, 구현도 가장 간단하다.

실전 코테에서는 이 풀이가 가장 많이 사용된다.

---

## 핵심 개념 2: Trie(트라이)

Trie는 문자열을 문자 단위로 저장하는 트리 자료구조다.

전화번호라면 각 자리 숫자를 따라 내려가는 구조가 된다.

예를 들어:

- `119`
- `1195`
- `97`

를 넣으면 개념적으로는 다음과 비슷하다:

```text
(root)
 ├─ 1
 │   └─ 1
 │       └─ 9 (끝)
 │           └─ 5 (끝)
 └─ 9
     └─ 7 (끝)
```

여기서 경로 하나가 문자열 하나를 의미한다.

---

## Trie에서 중요한 값

각 노드는 보통 다음 정보를 가진다.

- 자식 노드들
- 문자열이 여기서 끝나는지 여부 (`isEnd`)

즉:

```java
boolean isEnd;
```

가 매우 중요하다.

---

## Trie로 접두사 충돌을 찾는 원리

### 경우 1. 기존 번호가 새 번호의 접두사인 경우

예를 들어 이미 `119`가 들어 있는데 `1195`를 넣는 경우:

- `1 -> 1 -> 9` 까지 내려갔을 때
- 그 노드가 이미 `isEnd = true`

그러면 기존 번호가 접두사라는 뜻이다.

### 경우 2. 새 번호가 기존 번호의 접두사인 경우

예를 들어 이미 `1195`가 들어 있는데 `119`를 넣는 경우:

- 새 번호를 끝까지 다 넣고 나서
- 현재 노드 아래에 자식이 남아 있으면

내가 더 짧은 접두사라는 뜻이다.

---

## Trie 정석 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        Node[] child = new Node[10];
        boolean isEnd;
    }

    static class Trie {
        Node root = new Node();

        boolean insert(String s) {
            Node cur = root;

            for (int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - '0';

                if (cur.child[idx] == null) {
                    cur.child[idx] = new Node();
                }

                cur = cur.child[idx];

                // 기존 번호가 접두사인 경우
                if (cur.isEnd) return false;
            }

            // 새 번호가 기존 번호의 접두사인 경우
            for (int i = 0; i < 10; i++) {
                if (cur.child[i] != null) return false;
            }

            cur.isEnd = true;
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test = Integer.parseInt(br.readLine());

        while (test-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            boolean ok = true;

            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                if (ok && !trie.insert(s)) ok = false;
            }

            sb.append(ok ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
    }
}
```

---

## Trie 풀이 시간복잡도

문자열 하나 삽입에 `O(L)`  
전체 `n`개 삽입에:

```text
O(n * L)
```

이론상 매우 좋다.

다만 자바에서는:

- 코드가 길어짐
- 메모리를 더 사용함
- 실전에서는 정렬 풀이가 더 간단하고 충분히 빠름

그래서 코테에서는 보통 정렬 풀이가 먼저 떠오르면 그걸 쓰는 편이 좋다.

---

## 세 풀이 비교

| 방식 | 시간복잡도 | 장점 | 단점 |
|---|---:|---|---|
| 내 코드(모든 쌍 비교) | `O(n^2 * L)` | 원리가 직관적 | 느림 |
| 정렬 + 인접 비교 | `O(n log n + nL)` | 가장 간단하고 실전적 | 정렬 원리를 이해해야 함 |
| Trie | `O(nL)` | 접두사 문제에 정석 | 구현이 길고 메모리 사용 큼 |

---

## 이 문제에서 배워야 할 포인트

### 1. 문자열은 문자열로 다루자
숫자로만 구성되어 있어도 이 문제는 숫자 크기 비교가 아니라 **접두사 비교** 문제다.  
따라서 `int`나 `List<Integer>`보다 `String`이 맞다.

### 2. 모든 쌍 비교를 먼저 의심하자
`O(n^2)` 구조가 보이면 입력 크기를 보고 위험한지 먼저 판단해야 한다.

### 3. 정렬의 힘을 기억하자
접두사/사전순/인접 비교는 자주 묶여 나온다.  
문자열 문제에서 정렬 후 인접 비교는 매우 강력한 패턴이다.

### 4. Trie는 prefix 문제 전용 무기다
자동완성, 접두사 판정, 문자열 집합 탐색 문제에서는 Trie를 떠올리면 좋다.

---

## 내가 이 문제에서 잘못 생각한 점

- 문자열 문제를 굳이 숫자 리스트로 변환해서 더 무겁게 처리했다.
- 모든 쌍을 비교하는 방식으로 접근해서 비효율이 커졌다.
- 접두사 문제의 대표 패턴인 **정렬**과 **Trie**를 먼저 떠올리지 못했다.

---

## 앞으로 비슷한 문제를 만나면

다음 순서로 생각하자.

1. 이게 문자열 자체 비교 문제인가?
2. 접두사 / 사전순 / 인접 비교가 가능한가?
3. 정렬 후 해결 가능한가?
4. prefix 전용 자료구조 Trie가 필요한가?

---

## 한 줄 정리

이 문제의 실전 정답은 보통 **정렬 + 인접 비교**이고,  
자료구조 관점의 정석은 **Trie**다.  
내 코드는 원리 이해에는 좋지만, 실전 성능 면에서는 개선이 필요하다.
