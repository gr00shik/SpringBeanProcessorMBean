package com.gr00shik;

import com.gr00shik.beans.Benchmark;
import com.gr00shik.beans.MyTestBeanClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class App
{
    public static void main( String[] args ) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer.registerMBean(new Benchmark(), new ObjectName("benchmark", "name", "run"));

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        while(true) {
            context.getBean(MyTestBeanClass.class);
        }
    }
}
