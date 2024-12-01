package com.pserson.xywstudy.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component
public class Confirgtion {

    @Bean("a")
    private A getA(){
        return new A();
    }
    @Bean("a")
    private B getB(){
        return new B();
    }
}
