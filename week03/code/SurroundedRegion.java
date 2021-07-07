package code;

/**
 * Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Solution: dfs
 * @author liyuke
 * @date 2021-07-07 23:48
 */
public class SurroundedRegion {

    private int m, n;

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        //从边缘开始搜索所有的非围绕O
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }
        for (int j = 1; j < n - 1; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }
        //清理O，擦除标记
        for (int k = 0; k < m; k++) {
            for (int z = 0; z < n; z++) {
                if (board[k][z] == 'A') {
                    board[k][z] = 'O';
                } else if (board[k][z] == 'O') {
                    board[k][z] = 'X';
                }
            }
        }


    }

    private void dfs(char[][] board, int x, int y) {
        //终止条件,此处判断条件不能使用X代替
        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        //坐标遍历，上下左右移动
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }


}
