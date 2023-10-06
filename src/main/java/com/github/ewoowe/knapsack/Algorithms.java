package com.github.ewoowe.knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通用算法类
 *
 * @author wangcheng@ictnj.ac.cn
 * @since 2023/9/21
 */
public class Algorithms {

    /**
     * 选择一个剩余容量符合需求的集装箱或者剩余容量最大的集装箱
     *
     * @param containers 集装箱集合
     * @param need 容量需求
     * @return 存在符合需求的集装箱则返回，不存在返回null
     */
    private static Container selectContainer(List<Container> containers, int need) {
        assert (need > 0);
        assert (containers != null);
        assert (!containers.isEmpty());
        // 剩余容量符合要求的候选集装箱
        Container bestContainer1 = null;
        // 剩余容量不符合要求但是剩余容量最大的候选集装箱
        Container bestContainer2 = null;
        for (Container container : containers) {
            // 如果集装箱剩余容量满足需求
            if (container.getRemain() >= need) {
                if (bestContainer1 == null) {
                    // 候选集装箱初次赋值
                    bestContainer1 = container;
                } else {
                    if (bestContainer1.getUsedBoxes() > container.getUsedBoxes()) {
                        // 如果候选集装箱已容纳箱子数量大于当前集装箱已容纳箱子数量则更新候选集装箱
                        bestContainer1 = container;
                    } else if (bestContainer1.getUsedBoxes() == container.getUsedBoxes()) {
                        // 如果候选集装箱已容纳箱子数量等于当前集装箱已容纳箱子数量，则更新候选集装箱为两者剩余容量更大者
                        if (bestContainer1.getRemain() < container.getRemain())
                            bestContainer1 = container;
                    }
                }
            } else if (container.getRemain() > 0) {
                // 只有在剩余容量符合要求的候选集装箱不存在的情况下，才更新剩余容量不符合要求但是剩余容量最大的候选集装箱
                if (bestContainer1 == null) {
                    if (bestContainer2 == null) {
                        bestContainer2 = container;
                    } else {
                        if (bestContainer2.getRemain() < container.getRemain())
                            bestContainer2 = container;
                    }
                }
            }
        }
        return bestContainer1 == null ? bestContainer2 : bestContainer1;
    }

    /**
     * 可将箱子切开的背包算法，同时尽量保证箱子的完整
     *
     * @param maxContainers 最大的集装箱数量，小于等于0表示不限制最大集装箱数量
     * @param minContainer 是否以最小使用集装箱原则，如果不是则使用maxContainers集装箱数量
     * @param containerCap 每个集装箱的容量
     * @param boxes 箱子的数量及每个箱子的容量
     * @return 集装箱数量
     */
    public static List<Container> knapsackCanSplit(int maxContainers, boolean minContainer, int containerCap, Box[] boxes)
            throws AlgorithmException {
        if (containerCap <= 0)
            throw new AlgorithmException("containerCap must be positive");
        if (boxes.length == 0)
            return null;
        int sum = 0;
        for (Box box : boxes) {
            if (box.getCapacity() < 0)
                throw new AlgorithmException("box's capacity can not negative");
            sum += box.getCapacity();
        }
        if (sum == 0)
            return null;
        double needContainers = ((double) sum) / ((double) containerCap);
        if (maxContainers > 0 && needContainers > maxContainers)
            throw new AlgorithmException("container not enough");
        Arrays.sort(boxes);
        int base = (int) needContainers;
        int need;
        if (minContainer) {
            need = (needContainers - base) > 0 ? (base + 1) : base;
        } else {
            need = maxContainers;
        }
        List<Container> containers = new ArrayList<>(need);
        for (int i = 0; i < need; i++)
            containers.add(new Container(containerCap, boxes.length));
        int maxBoxPerContainer = (int) Math.ceil(((double) boxes.length) / ((double) need));
        int remain = sum;
        while (remain != 0) {
            int oldRemain = remain;
            for (Box box : boxes) {
                if (box.getRemain() == 0)
                    continue;
                Container container = selectContainer(containers, box.getRemain());
                if (container == null)
                    throw new AlgorithmException("can not allocate container");
                if (box.getRemain() > container.getRemain()) {
                    int containerRemain = container.getRemain();
                    remain -= containerRemain;
                    container.addBoxUse(box.getId(), containerRemain);
                    box.alloc(containerRemain);
                } else {
                    int boxRemain = box.getRemain();
                    remain -= boxRemain;
                    container.addBoxUse(box.getId(), boxRemain);
                    box.alloc(boxRemain);
                }
            }
            Arrays.sort(boxes);
        }
        return containers;
    }
}
