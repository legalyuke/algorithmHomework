package practice;

import java.util.*;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 * Solution:dfs 产生一个全排列
 *
 * @author liyuke
 * @date 2021-07-11 20:22
 */
public class NQueens {
    private boolean[] used;
    private Map<Integer, Boolean> rowAddCol = new HashMap<>();
    private Map<Integer, Boolean> rowSubtractCol = new HashMap<>();
    private List<List<Integer>> ans;
    private List<Integer> temp;
    private int n;

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        used = new boolean[n];
        Arrays.fill(used, false);
        ans = new ArrayList<>();
        temp = new ArrayList<>();
        this.n = n;
        find(0);
        for (List<Integer> list : ans) {
            List<String> l = new ArrayList<>();
            for (Integer i : list) {
                char[] c = new char[n];
                Arrays.fill(c, '.');
                c[i] = 'Q';
                String s = new String(c);
                l.add(s);
            }
            result.add(l);
        }
        return result;
    }

    private void find(int row) {
        if (row == n) {
            List<Integer> copyList = new ArrayList<>(temp);
            ans.add(copyList);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!used[col] && !rowAddCol.containsKey(row + col) && !rowSubtractCol.containsKey(row - col)) {
                used[col] = true;
                rowAddCol.put(row + col, true);
                rowSubtractCol.put(row - col, true);
                temp.add(col);
                find(row + 1);
                temp.remove(row);
                used[col] = false;
                rowAddCol.remove(row + col);
                rowSubtractCol.remove(row - col);

            }
        }
    }
}
