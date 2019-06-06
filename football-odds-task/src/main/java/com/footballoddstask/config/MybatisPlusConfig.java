package com.footballoddstask.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Description
 * @Author wenxi
 * @Date 2019/1/11 9:39
 * @Version 1.0
 */
@Configuration
@MapperScan("com.footballoddstask.dao")
public class MybatisPlusConfig {
    /**
    *@Description mybatis-plus 分页插件 文档：http://mp.baomidou.com<br>
    *@Date  9:40 2019/1/11
    *@Author wenxi
    *@Param []
    *@Return com.baomidou.mybatisplus.plugins.PaginationInterceptor
    *@Exception
    **/
    @Bean
   public PaginationInterceptor paginationInterceptor(){
       return new PaginationInterceptor();
   }
}
