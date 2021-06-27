package code;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * @author liyuke
 * @date 2021-06-27 19:21
 *
 * 利用Hash表实现
 */
public class ArrayDegree {
    class Solution {
        public int findShortestSubArray(int[] nums) {
            int degree = 1;
            int ans = nums.length;
            Map<Integer, SubInfo> map = new HashMap<>();
            for (int x = 0; x < nums.length; x++) {
                if (map.containsKey(nums[x])) {
                    map.get(nums[x]).degree++;
                    map.get(nums[x]).last = x;
                    degree = Math.max(map.get(nums[x]).degree, degree);
                } else {
                    SubInfo subInfo = new SubInfo(1, x);
                    map.put(nums[x], subInfo);
                }
            }
            if (degree == 1) {
                return 1;
            }

            for (SubInfo s : map.values()) {
                if (s.degree == degree) {
                    ans = Math.min(s.last - s.first + 1, ans);
                }
            }
            return ans;

        }
    }

    class SubInfo {
        int degree;
        int first;
        int last;

        public SubInfo() {

        }

        public SubInfo(int degree, int first) {
            this.degree = degree;
            this.first = first;
        }

        @Override
        public String toString() {
            return "degree:" + degree
                    + "first:" + first
                    + "last" + last;
        }
    }

}
