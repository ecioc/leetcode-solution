package Offer2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RepeatNumber {
    /*
    方案一
复杂度分析
时间复杂度
遍历数组需要 O(N) 时间。

注意参考代码里面的关键字 continue，这表示在 while 的一次循环里面，只有这次循环将 索引(i) 与 索引值(num[i]) 匹配到了，才会执行下一次循环。

在每一次的循环过程中，索引(i) 与 索引值(num[i]) 匹配到后，在后续的循环过程中不会操作它们，所以虽然一开始的循环过程中，执行的交换操作较多，但在后续的循环过程中根本不需要再执行操作了。

根据均摊复杂度分析 ，总的时间复杂度为 O(N) ，N 为数组的长度。

空间复杂度
使用常数复杂度的额外空间，为 O(1)。

     */
    public int findRepeatNumber1(int[] nums){
        if (nums.length == 0){
            return -1;
        }
        int i = 0;
        while (i < nums.length - 1){
            if (nums[i] == i){
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) return nums[i];
            //置换下标i与下标nums[i]的指
            replacementArr(nums, i, nums[i]);
        }
        return -1;
    }

    public void replacementArr(int[] nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

//    方案二：哈希查找
//    在暴力解法中，我们先遍历每一个元素，然后再从其余的元素中查找这个元素是否存在，其实这里要的就是能高效的判断一个元素是否已经存在，
//    我们可以使用哈希表，因为哈希表判断是否存在的时间复杂度是 O(1)。//
//    使用哈希表后的算法步骤是：
//    先初始化一个哈希表 (HashSet)
//    然后遍历每一个元素，分别对每一个元素做如下的处理：
//    先判断哈希表中是否存在这个元素
//    如果存在的话，则说明这个元素重复，则直接返回
//    否则，将这个元素加入到哈希表中，方便后续的判重
//    以上算法实现的复杂度分析：
//    时间复杂度是 O(n)
//    空间复杂度是 O(n)
//    时间复杂度 O(n)，对于数据规模 10 万级别的话，运行速度是可以接受的。但是这里的空间复杂度则变为 O(n)，因为哈希表需要申请额外的 n 个空间，这里用到的是典型的空间换时间的思想
    public int findRepeatNumber2(int[] nums){
        // 1. 初始化一个哈希表
        Set<Integer> temp = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            //2. 如果Set中已包含该值，则直接返回
            if (temp.contains(nums[i])){
                // 如果存在，则直接返回
                return nums[i];
            }
            //3. Set中无值，则添加值
            temp.add(nums[i]);
        }
        return -1;
    }

    /**
     * 方案三：数组代替哈希表
     * 在题目中，有一个信息，我们需要注意下，那就是数组中每个元素的大小在 0 ~ n - 1 的范围内。利用这个信息，我们就可以使用数组代替上面方案二的哈希表，主要的思路是：
     *
     * 定义一个长度为 n 的数组 bucket，然后将所有的元素初始化为 -1
     * 在查找处理的时候，使用原数组的元素作为 bucket 的下标，原数组元素对应的下标作为 bucket 的元素值。
     *
     * 以上算法实现的复杂度分析：
     *
     * 时间复杂度是 O(n)
     * 空间复杂度是 O(n)
     * 可以看出，时间复杂度和空间复杂度都是和用哈希表的解决方案是一样的。但是使用数组绝对会有性能的提高，主要表现在如下的两个方面：
     *
     * 哈希表 (HashSet) 底层是使用数组 + 链表或者红黑树组成的，而且它的数组也是用不满的，有加载因子的。所以使用数组来代替哈希表，能节省空间
     * 哈希表在判重的时候需要经过哈希计算，还可能存在哈希冲突的情况，而使用数组则可以直接计算得到 index 的内存位置，所以使用数组访问性能更好。
     * @param nums
     * @return
     */
    public int findRepeatNumber3(int[] nums){
        // 1. 初始化一个数组
        int[] temp = new int[nums.length];
        Arrays.fill(temp, -1);
        for (int i = 0; i < nums.length; i++) {
            //2. 判断当前元素是否已经存在
            if (temp[nums[i]] != -1){
                // 如果存在，则直接返回
                return nums[i];
            }
            // 否则的话，将当前元素作为索引，当前元素的下标作为值，填入数组中，
            // 方便后续的查找判重
            temp[nums[i]] = i;
        }
        return -1;
    }



    public static void main(String args[]){
        int[] nums = new int[]{1, 2, 2, 3};
        RepeatNumber repeatNumber = new RepeatNumber();
        System.out.println(repeatNumber.findRepeatNumber1(nums));

    }
}
