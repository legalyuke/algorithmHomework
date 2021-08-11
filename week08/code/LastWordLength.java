package code;

/**
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * @author liyuke
 * @date 2021-08-11 22:54
 */
public class LastWordLength {
    public int lengthOfLastWord(String s) {
        int ans = 0;
        int index = s.length() - 1;
        while (index >= 0) {
            if (s.charAt(index) == ' ') {
                index--;
            } else {
                break;
            }
        }
        for (int i = index; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                ans++;
            } else {
                break;
            }

        }
        return ans;

    }
}
