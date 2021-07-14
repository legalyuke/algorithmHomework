package code;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
 * <p>
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * <p>
 * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 * <p>
 * You must decrease the overall operation steps as much as possible.
 * <p>
 * Solution: 二分查找
 *
 * @author liyuke
 * @date 2021-07-14 23:32
 */
public class FindMin {
    public int findMin(int[] nums) {
        int target = nums[0];
        int left = 0;
        int right = nums.length - 1;
        int mid = -1;
        while (left != right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                if (nums[right] == target) {
                    right -= 1;
                } else {
                    //局部有序，左边直接全部跳过
                    left = mid + 1;
                }
                continue;
            }
            if (nums[mid] <= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return Math.min(nums[left], target);
    }
}
