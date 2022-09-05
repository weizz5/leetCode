package com.weizz5.leetcode.editor.cn.util;

import com.alibaba.fastjson.JSON;

/**
 * This is Description
 *
 * @author weizz5
 * @date 2022/09/05
 */
public class ArrayUtils {

    public static int[] generateArray(int size, int min, int max){
        if(size == 0){
            return new int[0];
        }
        int[] array = new int[size];
        int length = max - min;
        int index =0;
        while (index < size){
            int val = (int) (min + Math.random() * length);
            array[index] = val;
            index++;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] randomArray = generateArray(10, 0, 50);
        System.out.println(JSON.toJSON(randomArray));
    }

}
