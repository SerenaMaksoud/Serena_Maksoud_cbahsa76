package com.example.Anywr.configuration.mybatis;

import com.example.Anywr.configuration.properties.ConfigPropertyReader;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.Anywr.datahandlers.mappers", sqlSessionTemplateRef = "anywrSqlSessionTemplate")
@EnableTransactionManagement
public class AnywrDataSource {
    private final ConfigPropertyReader config;

    @Autowired
    public AnywrDataSource(ConfigPropertyReader cnf) {
        this.config = cnf;
    }

    @Bean(name = "anywr")
    public HikariDataSource dataSourcebackendBetting() {
        return setDs();
    }

    @Bean(name = "anywrSqlSessionFactory")
    public SqlSessionFactory anywrSqlSessionFactory(@Qualifier("anywr") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("anywr") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "anywrSqlSessionTemplate")
    public SqlSessionTemplate anywrSqlSessionTemplate(@Qualifier("anywrSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    private HikariDataSource setDs(){
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(config.getDriver_class_name());
        hikariDataSource.setJdbcUrl(config.getDb_url() + config.getDatabaseName()+config.getDriver_params());
        hikariDataSource.setUsername(config.getUsername());
        hikariDataSource.setPassword(config.getPassword());
        return hikariDataSource;
    }
}
