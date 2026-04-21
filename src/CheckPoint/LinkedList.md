# LinkedList 개념 정리

## 1. 기본 구조

### Node
LinkedList의 기본 단위. 값(value)과 다음 노드를 가리키는 링크(link)로 구성된다.

```java
static class Node {
    int value;
    Node link;

    Node() {}
    Node(int value) {
        this.value = value;
        this.link = null;
    }
}
```

### Dummy Head
head를 실제 데이터 노드가 아닌 빈 노드로 초기화하는 패턴.
head가 null인 상태에서 `head.link`를 참조하면 NullPointerException이 발생하기 때문에 dummy head를 사용해 이를 방지한다.

```java
LinkedList() {
    head = new Node(); // dummy node — 실제 데이터 없음
    size = 0;
}
```

---

## 2. 단일 연결 리스트 (Singly LinkedList)

각 노드가 다음 노드만 가리키는 구조.

```
head(dummy) → [1] → [2] → [3] → null
```

### 구현

```java
static class LinkedList {
    Node head;
    int size;

    LinkedList() {
        head = new Node();
        size = 0;
    }

    // 맨 앞에 삽입
    void addFirst(int value) {
        Node fir = new Node(value);
        fir.link = head.link;
        head.link = fir;
        size++;
    }

    // 맨 뒤에 삽입
    void add(int value) {
        Node last = new Node(value);
        Node cur = head;
        while (cur.link != null) cur = cur.link;
        cur.link = last;
        size++;
    }

    // index 위치에 삽입 (0-based)
    void add(int index, int value) {
        if (size < index) {
            add(value);
            return;
        }
        Node mid = new Node(value);
        Node cur = head;
        for (int i = 0; i < index; i++) cur = cur.link;

        mid.link = cur.link;
        cur.link = mid;
        size++;
    }

    // 삭제 — 이전 노드를 같이 추적해야 함
    void removeByValue(int value) {
        Node prev = head;
        Node cur = head.link;
        while (cur != null) {
            if (cur.value == value) {
                prev.link = cur.link; // prev 없이는 불가능
                size--;
                return;
            }
            prev = cur;
            cur = cur.link;
        }
    }
}
```

### 단일 연결 리스트의 한계
- **삭제 시 이전 노드 필요**: 삭제할 노드를 직접 참조하고 있어도, 이전 노드의 link를 수정해야 하므로 prev를 항상 추적해야 한다.
- **역방향 탐색 불가**: 앞에서 뒤로만 순회할 수 있다.
- **tail 삭제 O(n)**: tail의 이전 노드를 모르기 때문에 head부터 다시 순회해야 한다.

---

## 3. 이중 연결 리스트 (Doubly LinkedList)

각 노드가 이전 노드(prelink)와 다음 노드(postlink)를 모두 가리키는 구조.

```
head(dummy) ⇄ [1] ⇄ [2] ⇄ [3] ← tail
```

### 구현

```java
static class DoublyLinkedList {
    Node head;
    Node tail;
    int size;

    DoublyLinkedList() {
        head = new Node();
        tail = head;
        size = 0;
    }

    // 맨 뒤에 삽입 — tail 덕분에 O(1)
    void add(int value) {
        Node node = new Node(value);
        tail.postlink = node;
        node.prelink = tail;
        tail = node;
        size++;
    }

    // index 위치에 삽입 — 양방향 탐색으로 최대 n/2만 순회
    void add(int index, int value) {
        if (size < index) {
            add(value);
            return;
        }
        Node node = new Node(value);
        Node cur;

        if (index <= size / 2) {
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.postlink;
        } else {
            cur = tail;
            for (int i = size; i > index; i--) cur = cur.prelink;
        }

        node.prelink = cur.prelink;
        node.postlink = cur;
        if (cur.prelink != null) cur.prelink.postlink = node;
        cur.prelink = node;
        size++;
    }

    // 노드 하나만으로 삭제 — prev 추적 불필요
    void remove(Node node) {
        if (node.prelink != null) node.prelink.postlink = node.postlink;
        if (node.postlink != null) node.postlink.prelink = node.prelink;
        if (node == tail) tail = node.prelink;
        size--;
    }

    // 값으로 삭제
    void removeByValue(int value) {
        Node cur = head.postlink;
        while (cur != null) {
            if (cur.value == value) {
                remove(cur); // 찾은 노드만 넘기면 끝
                return;
            }
            cur = cur.postlink;
        }
    }

    // index로 삭제 — 양방향 탐색
    void removeByIndex(int index) {
        if (index >= size) return;
        Node cur;

        if (index <= size / 2) {
            cur = head.postlink;
            for (int i = 0; i < index; i++) cur = cur.postlink;
        } else {
            cur = tail;
            for (int i = size - 1; i > index; i--) cur = cur.prelink;
        }
        remove(cur);
    }
}
```

---

## 4. 단일 vs 이중 비교

### 연산 복잡도

| 연산 | 단일 연결 리스트 | 이중 연결 리스트 |
|------|--------------|--------------|
| 맨 앞 삽입 | O(1) | O(1) |
| 맨 뒤 삽입 | O(n) / tail 있으면 O(1) | O(1) |
| 중간 삽입 | O(n) | O(n/2) |
| **tail 삭제** | **O(n)** | **O(1)** |
| 값으로 삭제 | O(n) — prev 추적 필요 | O(n) — prev 추적 불필요 |
| 역방향 탐색 | 불가 | O(n) |

### 이중 연결이 유리한 상황
1. **삭제 편의성**: 삭제할 노드를 직접 참조하고 있으면 `remove(node)` 한 번으로 끝. 단일은 항상 이전 노드를 같이 추적해야 한다.
2. **tail 삭제 O(1)**: `tail.prelink`로 바로 이전 노드에 접근 가능하므로 head부터 순회할 필요가 없다.
3. **덱(Deque) 구현**: head, tail 양쪽에서 O(1) 삽입/삭제가 가능해 단일로는 구현하기 어려운 덱을 자연스럽게 구현할 수 있다.

### 이중 연결의 trade-off
- **메모리**: 노드 하나당 링크를 2개 저장하므로 단일 대비 메모리 사용량이 2배
- **연산 상수**: 삽입/삭제 시 링크를 2개 관리하지만, O 표기법에서 상수는 무시하므로 복잡도 자체는 동일

```
단일: O(n)
이중: O(2n) → O(n)  // 상수 무시
```

### 적합한 자료구조

| 자료구조 | 적합한 LinkedList |
|---------|----------------|
| 스택 (Stack) | 단일 연결 리스트 |
| 큐 (Queue) | 단일 연결 리스트 |
| **덱 (Deque)** | **이중 연결 리스트** |
| 브라우저 히스토리 | 이중 연결 리스트 |
| LRU 캐시 | 이중 연결 리스트 |

---

## 5. 흔한 실수 정리

| 실수 | 원인 | 해결 |
|------|------|------|
| NullPointerException | head가 null인 상태에서 `head.link` 참조 | dummy head 사용 |
| size 미갱신 | add/remove 후 size++ / size-- 누락 | 모든 삽입/삭제에 size 갱신 |
| 단방향 링크만 수정 | 이중 연결에서 한쪽 링크만 연결 | prelink, postlink 양쪽 모두 갱신 |
| tail 미갱신 | tail 노드 삭제 후 tail 포인터 방치 | `if (node == tail) tail = node.prelink` |
| index 범위 조건 오류 | `size <= index` 로 유효한 케이스 누락 | `size < index` 로 수정 |