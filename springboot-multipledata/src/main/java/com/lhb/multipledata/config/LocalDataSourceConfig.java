package com.lhb.multipledata.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author lhb
 * @date 2022/2/13 17:22
 */
@Configuration
@MapperScan(
        basePackages = "com.lhb.multipledata.dao.local",
        sqlSessionFactoryRef = "sqlSessionFactoryLocal",
        sqlSessionTemplateRef = "sqlSessionTemplateLocal"
)
public class LocalDataSourceConfig {

    private DataSource localDataSource;

    public LocalDataSourceConfig(@Qualifier("localDataSource") DataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryLocal() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/local/*.xml"));
        bean.setDataSource(localDataSource);
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateLocal() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryLocal());
    }

}
