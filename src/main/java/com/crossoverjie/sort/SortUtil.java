package com.crossoverjie.sort;

/**
 * @author: xiac
 * @date: 2018/7/27
 * @desc: 排序算法。
 */
public class SortUtil {

    /**
     * 冒泡排序：
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     *
     * @param numbers
     */
    public static void bubbleSort(int[] numbers) {
        int size = numbers.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                //交换位置
                swap(numbers, j, j + 1);
            }
        }
    }

    private static void swap(int[] numbers, int j, int i) {
        int temp;
        if (numbers[j] > numbers[j + 1]) {
            temp = numbers[j];
            numbers[j] = numbers[i];
            numbers[i] = temp;
        }
    }

    /**
     * 插入排序
     * 在要排序的一组数中，假设前面(n-1) [n>=2] 个数已经是排好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
     *
     * @param numbers
     */
    public static void insertSort(int[] numbers) {
        //数组长度
        int len = numbers.length;
        //要插入的数
        int insertNum;
        //第一次不用
        for (int i = 1; i < len; i++) {
            insertNum = numbers[i];

            int j = i - 1;
            while (j > 0 && numbers[j] > insertNum) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j + 1] = insertNum;
        }
    }

    /**
     * 对于直接插入排序问题，数据量巨大时。
     * <p>
     * 将数的个数设为n，取奇数k=n/2，将下标差值为k的数分为一组，构成有序序列。
     * <p>
     * 再取k=k/2 ，将下标差值为k的书分为一组，构成有序序列。
     * <p>
     * 重复第二步，直到k=1执行简单插入排序。
     *
     * @param a
     */
    public static void sheelSort(int[] a) {
        int len = a.length;//单独把数组长度拿出来，提高效率
        while (len != 0) {
            len = len / 2;
            for (int i = 0; i < len; i++) {//分组
                for (int j = i + len; j < a.length; j += len) {//元素从第二个开始
                    int k = j - len;//k为有序序列最后一位的位数
                    int temp = a[j];//要插入的元素
                    /*for(;k>=0&&temp<a[k];k-=len){
                        a[k+len]=a[k];
                    }*/
                    while (k >= 0 && temp < a[k]) {//从后往前遍历
                        a[k + len] = a[k];
                        k -= len;//向后移动len位
                    }
                    a[k + len] = temp;
                }
            }
        }
    }

    /**
     *
     * 1. 首先确定循环次数，并且记住当前数字和当前位置。
     * 2. 将当前位置后面所有的数与当前数字进行对比，小数赋值给key，并记住小数的位置。
     * 3. 比对完成后，将最小的值与第一个数的值交换。
     * 4. 重复2、3步
     * @param a
     */
    public static void selectSort(int[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            int value = a[i];
            int position = i;
            for (int j = i + 1; j < len; j++) {//找到最小的值和位置
                if (a[j] < value) {
                    value = a[j];
                    position = j;
                }
            }
            a[position] = a[i];//进行交换
            a[i] = value;
        }
    }

    public static void quickSort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
    }

    private static void quickSort(int[] numbers, int low, int high) {
        int middle = getMiddle(numbers, low, high);
        quickSort(numbers, low, middle - 1);
        quickSort(numbers, middle + 1, high);
    }

    private static int getMiddle(int[] numbers, int low, int high) {
        //数组的第一个作为中轴
        int temp = numbers[low];
        while (low < high) {
            while (low < high && numbers[high] > temp) {
                high--;
            }
            //比中轴小的记录移到低端
            numbers[low] = numbers[high];
            while (low < high && numbers[low] < temp) {
                low++;
            }
            //比中轴大的记录移到高端
            numbers[high] = numbers[low];
        }
        //中轴记录到尾
        numbers[low] = temp;
        // 返回中轴的位置
        return low;
    }

}
