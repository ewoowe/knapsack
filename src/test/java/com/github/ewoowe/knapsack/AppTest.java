package com.github.ewoowe.knapsack;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws AlgorithmException {
        Box[] boxes = new Box[]{
                new Box(2, 1),
                new Box(2, 2),
                new Box(2, 3),
                new Box(1, 4),
                new Box(1, 5),
                new Box(1, 6),
                new Box(1, 7),
                new Box(1, 8)};
        Arrays.sort(boxes);
        List<Container> containers = Algorithms.knapsackCanSplit(3, true, 4, boxes);
        if (containers != null) {
            containers.forEach(container -> {
                System.out.println(container.toString());
            });
        }
    }
}
