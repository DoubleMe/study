package com.chenhm.tree.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * fork join 线程测试
 * <p>
 * <p>主要测试RecursiveTask 并行算法的使用</p>
 *
 * @author chen-hongmin
 * @date 2018/3/8 10:35
 * @since V1.0
 */
public class ForkJoinTest {

    /**
     * 最小处理任务单元
     */
    private static final int MIN_FORK = 2;


    public static void main(String[] args) throws Exception{

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTest test = new ForkJoinTest();

        ForkJoinTest.ForkJoinSumTask sumTask = test.new ForkJoinSumTask(2, 3);

        ForkJoinTask<Integer> joinTask = pool.submit(sumTask);
        System.out.println(joinTask.get());
    }


    /**
     * 简单的累加任务
     */
    public class ForkJoinSumTask extends RecursiveTask<Integer> {

        int sum;
        int start;
        int end;

        public ForkJoinSumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            //小于最小任务单元 直接处理,不需要新增任务并行处理
            if (start - end < MIN_FORK) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }

                return sum;
            }
            //取中位数
            int midNum = (start + end) / 2;
            ForkJoinSumTask left = new ForkJoinSumTask(start, midNum);
            ForkJoinSumTask right = new ForkJoinSumTask(midNum + 1, end);
            left.fork();
            right.fork();
            sum = left.join() + right.join();

            return sum;
        }
    }
}
