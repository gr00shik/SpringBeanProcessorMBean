package com.gr00shik.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

public class MyBenchmarkBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for(Field filed : fields){
            InjectMyRandom annotation = filed.getAnnotation(InjectMyRandom.class);
            if(annotation!=null){
                int min = annotation.min();
                int max = annotation.max();
                int random = min + new Random().nextInt(max-min);
                filed.setAccessible(true);
                ReflectionUtils.setField(filed, bean, random);
            }
        } return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] declaredMethods = bean.getClass().getDeclaredMethods();
        for (Method method : declaredMethods){
            RunMyBenchmark annotation = method.getAnnotation(RunMyBenchmark.class);
            if(annotation!=null){
                long begin = System.currentTimeMillis();
                ReflectionUtils.invokeMethod(method, bean);
                long after = System.currentTimeMillis();
                if(MBeanController.runBenchmark) {
                    System.out.println(after - begin);
                }
            }
        }
        return bean;
    }
}
