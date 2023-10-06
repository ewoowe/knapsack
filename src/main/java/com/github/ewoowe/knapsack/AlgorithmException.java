package com.github.ewoowe.knapsack;

/**
 * 算法异常，调用方需要捕获该异常
 *
 * @author wangcheng@ictnj.ac.cn
 * @since 2023/9/21
 */
public class AlgorithmException extends Exception {

    public AlgorithmException(String message) {
        super(message);
    }
}
