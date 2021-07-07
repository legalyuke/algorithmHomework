package code;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * @author liyuke
 * @date 2021-07-04 19:01
 */
public class CreateBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int[] inorder;
    private int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        return bulid(0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode bulid(int l1, int r1, int l2, int r2) {

        if (l2 > r2 || l1 > r1) {
            return null;
        }
        TreeNode node = new TreeNode(postorder[r2]);
        int mid = l1;
        while (node.val != inorder[mid]) mid++;
        int leftSize = mid - l1 - 1;
        int rightSize = r1 - mid;
        node.left = bulid(l1, l1 + leftSize, l2, l2 + leftSize);
        node.right = bulid(r1 - rightSize + 1, r1, r2 - rightSize, r2 - 1);
        return node;
    }
}
