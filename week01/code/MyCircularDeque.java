package code;

/**
 * 设计实现双端队列。
 * 你的实现需要支持以下操作：
 * <p>
 * code.MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 * 实现方法1：
 * 使用双向链表实现
 * 实现方法2：
 * 使用数组实现
 *
 * @author liyuke
 * @date 2021-06-20 11:58
 */
public class MyCircularDeque {

    class ListNode {
        int val;
        ListNode next;
        ListNode pre;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode pre, ListNode next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    //方法1 用双向链表实现
    class LinkedListImpl {

        int size;
        int used;
        ListNode head;
        ListNode tail;

        /**
         * Initialize your data structure here. Set the size of the deque to be k.
         */
        public LinkedListImpl(int k) {
            size = k;
        }

        /**
         * Adds an item at the front of Deque. Return true if the operation is successful.
         */
        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            ListNode cur = new ListNode(value);
            if (isEmpty()) {
                head = cur;
                tail = cur;
            } else {
                head.pre = cur;
                cur.next = head;
                head = cur;
            }
            used++;
            return true;
        }

        /**
         * Adds an item at the rear of Deque. Return true if the operation is successful.
         */
        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            ListNode cur = new ListNode(value);
            if (isEmpty()) {
                head = cur;
                tail = cur;
            } else {
                cur.pre = tail;
                tail.next = cur;
                tail = cur;
            }
            used++;
            return true;
        }

        /**
         * Deletes an item from the front of Deque. Return true if the operation is successful.
         */
        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.pre = null;
            }
            used--;
            return true;
        }

        /**
         * Deletes an item from the rear of Deque. Return true if the operation is successful.
         */
        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.pre;
                tail.next = null;
            }
            used--;
            return true;
        }

        /**
         * Get the front item from the deque.
         */
        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return head.val;
        }

        /**
         * Get the last item from the deque.
         */
        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return tail.val;
        }

        /**
         * Checks whether the circular deque is empty or not.
         */
        public boolean isEmpty() {
            return used == 0;
        }

        /**
         * Checks whether the circular deque is full or not.
         */
        public boolean isFull() {
            return used == size;
        }
    }

    //方法2 用数组实现
    class ArrayImpl {

        int size;
        int used;
        int[] value;

        /**
         * Initialize your data structure here. Set the size of the deque to be k.
         */
        public ArrayImpl(int k) {
            size = k;
            value = new int[size];
        }

        /**
         * Adds an item at the front of Deque. Return true if the operation is successful.
         */
        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            for (int i = used; i > 0; i--) {
                this.value[i] = this.value[i - 1];
            }
            this.value[0] = value;
            used++;
            return true;
        }

        /**
         * Adds an item at the rear of Deque. Return true if the operation is successful.
         */
        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            this.value[used] = value;
            used++;
            return true;
        }

        /**
         * Deletes an item from the front of Deque. Return true if the operation is successful.
         */
        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            for (int i = 0; i < used - 1; i++) {
                value[i] = value[i + 1];
            }
            value[used - 1] = 0;
            used--;
            return true;
        }

        /**
         * Deletes an item from the rear of Deque. Return true if the operation is successful.
         */
        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            value[used - 1] = 0;
            used--;
            return true;
        }

        /**
         * Get the front item from the deque.
         */
        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return value[0];
        }

        /**
         * Get the last item from the deque.
         */
        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return value[used - 1];
        }

        /**
         * Checks whether the circular deque is empty or not.
         */
        public boolean isEmpty() {
            return used == 0;
        }

        /**
         * Checks whether the circular deque is full or not.
         */
        public boolean isFull() {
            return used == size;
        }
    }
}
