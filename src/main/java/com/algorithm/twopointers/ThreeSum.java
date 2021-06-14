package com.algorithm.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/5/25
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 先排序 是为了方便之后的去重和双指针法探测结果
        Arrays.sort(nums);
        int lo, hi, sum;
        // 遍历所有可能的三元组的开头
        for (int i = 0; i < nums.length - 2; i++) {
            // 剪枝：因为升序排序 当前数>0 后面不可能有符合条件的数来构成 nums[i] + nums[lo] + nums[hi] == 0了
            if (nums[i] > 0) {
                break;
            }
            // 遇到重复的数 只选第一个进行遍历
            if (i == 0 || nums[i] != nums[i - 1]) {
                lo = i + 1;
                hi = nums.length - 1;
                sum = -nums[i];
                // 再找满足条件的二元组（将问题降维为2维的问题 3Sum -> 2Sum(有序数组中的)）
                while (lo < hi) {
                    if (nums[lo] + nums[hi] < sum) {
                        lo++;
                    } else if (nums[lo] + nums[hi] > sum) {
                        hi--;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        // 这两个while循环也是为了去重 如果有重复元素让lo hi走到重复元素的最末端 方便之后的统一处理
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            lo++;
                        }
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    }
                }
            }
        }
        return res;
    }
}
