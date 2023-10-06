package com.github.ewoowe.knapsack;

/**
 * 背包算法集装箱
 *
 * @author wangcheng@ictnj.ac.cn
 * @since 2023/9/21
 */
public class Container {
    /**
     * 集装箱的容量
     */
    private int capacity;
    /**
     * 集装箱使用的容量
     */
    private int used = 0;
    /**
     * 每个box的使用量
     */
    private final int[] usePerBox;

    public Container(int capacity, int boxNum) {
        this.capacity = capacity;
        usePerBox = new int[boxNum];
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public void addBoxUse(int index, int use) {
        usePerBox[index - 1] = use;
        used += use;
    }

    public int[] getUsePerBox() {
        return usePerBox;
    }
    public int getRemain() {
        return capacity - used;
    }

    public int getUsedBoxes() {
        int used = 0;
        for (int use : usePerBox)
            if (use > 0)
                used++;
        return used;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("capacity=").append(capacity).append(",").append("used=").append(used).append("\n");
        for (int i = 0; i < usePerBox.length; i++)
            sb.append(i).append("=").append(usePerBox[i]).append("|");
        return sb.toString().substring(0, sb.length() - 1);
    }
}
