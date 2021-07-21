package practice;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * You can assume that you can always reach the last index.
 * <p>
 * Solution:贪心算法-向后扩展一步
 *
 * @author liyuke
 * @date 2021-07-19 22:26
 */
public class JumpGameII {

    public int jump(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int cur = 0;
        while (cur < n - 1) {
            int step = 1;
            int max = cur + nums[cur + step] + step;
            int next = cur + step;
            while (step <= nums[cur]) {
                if (cur + step == n - 1) {
                    next = cur + step;
                    break;
                }
                if (cur + nums[cur + step] + step > max) {
                    next = cur + step;
                    max = cur + nums[cur + step] + step;
                }
                step++;
            }
            cur = next;
            ans++;
        }

        return ans;
    }
}
