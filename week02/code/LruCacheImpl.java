package code;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * @author liyuke
 * @date 2021-06-27 16:21
 */

public class LruCacheImpl {
    // 双向链表+哈希表实现
    class LRUCache {

        ListNode head;
        ListNode tail;
        int capacity;
        Map<Integer, ListNode> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            head = new ListNode();
            tail = new ListNode();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            if (map != null && map.containsKey(key)) {
                moveTohead(map.get(key));
                return map.get(key).val;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            ListNode node = new ListNode(key, value);
            if (map.containsKey(key)) {
                map.get(key).val = value;
                moveTohead(map.get(key));
            } else if (map.size() == capacity) {
                map.remove(tail.pre.key);
                map.put(key, node);
                deleteTail();
                insertHead(node);
            } else {
                map.put(key, node);
                insertHead(node);
            }

        }

        private void moveTohead(ListNode node) {
            deleteNode(node);
            insertHead(node);
        }

        private void deleteNode(ListNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void insertHead(ListNode node) {
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        private void deleteTail() {
            tail.pre.pre.next = tail;
            tail.pre = tail.pre.pre;
        }
    }

    class ListNode {
        ListNode pre;
        ListNode next;
        int key;
        int val;

        public ListNode() {
        }

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public ListNode(int val, ListNode pre, ListNode next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
