package Offer2;

public class NumberIn2DArray {

    /**
     * 方案一：暴力解决
     *
     * 思路：简单的遍历二维数组，判断每个位置的值是否等于目标值
     *
     * 复杂度分析：
     * 时间复杂度：O(nm)。二维数组中的每个元素都被遍历，因此时间复杂度为二维数组的大小。
     * 空间复杂度：O(1)。
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray1(int[][] matrix, int target) {
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if (target == matrix[i][j])
                    return true;
            }
        }
        return false;
    }

    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        int left = 0, right = matrix.length, top = 0, down = matrix[0].length;
        int a = 0, b = 0;
        while (right != left && top != down){
            a = (int)Math.ceil((right - a) / 2);
            b =  (int)Math.ceil((right - b) / 2);
            System.out.println(a + '-' + b);
            if (target == matrix[a][b]){
                return true;
            }else if(target < matrix[a][b]){
                right = a;
                down = b;
            }else if(target < matrix[a][b]){
                left = a;
                top = b;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[][] nums = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};

        NumberIn2DArray numberIn2DArray = new NumberIn2DArray();
        System.out.println(numberIn2DArray.findNumberIn2DArray2(nums, 5));
    }
}
