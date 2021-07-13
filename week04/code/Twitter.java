package code;

import java.util.*;

/**
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 * <p>
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 *
 * @author liyuke
 * @date 2021-07-11 22:37
 */
public class Twitter {

    // 用于保存用户信息内部类
    private class Node {
        Set<Integer> follow;
        LinkedList<Integer> posted;

        public Node() {
            follow = new HashSet<>();
            posted = new LinkedList<>();
        }

    }

    // 推特限制条目数与全局时间计数
    private int limitedCount, time;
    private Map<Integer, Node> userMap;
    private Map<Integer, Integer> postedTimeMap;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        time = 0;
        limitedCount = 10;
        userMap = new HashMap<>();
        postedTimeMap = new HashMap<>();

    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        //如果用户不存在，初始化用户
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new Node());
        }
        //推特已满，移除最后一个
        if (userMap.get(userId).posted.size() == limitedCount) {
            userMap.get(userId).posted.remove(limitedCount - 1);
        }
        //存放推特信息
        userMap.get(userId).posted.addFirst(tweetId);
        postedTimeMap.put(tweetId, ++time);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        if (userMap.get(userId) == null) {
            return new ArrayList<>();
        }
        if (userMap.get(userId).follow.isEmpty()) {
            return new ArrayList<>(userMap.get(userId).posted);
        }
        // 合并K个链表（始终限制链表长度为limitedCount）
        LinkedList<Integer> ans = new LinkedList<>(userMap.get(userId).posted);
        for (int followId : userMap.get(userId).follow) {
            // 防止用户关注自己
            if (followId == userId) {
                continue;
            }
            int tweetSize = userMap.get(followId).posted.size();
            LinkedList<Integer> res = new LinkedList<>();
            int i = 0;
            int j = 0;
            int cur = -1;
            Iterator<Integer> it = userMap.get(followId).posted.iterator();
            if (j < tweetSize) {
                cur = it.next();
                while (i < ans.size() && j < tweetSize && res.size() < limitedCount) {
                    if (postedTimeMap.get(cur) > postedTimeMap.get(ans.get(i))) {
                        res.addLast(cur);
                        ++j;
                        if (it.hasNext()) {
                            cur = it.next();
                        }
                    } else {
                        res.addLast(ans.get(i));
                        ++i;
                    }
                }
            }
            for (; i < ans.size() && res.size() < limitedCount; i++) {
                res.addLast(ans.get(i));
            }
            for (; j < tweetSize && res.size() < limitedCount; j++) {
                res.addLast(cur);
                if (it.hasNext()) {
                    cur = it.next();
                }
            }
            ans = new LinkedList<>(res);
        }
        return ans;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new Node());
        }
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new Node());
        }
        userMap.get(followerId).follow.add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.get(followerId) != null) {
            userMap.get(followerId).follow.remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */