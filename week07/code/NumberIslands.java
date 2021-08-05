package code;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Solution:
 * 并查集
 *
 * @author liyuke
 * @date 2021-08-05 10:29
 */
public class NumberIslands {
    private int[] dx = new int[]{1, -1, 0, 0};
    private int[] dy = new int[]{0, 0, 1, -1};

    class UnionSet {
        int count;
        int[] fa;

        public UnionSet(char[][] grid) {
            int nx = grid.length;
            int ny = grid[0].length;
            fa = new int[nx * ny];
            for (int i = 0; i < nx; i++) {
                for (int j = 0; j < ny; j++) {
                    fa[i * ny + j] = i * ny + j;
                    if (grid[i][j] == '1') {
                        count++;
                    }
                }
            }
        }

        void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x > y) {
                fa[x] = y;
                count--;
            } else if (x < y) {
                fa[y] = x;
                count--;
            }
        }

        int find(int z) {
            if (z == fa[z]) {
                return z;
            }
            return fa[z] = find(fa[z]);
        }
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UnionSet f = new UnionSet(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 4; k++) {
                        int nx = dx[k] + i;
                        int ny = dy[k] + j;
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1') {
                            f.union(i * n + j, nx * n + ny);
                        }
                    }
                }
            }
        }

        return f.count;
    }
}
