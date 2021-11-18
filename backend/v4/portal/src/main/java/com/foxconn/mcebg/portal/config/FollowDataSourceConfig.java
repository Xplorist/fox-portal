package com.foxconn.mcebg.portal.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.foxconn.mcebg.portal.mapper.follow", sqlSessionTemplateRef = "followSqlSessionTemplate")
public class FollowDataSourceConfig {
    @Bean(name = "followDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.follow")
    public DataSource setDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "followTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("followDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "followSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("followDataSource") DataSource dataSource) throws Exception {
        // MyBatis-Plus使用MybatisSqlSessionFactoryBean  MyBatis直接使用SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultStatementTimeout(30000);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setCacheEnabled(true);
        bean.setConfiguration(configuration);
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:configuration/MyBatis/mapper/follow/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "followSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("followSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
