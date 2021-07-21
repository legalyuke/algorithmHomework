package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在选举中，第 i 张票是在时间为 times[i] 时投给 persons[i] 的。
 * <p>
 * 现在，我们想要实现下面的查询函数： TopVotedCandidate.q(int t) 将返回在 t 时刻主导选举的候选人的编号。
 * <p>
 * 在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
 * <p>
 * Solution:
 * 构造一个二维关于时刻单调数组
 * 二次二分查找
 *
 * @author liyuke
 * @date 2021-07-21 21:15
 */
public class TopVotedCandidate {
    class Vote {
        int person, time;

        public Vote(int p, int t) {
            person = p;
            time = t;
        }
    }

    private List<List<Vote>> voteList;

    public TopVotedCandidate(int[] persons, int[] times) {
        Map<Integer, Integer> countMap = new HashMap<>();
        voteList = new ArrayList<>();
        int n = persons.length;
        for (int i = 0; i < n; i++) {
            countMap.merge(persons[i], 1, Integer::sum);
            int c = countMap.get(persons[i]);
            int k = c;
            while (voteList.size() < c) {
                voteList.add(new ArrayList<Vote>());
                c--;
            }
            voteList.get(k - 1).add(new Vote(persons[i], times[i]));
        }


    }

    public int q(int t) {
        int l = 0;
        int r = voteList.size() - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (voteList.get(mid).get(0).time <= t) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        int m = l;

        int h = voteList.get(m).size() - 1;
        int d = 0;
        while (d < h) {
            int z = (d + h + 1) / 2;
            if (voteList.get(m).get(z).time <= t) {
                d = z;
            } else {
                h = z - 1;
            }
        }
        return voteList.get(m).get(d).person;

    }
}
