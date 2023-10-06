package com.github.ewoowe.knapsack;

/**
 * 背包算法中的箱子
 *
 * @author wangcheng@ictnj.ac.cn
 * @since 2023/9/21
 */
public class Box implements Comparable<Box> {
    /**
     * 箱子的容量
     */
    private final int capacity;
    /**
     * 箱子剩余容量
     */
    private int remain;
    /**
     * 箱子id
     */
    private final int id;

    public Box(int capacity, int id) {
        this.capacity = capacity;
        this.remain = capacity;
        this.id = id;
    }

    @Override
    public int compareTo(Box o) {
        return o.remain - remain;
    }

    public int getRemain() {
        return remain;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public boolean alloc(int need) {
        if (remain < need)
            return false;
        remain -= need;
        return true;
    }
}
