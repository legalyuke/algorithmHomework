package code;

import java.util.Arrays;

/**
 * 给定一个未排序的整数数组，找到最长递增子序列的个数
 * <p>
 * Solution:
 * 动态规划
 *
 * @author liyuke
 * @date 2021-07-28 9:20
 */
public class LIS {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }
        int[] count = new int[n];
        int[] lengths = new int[n];
        Arrays.fill(count, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (lengths[j] >= lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        count[i] = count[j];
                    } else if (lengths[i] == lengths[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
        }
        int longest = 0;
        int ans = 0;
        for (int t : lengths) {
            longest = Math.max(longest, t);
        }
        for (int z = 0; z < n; z++) {
            if (lengths[z] == longest) {
                ans += count[z];
            }
        }
        return ans;


    }
}
