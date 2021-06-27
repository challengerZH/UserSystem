package com.lzy.pi.config;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liuqh
 * @date 2021年06月27日
 * @description
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private static final Logger logger =  LoggerFactory.getLogger(WebMvcConfig.class);

    @Value("${case.resources.pathPatterns}")
    private String pathPatterns;

    @Value("${case.resources.resourceLocations}")
    private String resourceLocations;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String [] paths = pathPatterns.split(",|，");
        logger.info("paths is :{}", pathPatterns);
        String [] resources = resourceLocations.split(",|，");
        logger.info("resources is :{}", resourceLocations);
        registry.addResourceHandler(paths).addResourceLocations(resources);
    }
}
