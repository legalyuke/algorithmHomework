package practice;

import java.util.*;

/**
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 * <p>
 * Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in the gene string.
 * <p>
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 * <p>
 * Given the two gene strings start and end and the gene bank bank, return the minimum number of mutations needed to mutate from start to end. If there is no such a mutation, return -1.
 * <p>
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 * Solution: bfs
 *
 * @author liyuke
 * @date 2021-07-11 11:34
 */
public class MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        char[] gene = new char[]{'A', 'C', 'G', 'T'};
        Map<String, Integer> depth = new HashMap<>();
        Queue<String> geneticQueue = new LinkedList<>();
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        bankSet.add(start);
        geneticQueue.offer(start);
        depth.put(start, 0);
        while (!geneticQueue.isEmpty()) {
            String s = geneticQueue.poll();
            if (!bankSet.contains(s)) {
                continue;
            }
            if (s.equals(end)) {
                return depth.get(s);
            }
            //一共3*8种可能
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    char[] c = s.toCharArray();
                    if (gene[j] == s.charAt(i)) {
                        continue;
                    }
                    c[i] = gene[j];
                    String ns = String.valueOf(c);
                    if (depth.containsKey(ns)) {
                        continue;
                    }
                    depth.put(ns, depth.get(s) + 1);
                    geneticQueue.offer(ns);
                }
            }
        }
        return -1;

    }
}
