package code;

import java.util.TreeSet;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 *
 * @author liyuke
 * @date 2021-08-18 20:46
 * Solution:
 * TreeSet-----红黑树
 */
public class MaximumWindow {
    class Node implements Comparable<Node> {

        int index;
        int value;

        public Node() {
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return o.value == value ? index - o.index : value - o.value;
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        Node[] array = new Node[nums.length];
        for (int z = 0; z < nums.length; z++) {
            array[z] = new Node(z, nums[z]);
        }
        int[] ans = new int[nums.length - k + 1];
        TreeSet<Node> treeSet = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            treeSet.add(array[i]);
        }
        ans[0] = treeSet.last().value;
        for (int j = k; j < nums.length; j++) {
            treeSet.remove(array[j - k]);
            treeSet.add(array[j]);
            ans[j - k + 1] = treeSet.last().value;
        }
        return ans;
    }
}
