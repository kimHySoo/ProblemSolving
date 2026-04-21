package DataStructure;

public class LinkedList {
    static class Node {
        int value;
        Node prelink;
        Node postlink;

        Node() {}

        Node(int value) {
            this.value = value;
            this.prelink = null;
            this.postlink = null;
        }
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        DoublyLinkedList() {
            head = new Node();
            tail = head;
            size = 0;
        }

        void add(int value) {
            Node node = new Node(value);
            tail.postlink = node;
            node.prelink = tail;
            tail = node;
            size++;
        }

        void add(int index, int value) {
            if (size < index) {
                add(value);
                return;
            }
            Node node = new Node(value);
            Node cur;

            // 양방향 탐색
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

        // 노드 하나만으로 삭제
        void remove(Node node) {
            if (node.prelink != null) node.prelink.postlink = node.postlink;
            if (node.postlink != null) node.postlink.prelink = node.prelink;
            if (node == tail) tail = node.prelink; // tail 갱신
            size--;
        }

        // 값으로 삭제 — prev 추적 없이 바로 삭제
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

        // index로 삭제
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
}