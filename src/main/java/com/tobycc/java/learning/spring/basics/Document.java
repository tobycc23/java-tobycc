package com.tobycc.java.learning.spring.basics;

import org.springframework.context.annotation.*;

/**
 * Created by toby.christey-clover on 04/05/2018.
 */
@Configuration
public class Document {

    //Registers docPage as a bean in the Spring Application Context equivalent to
    //<beans><bean id = "docPage" class = "com.....DocPage" /></beans>
    @Bean
    public DocPage docPage(){
        return new DocPage();
    }
}
