package com.ming.zhihuWebSpider;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  //实现spring先加载log4j后被Junit加载
@ContextConfiguration(locations =  {
        "classpath*:config/spring-mvc.xml" ,
        "classpath*:config/spring-mybatis.xml"
})
public abstract class AbstractServiceTests  extends AbstractTransactionalJUnit4SpringContextTests {


}
