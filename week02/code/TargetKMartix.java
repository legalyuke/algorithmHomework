package code;

import java.util.HashMap;
import java.util.Map;

/**
 * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 * <p>
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 * <p>
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 *
 * @author liyuke
 * @date 2021-06-27 23:28
 */
public class TargetKMartix {
    // 枚举+前缀和
    class Solution {
        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            int m = matrix.length;
            int n = matrix[0].length;
            int ans = 0;
            // 确定上边界
            for (int i = 0; i < m; i++) {
                int[] sum = new int[n];
                // 下边界，就是一直到最底部
                for (int j = i; j < m; j++) {
                    for (int k = 0; k < n; k++) {
                        sum[k] += matrix[j][k];
                    }
                    //转化为一维优美子数组问题
                    ans += subArraySum(sum, target);
                }
            }
            return ans;

        }

        //和为k的优美子数组
        private int subArraySum(int[] nums, int k) {
            int result = 0;
            Map<Integer, Integer> map = new HashMap<>();
            int n = nums.length;
            int[] sum = new int[n + 1];
            for (int c = 1; c <= n; c++) {
                sum[c] = sum[c - 1] + nums[c - 1];
            }
            int pre = 0;
            map.put(pre, 1);
            for (int x = 1; x <= n; x++) {
                pre = sum[x] - k;
                if (map.containsKey(pre)) {
                    result += map.get(pre);
                }
                //sum[i] - sum[j] = k
                map.merge(sum[x], 1, Integer::sum);
            }
            return result;
        }


    }
}
