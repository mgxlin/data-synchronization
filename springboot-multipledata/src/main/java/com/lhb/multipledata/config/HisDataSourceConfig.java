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
        basePackages = "com.lhb.multipledata.dao.his",  //在这里指定 mapper 接口位置
        sqlSessionFactoryRef = "sqlSessionFactoryHis",
        sqlSessionTemplateRef = "sqlSessionTemplateHis"
)
public class HisDataSourceConfig {

    private DataSource hisDataSource;

    /**
     * 注入数据源到配置类中
     * @param hisDataSource
     */
    public HisDataSourceConfig(@Qualifier("hisDataSource") DataSource hisDataSource) {
        this.hisDataSource = hisDataSource;
    }

    /**
     * 注入 SqlSessionFactory 对象
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryHis() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //这里 指定 mapper-locations 配置文件的位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/his/*.xml"));
        bean.setDataSource(hisDataSource);
        return bean.getObject();
    }

    /**
     * 注入SqlSessionTemplate对象
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplateHis() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryHis());
    }

}
