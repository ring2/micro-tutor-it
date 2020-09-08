package com.lz.read.common;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author :     lz
 * @date :       2020/4/2 10:14
 * description:  自定义注解，集中配置启动类上的注解
 **/
@SpringBootApplication
@EnableTransactionManagement // 开启事务管理
@Inherited // 注解可被继承，使用该注解的类继承已有全部注解
@Retention(RetentionPolicy.RUNTIME) // 声明为运行时注解，可在运行时通过反射读取到
@MapperScan("com.lz.read.dao")
public @interface ReadBootApplication {
}
