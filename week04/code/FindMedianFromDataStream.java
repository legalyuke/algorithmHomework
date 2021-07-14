package code;

import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
 * <p>
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * <p>
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 * <p>
 * Solution: 对顶堆法：将小于中位数的值存在大顶堆，大于中位数的值存在小顶堆
 *
 * @author liyuke
 * @date 2021-07-14 21:16
 */
public class FindMedianFromDataStream {

    PriorityQueue<Integer> bigHeap;
    PriorityQueue<Integer> smallHeap;

    /**
     * initialize your data structure here.
     */
    public FindMedianFromDataStream() {
        bigHeap = new PriorityQueue<>((x1, x2) -> x2 - x1);
        smallHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (smallHeap.isEmpty() || smallHeap.peek() < num) {
            smallHeap.add(num);
        } else {
            bigHeap.add(num);
        }
        if (smallHeap.size() - bigHeap.size() > 1) {
            bigHeap.add(smallHeap.poll());
        }
        if (bigHeap.size() - smallHeap.size() > 1) {
            smallHeap.add(bigHeap.poll());
        }

    }

    public double findMedian() {
        if (bigHeap.isEmpty() && smallHeap.isEmpty()) {
            return 0;
        }
        if ((bigHeap.size() + smallHeap.size()) % 2 == 0) {
            return (bigHeap.peek() + smallHeap.peek()) / 2.0;
        } else if (bigHeap.size() > smallHeap.size()) {
            return bigHeap.peek();
        } else {
            return smallHeap.peek();
        }


    }
}
