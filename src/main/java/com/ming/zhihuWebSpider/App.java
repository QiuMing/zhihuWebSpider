package com.ming.zhihuWebSpider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	   public static void main(String[] args) {
	       SpringApplication.run(new String[]{
	               "classpath*:/config/spring-*.xml",
	       }, args);
	   }
}
