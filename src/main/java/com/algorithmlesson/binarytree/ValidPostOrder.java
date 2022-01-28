package com.algorithmlesson.binarytree;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2022/1/11
 */
public class ValidPostOrder {

    public static void main(String[] args) {
        int[] postorder = {1,2,5,10,6,9,4,3};
        System.out.println(verifyPostorder(postorder));
    }

    public static boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    private static boolean verifyPostorder(int[] postorder, int low, int high) {
        if (low >= high) {
            return true;
        }
        int i = low;
        // LRN 先确定L的部分
        while (i < high && postorder[i] < postorder[high]) {
            i++;
        }
        int j = i;
        // 则后面的都是R的部分 如果是二叉搜索树 肯定都是大于根结点的
        while (j < high && postorder[j] > postorder[high]) {
            j++;
        }
        if (j != high) {
            return false;
        }
        boolean leftValid = verifyPostorder(postorder, low, i - 1);
        if (!leftValid) {
            return false;
        }
        boolean rightValid = verifyPostorder(postorder, i, high - 1);
        return rightValid;
    }
}
