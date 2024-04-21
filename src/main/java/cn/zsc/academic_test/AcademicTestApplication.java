package cn.zsc.academic_test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.zsc")
@MapperScan("cn.zsc.mapper")
public class AcademicTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademicTestApplication.class, args);
    }

}
