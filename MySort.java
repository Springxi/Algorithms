import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MySort {

    // 冒泡排序
    // 时间复杂度O(n^2)，空间复杂度O（1），稳定性
    // 每次对比相邻两个数，小的往上浮动。
    public static void BubbleSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j + 1, j);
                }
            }
        }
    }

    // 选择排序
    // 时间复杂度O(n^2)，空间复杂度O（1），无稳定性
    // 每次循环选择剩余未排序的最小值，交换放在i位。
    public static void SelectSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                minIndex = nums[j] < nums[minIndex] ? j : minIndex;
            }
            swap(nums, i, minIndex);
        }

    }

    // 插入排序
    // 时间复杂度，好情况下O(n)正序，差情况下O(nlogn) 倒序,空间复杂度O（1）,稳定性
    // 每次循环，从当前位置往前，一个一个比较，只要比自己大就交换
    public static void InsertSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0 && nums[j] > nums[j + 1]; j--) {
                swap(nums, j, j + 1);
            }
        }

    }

    public static void InsertSort(int[] nums, int n) {
        if (nums == null || nums.length < 2 || n < 2)
            return;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0 && nums[j] > nums[j + 1]; j--) {
                swap(nums, j, j + 1);
            }
        }

    }

    // 希尔排序
    // 时间复杂度，O(nlogn) ,空间复杂度O（1）,无稳定性
    // 先将待排记录序列分割成为若干子序列分别进行插入排序，待整个序列中的记录"基本有序"时，再对全体记录进行一次直接插入排序。
    public static void ShellSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        int n = nums.length, j, increasement;
        for (increasement = n >> 1; increasement > 0; increasement >>= 1) {
            for (int i = increasement; i < n; i++) {
                int temp = nums[i];
                for (j = i - increasement; j >= 0 && nums[j] > temp; j -= increasement) {
                    nums[j + increasement] = nums[j];
                }
                nums[j + increasement] = temp;
            }
        }

    }

    // 归并排序，分治法
    // 时间复杂度，O(nlogn) ,空间复杂度O（n）,稳定性
    public static void MergeSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        MergeSort(nums, 0, nums.length - 1);
    }

    public static void MergeSort(int[] nums, int start, int end) {
        if (start >= end)
            return;
        int mid = (start + end) >> 1;
        MergeSort(nums, start, mid);
        MergeSort(nums, mid + 1, end);
        int[] help = new int[end - start + 1];
        int i = start, j = mid + 1, index = 0;
        while (i <= mid && j <= end) {
            help[index++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= mid) {
            help[index++] = nums[i++];
        }
        while (j <= end) {
            help[index++] = nums[j++];
        }
        for (i = 0; i < help.length; i++) {
            nums[i + start] = help[i];
        }
    }

    // 快速排序
    // 时间复杂度，O(nlogn) 最坏情况O(n^2),空间复杂度O（logn）,无稳定性
    public static void QuickSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        QuickSort(nums, 0, nums.length - 1);
    }

    public static void QuickSort(int[] nums, int low, int high) {
        if (low >= high)
            return;
        Random rand = new Random();
        int standIndex = rand.nextInt(high - low) + low;
        int[] p = Partition(nums, low, high, nums[standIndex]);
        QuickSort(nums, low, p[0] - 1);
        QuickSort(nums, p[1] + 1, high);
    }

    // 荷兰国旗问题
    // 给定一个数组nums，和一个数num，请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。要求额外空间复杂度O(1）,时间复杂度O(N）
    public static int[] Partition(int nums[], int L, int R, int num) {
        int start = L - 1, end = R + 1, index = L;
        while (index < end) {
            if (nums[index] > num) {
                swap(nums, index, --end);
            } else if (nums[index] < num) {
                swap(nums, index++, ++start);
            } else {
                index++;
            }
        }
        return new int[] { start + 1, end - 1 };// 相等区域的边界范围

    }

    // 堆排序
    // 时间复杂度为O(nlogn),额外空间复杂度O(1),不稳定
    // 堆排序为什么比那些O(n^2)的算法快呢？因为排序就是消除逆序对的过程，复杂度O(n^2)的算法，消除了n个逆序对需要执行n次操作，而堆排序消除n个逆序对只需要logn次操作。
    public static void HeapSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        for (int i = 0; i < nums.length; i++) {
            HeapInsert(nums, i);
        }
        int heapSize = nums.length - 1;
        while (heapSize > 0) {
            swap(nums, 0, heapSize);// 弹出堆顶
            Heapify(nums, 0, heapSize);// 调整
            heapSize--;

        }
    }

    // 建立大根堆，新节点加入时堆的调整
    public static void HeapInsert(int[] nums, int i) {
        while (nums[i] > nums[(i - 1) / 2]) {
            swap(nums, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }

    }

    // 当数更改后，如何保证堆结构？ ————比较左右孩子，向孩子值大的下沉，直至无孩子或比孩子大
    public static void Heapify(int[] nums, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int largestIndex = left + 1 < heapSize && nums[left + 1] > nums[left] ? left + 1 : left;
            largestIndex = nums[largestIndex] > nums[index] ? largestIndex : index;
            if (largestIndex == index)
                break;
            swap(nums, largestIndex, index);
            index = largestIndex;
            left = 2 * index + 1;
        }

    }

    // 计数排序
    // 时间复杂度，O(n+k) ,空间复杂度O（k）,稳定性
    // 输入的数据必须是有确定范围的整数。 不适用：最大最小值差距过大、非整数
    // 创建一个的数组C长度取决于待排序数组中数据的范围，将排序数值对应的C数组+1，然后按C数组中的计数输出
    public static void CountingSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        int min = nums[0], max = nums[0], n = nums.length;
        for (int i = 1; i < n; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        int total = max - min;
        int[] arr = new int[total + 1];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i] - min]++;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                nums[count++] = i + min;
            }
        }
    }

    // 桶排序
    // 时间复杂度O(N+C) C=N*(logN-logM)，好情况下O(N) N=M时，差情况下O(N^2),空间复杂度O（N+M）,稳定性
    // 桶为一个数据容器，每个桶存储一个区间内的数。
    public static void BucketSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        int min = nums[0], max = nums[0];
        int n = nums.length, M = 10; // M 表示桶的个数
        for (int i = 1; i < n; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        int total = max - min + 1;

        int[] bucketNum = new int[M]; // 记录每个桶中的元素个数
        int[][] buckets = new int[M][total];// 链表或ArrayList
        int capacity = total / M + 1;

        for (int i = 0; i < n; i++) {

            int index = (nums[i] - min) / capacity;
            buckets[index][bucketNum[index]] = nums[i];
            bucketNum[index]++;
        }
        for (int i = 0; i < M; i++) {
            InsertSort(buckets[i], bucketNum[i]);
        }
        int count = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < bucketNum[i]; j++) {
                nums[count++] = buckets[i][j];
            }
        }

    }

    // 基数排序————桶排序的扩展 , 时间复杂度，O(m*n) ,空间复杂度O(n+m),稳定性
    // 基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。
    // 1.确定数组中的最大元素有几位 2.创建0~9个桶,桶的底层是队列
    // 3.依次判断每个元素的个位，十位至MAX位，存入对应的桶中，出队，存入原数组；直至MAX轮结束输出数组。
    public static void RadixSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        int min = nums[0], max = nums[0];
        int n = nums.length, M = 10; // M 表示桶的个数
        for (int i = 1; i < n; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        int radix = (max - min + "").length();
        for (int i = 0; i < n; i++) {
            nums[i] -= min;
        }
        Queue<Integer>[] list = new LinkedList[M];
        for (int i = 0; i < 10; i++) {
            list[i] = new LinkedList<Integer>();
        }
        for (int r = 0; r < radix; r++) {
            // 分类过程
            for (int i = 0; i < n; i++) {
                list[getIndex(nums[i], r)].offer(nums[i]);
            }
            // 收集的过程
            int index = 0;
            for (int i = 0; i < list.length; i++) {
                while (!list[i].isEmpty()) {
                    nums[index++] = list[i].poll();
                }
            }
        }
        for (int i = 0; i < n; i++) {
            nums[i] += min;
        }

    }

    public static int getIndex(int num, int r) {
        int t = (int) Math.pow(10, r); // ^异或
        return (num / t) % 10;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void PrintNums(String str, int[] nums) {
        System.out.print(str + "     {");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }
        System.out.print("}");
        System.out.println();
    }

    public static int[] generateRandomNums(int maxSize, int maxValue) {
        int[] nums = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * (maxValue + 1) - (Math.random() * maxValue));
        }
        return nums;
    }

    public static void comparator(int[] nums) {
        Arrays.sort(nums);
    }

    public static int[] copyArrys(int[] nums) {
        if (nums == null)
            return null;
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        return copy;
    }

    public static boolean isEquals(int[] nums, int[] arr) {
        if (nums == null && arr == null)
            return true;
        if (nums == null || arr == null)
            return false;
        if (nums.length != arr.length) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void testSort() {
        int maxSize = 10;
        int maxValue = 100;
        int testTime = 1000;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] nums = generateRandomNums(maxSize, maxValue);
            int[] arr = copyArrys(nums);
            int[] numscopy = copyArrys(nums);
            // BubbleSort(nums);
            // SelectSort(nums);
            // InsertSort(nums);
            // ShellSort(nums);

            // MergeSort(nums);
            // QuickSort(nums);
            HeapSort(nums);

            // CountingSort(nums);
            // BucketSort(nums);
            // RadixSort(nums);

            comparator(arr);
            if (!isEquals(nums, arr)) {
                PrintNums("Before Sort", numscopy);
                success = false;
                PrintNums("After Sort", nums);
                PrintNums("Right Sort", arr);
                break;
            }
        }
        System.out.println(success ? "Test Success!" : "Test Failed!");

    }

    public static void main(String[] args) {
        testSort();
    }
}
