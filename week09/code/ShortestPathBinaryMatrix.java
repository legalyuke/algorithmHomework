package code;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 * <p>
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * <p>
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 * <p>
 * Solution:
 * 双向BFS
 *
 * @author liyuke
 * @date 2021-08-17 23:27
 */
public class ShortestPathBinaryMatrix {
    /**
     * 方向数组---可向八个方向移动
     */
    private int[] dx = {1, 0, 1, 1, -1, -1, 0, -1};
    private int[] dy = {1, 1, 0, -1, 1, 0, -1, -1};
    private int ans = 2;
    private int n;

    public int shortestPathBinaryMatrix(int[][] grid) {
        n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        boolean[][] visitedS = new boolean[n][n];
        boolean[][] visitedE = new boolean[n][n];
        visitedS[0][0] = true;
        visitedE[n - 1][n - 1] = true;
        Queue<int[]> q1 = new LinkedList<>();
        Queue<int[]> q2 = new LinkedList<>();
        q1.offer(new int[]{0, 0});
        q2.offer(new int[]{n - 1, n - 1});
        while (!q1.isEmpty() && !q2.isEmpty()) {
            boolean res = expand(q1, grid, visitedS, visitedE);
            if (res) {
                return ans;
            }
            res = expand(q2, grid, visitedE, visitedS);
            if (res) {
                return ans;
            }
        }
        return -1;
    }

    private boolean expand(Queue<int[]> queue, int[][] grid, boolean[][] visited, boolean[][] visitedOther) {
        if (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int[] cur = queue.poll();
                for (int i = 0; i < 8; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 0 && !visited[nx][ny]) {
                        if (visitedOther[nx][ny]) {
                            return true;
                        }
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            ans++;
        }
        return false;
    }

}
