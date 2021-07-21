package code;

/**
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 * <p>
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 * <p>
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
 * <p>
 * Solution: 关于条件有序的二分查找
 * 1. 当载重xi在区间[x1,xn]上变化时，若xi满足条件，则区间[xi,xn]内所有x都满足，若xi不满足条件,则区间[x1,xi]内所有x都不满足
 * 2. 因此，xi在区间[x1,xn]上条件有序，可利用二分查找
 *
 * @author liyuke
 * @date 2021-07-19 23:51
 */
public class ShipWithinDays {

    public int shipWithinDaysMethod(int[] weights, int days) {
        int max = 0;
        int r = 0;
        for (int w : weights) {
            max = Math.max(max, w);
            r += w;
        }
        int l = Math.max(max, r / days);
        while (l < r) {
            int mid = (l + r) / 2;
            if (isValid(weights, days, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    /**
     * 判断当前载重是否满足条件
     *
     * @param weights
     * @param days
     * @param limit
     * @return
     */
    private boolean isValid(int[] weights, int days, int limit) {
        int curWeight = 0;
        int curDay = 1;
        for (int x : weights) {
            if (curDay > days) {
                return false;
            }
            if (curWeight + x > limit) {
                curDay++;
                curWeight = x;
            } else {
                curWeight += x;
            }
        }
        return curDay <= days;
    }
}
