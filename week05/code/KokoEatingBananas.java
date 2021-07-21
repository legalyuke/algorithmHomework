package code;

/**
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 * <p>
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 * <p>
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * <p>
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 * <p>
 * Solution:
 * 二分查找（K 在 取值范围内，关于是否能吃完所有香蕉条件有序）
 *
 * @author liyuke
 * @date 2021-07-21 22:19
 */
public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        long sum = 0;
        int max = 0;
        for (int p : piles) {
            sum += p;
            max = Math.max(max, p);
        }
        //K的左边界
        int l = Math.max((int) (sum / h), 1);
        //K的右边界
        int r = max;
        int mid = -1;
        //二分查找
        while (l < r) {
            mid = (l + r) / 2;
            if (isEatAll(piles, h, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;

    }

    /**
     * 判断是否能吃完所有香蕉
     *
     * @param piles
     * @param h
     * @param k
     * @return
     */
    private boolean isEatAll(int[] piles, int h, int k) {
        for (int i = 0; i < piles.length; i++) {
            int c = piles[i] / k;
            if (piles[i] % k != 0) {
                c++;
            }
            h -= c;
            if (h < 0) {
                return false;
            }
        }
        return true;
    }
}
