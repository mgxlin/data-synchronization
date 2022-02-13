package com.lhb.multipledata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 *
 * 多数据源配置类
 *
 * @author lhb
 * @date 2022/2/13 17:51
 */
@Configuration
public class DataSourceConfig {

    /**
     * 指定第一个数据源
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.local")  //这里 prefix 指向了 application.yml 中的配置,实现指定数据源,即使用本地数据库
    public DataSource localDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.his")  //这里 prefix 指向了 application.yml 中的配置,实现指定数据源,即使用His视图
    public DataSource hisDataSource(){
        return DataSourceBuilder.create().build();
    }
}
