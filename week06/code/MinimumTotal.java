package code;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * <p>
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * Solution:
 * 动态规划
 *
 * @author liyuke
 * @date 2021-07-26 22:32
 */
public class MinimumTotal {

    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> ans = new ArrayList<>();
        ans.add(triangle.get(0).get(0));
        for (int i = 1; i < triangle.size(); i++) {
            int n = triangle.get(i).size();
            ans.add(ans.get(ans.size() - 1) + triangle.get(i).get(n - 1));
            for (int j = n - 2; j > 0; j--) {
                ans.set(j, triangle.get(i).get(j) + Math.min(ans.get(j - 1), ans.get(j)));
            }
            ans.set(0, ans.get(0) + triangle.get(i).get(0));
        }
        int result = Integer.MAX_VALUE;
        for (Integer t : ans) {
            result = Math.min(result, t);
        }
        return result;

    }

}
