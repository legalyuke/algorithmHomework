package practice;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * <p>
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 * Solution: dfs+记忆化搜索
 * @author liyuke
 * @date 2021-07-11 15:12
 */
public class LongestIncreasingPathInMatrix {

    /**
     * 方向偏移数组
     */
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};
    /**
     * 结果记忆矩阵
     */
    private int[][] answer;
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        int ans = 0;
        answer = new int[m][n];
        //遍历所有顶点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, howFar(matrix, i, j));
            }
        }
        return ans;
    }

    private int howFar(int[][] matrix, int x, int y) {
        if (answer[x][y] != 0) {
            return answer[x][y];
        }
        answer[x][y] = 1;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n || matrix[nx][ny] <= matrix[x][y]) {
                continue;
            }
            answer[x][y] = Math.max(answer[x][y], howFar(matrix, nx, ny) + 1);
        }
        return answer[x][y];
    }
}
