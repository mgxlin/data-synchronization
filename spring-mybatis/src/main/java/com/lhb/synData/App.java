package com.lhb.synData;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lhb
 * @date 2022/2/13 13:39
 */
public class App {
    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    }
}
