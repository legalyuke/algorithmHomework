package code;

import java.util.ArrayList;
import java.util.List;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 *
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * @author liyuke
 * @date 2021-07-05 16:06
 */
public class CourseScheduleII {
    /**
     * 出边数组
     */
    private List<List<Integer>> list;
    /**
     * 结果数组
     */
    private int[] ans;
    /**
     * 状态数组： 0-未搜索 1-搜索中 2-搜索已完成
     */
    private int[] visited;
    /**
     * 是否有环标志位
     */
    private boolean flag = true;
    /**
     * 结果数组索引
     */
    private int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ans = new int[numCourses];
        visited = new int[numCourses];
        index = numCourses - 1;
        list = new ArrayList<>();
        //构造出边数组
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            list.get(prerequisite[1]).add(prerequisite[0]);
        }
        //遍历所有边
        for (int k = 0; k < numCourses && flag; k++) {
            if (visited[k] == 0) {
                dfs(k);
            }
        }
        if (!flag) {
            return new int[0];
        }
        return ans;
    }

    private void dfs(int root) {
        visited[root] = 1;
        for (int v : list.get(root)) {
            if (visited[v] == 0) {
                if (!flag) {
                    return;
                }
                dfs(v);
            } else if (visited[v] == 1) {
                flag = false;
                return;
            }
        }
        visited[root] = 2;
        ans[index--] = root;
    }
}
