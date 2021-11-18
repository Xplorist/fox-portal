package com.foxconn.mcebg.portal.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.foxconn.mcebg.portal.mapper.base", sqlSessionTemplateRef = "baseSqlSessionTemplate")
public class BaseDataSourceConfig {
    //这里配置数据源
    @Bean(name = "baseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.base")
    @Primary
    public DataSource setDataSource() {
        return DataSourceBuilder.create().build();
    }

    //这里配置事务管理器
    @Bean(name = "baseTransactionManager")
    @Primary
    public DataSourceTransactionManager setTransactionManager(@Qualifier("baseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //这里配置SqlSessionFactory，连接工厂
    @Bean(name = "baseSqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource) throws Exception {
        // MyBatis 配置
        //SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();

        // MyBatis-Plus使用MybatisSqlSessionFactoryBean  MyBatis直接使用SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        //此处配置下划线转驼峰
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setDefaultStatementTimeout(30000);
        configuration.setCacheEnabled(true);
        bean.setConfiguration(configuration);
        bean.setDataSource(dataSource);
        //此处配置mybatis扫描路径
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:configuration/MyBatis/mapper/base/*Mapper.xml"));

        return bean.getObject();
    }

    //这里配置SqlSessionTemplate，标准操作模板
    @Bean(name = "baseSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
