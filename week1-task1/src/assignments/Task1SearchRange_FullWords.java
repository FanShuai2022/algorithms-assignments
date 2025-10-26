package assignments;

import java.util.Arrays;

public class Task1SearchRange_FullWords {

    // 主函数：返回目标值的首尾下标
    public static int[] searchRange(int[] nums, int target) {
        int length = (nums == null) ? 0 : nums.length;
        if (length == 0) return new int[]{-1, -1};

        // 先找最左边的出现位置
        int left = findLeft(nums, target);
        if (left == -1) return new int[]{-1, -1};  // 没找到就直接返回

        // 再找最右边的出现位置
        int right = findRight(nums, target);
        return new int[]{left, right};
    }

    // 找到 target 的最左位置
    private static int findLeft(int[] nums, int target) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        while (leftIndex <= rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

            // 如果中间值 >= 目标，说明目标可能在左边
            if (nums[middleIndex] >= target) {
                rightIndex = middleIndex - 1;
            } else { // 否则目标一定在右边
                leftIndex = middleIndex + 1;
            }
        }

        // 最后 leftIndex 会停在第一个 >= target 的位置
        if (leftIndex < nums.length && nums[leftIndex] == target)
            return leftIndex;
        return -1;
    }

    // 找到 target 的最右位置
    private static int findRight(int[] nums, int target) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        while (leftIndex <= rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

            // 如果中间值 <= 目标，说明目标可能在右边
            if (nums[middleIndex] <= target) {
                leftIndex = middleIndex + 1;
            } else { // 否则目标在左边
                rightIndex = middleIndex - 1;
            }
        }

        // 最后 rightIndex 会停在最后一个 <= target 的位置
        if (rightIndex >= 0 && nums[rightIndex] == target)
            return rightIndex;
        return -1;
    }

    // 打印测试结果的小函数
    private static void printResult(int[] nums, int target) {
        System.out.println("nums = " + Arrays.toString(nums) +
                ", target = " + target +
                "  ->  " + Arrays.toString(searchRange(nums, target)));
    }

    // 主程序：自测
    public static void main(String[] args) {
        // 题目给的示例
        printResult(new int[]{4,9,10,13,17,17,19,21}, 17);  // [4,5]
        printResult(new int[]{2,4,6,8,10,14,16}, 12);       // [-1,-1]
        printResult(new int[]{}, 0);                        // [-1,-1]

        // 我自己加的一些测试（各种边界）
        printResult(new int[]{1}, 1);                       // [0,0]
        printResult(new int[]{1}, 0);                       // [-1,-1]
        printResult(new int[]{1,1,1,1}, 1);                 // [0,3]
        printResult(new int[]{1,2,3,4}, 1);                 // [0,0]
        printResult(new int[]{1,2,3,4}, 4);                 // [3,3]
        printResult(new int[]{1,2,2,2,3}, 2);               // [1,3]
        printResult(new int[]{5,7,7,8,8,10}, 6);            // [-1,-1]
        printResult(new int[]{2,2,2,3,4}, 1);               // [-1,-1]
        printResult(new int[]{2,3,4}, 9);                   // [-1,-1]
    }
}

