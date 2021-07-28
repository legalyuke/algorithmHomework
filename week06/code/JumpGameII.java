package code;

import java.util.Arrays;

/**
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * Solution:
 * 动态规划
 * 与使用贪心算法相比：
 * 1.贪心算法只根据局部最优访问了最优路径，动态规划遍历了所有的不重复可能路径
 * 2.贪心算法从前往后多看一步，动态规划，每次从后往前看一步
 *
 * @author liyuke
 * @date 2021-07-28 10:59
 */
public class JumpGameII {

    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] + j >= i) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[n - 1];


    }
}
