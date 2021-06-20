package code;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * <p>
 *
 * @author liyuke
 * @date 2021-06-20 14:21
 */
public class SumEqualK {
    //解法1 前缀和+暴力枚举
    public int subarraySum1(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        for (int j = 0; j < n; j++) {
            for (int m = j + 1; m <= n; m++) {
                if (sum[m] - sum[j] == k) {
                    ans++;
                }
            }
        }
        return ans;

    }

    //解法2 前缀和+哈希表
    public int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int pre = 0;
        map.put(pre, 1);
        for (int i = 0; i < n; i++) {
            pre += nums[i];
            int temp = pre - k;
            if (map.containsKey(temp)) {
                ans += map.get(temp);
            }
            map.merge(pre, 1, Integer::sum);
        }
        return ans;

    }
}
