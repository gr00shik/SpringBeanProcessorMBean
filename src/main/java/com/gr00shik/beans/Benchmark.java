package com.gr00shik.beans;

public class Benchmark implements BenchmarkMBean {
    @Override
    public void runBenchmark(boolean run) {
        MBeanController.runBenchmark = run;
    }
}
