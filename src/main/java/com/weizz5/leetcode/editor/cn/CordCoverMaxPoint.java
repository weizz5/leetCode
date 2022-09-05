package com.weizz5.leetcode.editor.cn;

import com.alibaba.fastjson.JSON;
import com.weizz5.leetcode.editor.cn.util.ArrayUtils;

import java.util.Arrays;

/**
 * 给定一个有序数组arr，代表坐落在X轴上的点，给定一个正数K，代表绳子的长度，返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 *
 * @author weizz5
 * @date 2022/09/05
 */
public class CordCoverMaxPoint {

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = ArrayUtils.generateArray(len, 0, 1000);
            Arrays.sort(arr);
            int ans = test(arr, L);
            int ans1 = maxPoint1(arr, L);
            int ans2 = maxPoint2(arr, L);
//            int ans2 = maxPoint2(arr, L);
            if (ans2 != ans) {
                System.out.println("oops!");
                System.out.println("L:"+L);
                System.out.println("ans:"+ans);
                System.out.println("ans2:"+ans2);
                System.out.println("arr:"+ JSON.toJSONString(arr));
                break;
            }
        }

    }

    /**
     * 双重for循环
     *
     * @param arr
     * @param L
     * @return
     */
    public static int maxPoint1(int[] arr, int L) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }

        int length = arr.length;
        int res = 1;
        for (int i = 0; i < length; i++) {
            int next = i + 1;
            while (next < length && arr[next] - arr[i] <= L) {
                next++;
            }
            res = Math.max(res, next - i);
        }
        return res;
    }

    /**
     * 贪心+二分
     *
     * @param arr
     * @param L
     * @return
     */
    public static int maxPoint2(int[] arr, int L) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int length = arr.length;
        int res = 1;
        for (int i = 0; i < length; i++) {
            int index = nearestIndex(arr, arr[i] + L, i, length-1);
            res = Math.max(res, index - i + 1);
        }

        return res;
    }

    public static int nearestIndex1(int[] arr, int val, int left, int right) {
        int index = left;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (val < arr[mid]) {
                right = mid - 1;
            } else {
                index = mid;
                left = mid + 1;
            }
        }
        return index;
    }

    public static int nearestIndex(int[] arr, int val, int left, int right) {
        if (right < left) {
            return left-1;
        }

        int mid = left + ((right - left) >> 1);
        if (val < arr[mid]) {
            return nearestIndex(arr, val, left, mid - 1);
        } else {
            return nearestIndex(arr, val, mid + 1, right);
        }
    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

}
