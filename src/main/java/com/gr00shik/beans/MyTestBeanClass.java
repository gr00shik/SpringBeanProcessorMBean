package com.gr00shik.beans;

public class MyTestBeanClass {

    @InjectMyRandom(min=5, max=20)
    private int random;


    @RunMyBenchmark
    public void runMyBean(){
        for (int i = 0; i < random; i++) {
            try {
                Thread.sleep(200);
                System.out.println("Working ....");
            } catch (InterruptedException e) {
                System.out.println("Thread sleep error");
            }
        }
    }


}
