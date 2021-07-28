package code;

/**
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标。
 * <p>
 * Solution:
 * 动态规划
 *
 * @author liyuke
 * @date 2021-07-28 10:10
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] + j >= i && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n - 1];
    }
}
