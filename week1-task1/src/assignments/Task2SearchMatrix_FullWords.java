package assignments;


import java.util.Arrays;

public class Task2SearchMatrix_FullWords {

    // 主函数：按题意返回 true/false
    public static boolean searchMatrix(int[][] matrix, int target) {
        // 1) 边界情况：空矩阵或列数为 0，直接 false
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // 2) 定义“虚拟一维数组”的左右边界
        int leftIndex = 0;
        int rightIndex = rows * cols - 1;

        // 3) 标准二分框架
        while (leftIndex <= rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

            // 4) 一维下标映射回二维坐标
            int row = middleIndex / cols;   // 行 = 下标除以列数
            int col = middleIndex % cols;   // 列 = 下标对列数取模
            int value = matrix[row][col];

            if (value == target) {
                return true;                // 找到了
            } else if (value < target) {
                leftIndex = middleIndex + 1; // 去右半边
            } else {
                rightIndex = middleIndex - 1; // 去左半边
            }
        }
        return false; // 没找到
    }

    // 简单打印辅助
    private static void printResult(int[][] matrix, int target) {
        System.out.println("matrix = " + Arrays.deepToString(matrix)
                + ", target = " + target
                + "  ->  " + searchMatrix(matrix, target));
    }

    // 自测：包含题目示例 + 额外样例（边界/极端）
    public static void main(String[] args) {
        // 题目示例
        printResult(new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        }, 3);          // true

        printResult(new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        }, 13);         // false

        // 额外测试
        printResult(new int[][]{{1}}, 1);          // true（单元素命中）
        printResult(new int[][]{{1}}, 0);          // false（单元素未命中）
        printResult(new int[][]{{1,2,3}}, 3);      // true（单行）
        printResult(new int[][]{{1},{3},{5}}, 4);  // false（单列）
        printResult(new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        }, 60);         // true（最后一个）
        printResult(new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        }, 0);          // false（比最小还小）
        printResult(new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        }, 100);        // false（比最大还大）

        // 空矩阵相关
        printResult(new int[][]{}, 1);             // false（空行）
        printResult(new int[][]{{}}, 1);           // false（有行但 0 列）
    }
}

