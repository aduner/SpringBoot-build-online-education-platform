package com.aduner.eduService.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Aduner
 */
@Configuration
@MapperScan("com.aduner.eduService.mapper")
@EnableSwagger2
public class EduConfig {
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

}
