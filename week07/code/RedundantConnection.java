package code;

/**
 * 树可以看成是一个连通且 无环 的 无向 图。
 * <p>
 * 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。
 * <p>
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的边。
 * <p>
 * Solution:
 * 并查集
 *
 * @author liyuke
 * @date 2021-08-04 21:46
 */
public class RedundantConnection {
    private int[] fa;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        fa = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
        }
        for (int[] edge : edges) {
            if (find(edge[0]) == find(edge[1])) {
                return edge;
            }
            unionSet(edge[0], edge[1]);
        }
        return new int[0];
    }

    private int find(int target) {
        if (target == fa[target]) {
            return target;
        }
        return fa[target] = find(fa[target]);
    }

    private void unionSet(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            fa[y] = x;
        }

    }


}
