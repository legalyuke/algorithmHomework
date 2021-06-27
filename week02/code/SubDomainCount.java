package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个网站域名，如"discuss.leetcode.com"，包含了多个子域名。作为顶级域名，常用的有"com"，下一级则有"leetcode.com"，最低的一级为"discuss.leetcode.com"。当我们访问域名"discuss.leetcode.com"时，也同时访问了其父域名"leetcode.com"以及顶级域名 "com"。
 * <p>
 * 给定一个带访问次数和域名的组合，要求分别计算每个域名被访问的次数。其格式为访问次数+空格+地址，例如："9001 discuss.leetcode.com"。
 * <p>
 * 接下来会给出一组访问次数和域名组合的列表cpdomains 。要求解析出所有域名的访问次数，输出格式和输入格式相同，不限定先后顺序。
 *
 * @author liyuke
 * @date 2021-06-27 18:21
 */

public class SubDomainCount {
    class Solution {
        Map<String, Integer> map = new HashMap<>();

        public List<String> subdomainVisits(String[] cpdomains) {
            List<String> ans = new ArrayList<>();
            for (String s : cpdomains) {
                resolve(s);
            }
            for (Map.Entry entry : map.entrySet()) {
                ans.add(entry.getValue() + " " + entry.getKey());
            }
            return ans;

        }

        private void resolve(String target) {
            int index = target.indexOf(" ");
            Integer times = Integer.valueOf(target.substring(0, index));
            String address = target.substring(index + 1, target.length());
            String[] addressArray = address.split("\\.");
            String temp = addressArray[addressArray.length - 1];
            map.merge(temp, times, Integer::sum);
            for (int k = addressArray.length - 2; k >= 0; k--) {
                temp = new StringBuilder().append(addressArray[k]).append(".").append(temp).toString();
                map.merge(temp, times, Integer::sum);
            }
        }
    }
}
