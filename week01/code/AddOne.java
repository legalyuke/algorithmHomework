package code;

/**
 * +1
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 示例 ：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 *
 * @author liyuke
 * @date 2021-06-20 10:22
 */
public class AddOne {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] += 1;
            if (digits[i] == 10) {
                digits[i] = 0;
            } else {
                break;
            }
        }
        if (digits[0] != 0) {
            return digits;
        } else {
            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            for (int i = 1; i <= digits.length; i++) {
                ans[i] = digits[i - 1];
            }
            return ans;
        }

    }
}
