package com.ueboot.starter.backend.config;

import com.bstek.uflo.console.UfloServlet;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author yangkui
 * createTime:2019/3/1810:38 AM
 */
@Configuration
@ImportResource(locations = {"classpath:uflo-console-context.xml", "classpath:config.xml"})
public class UflConfig {


   @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(
                new String[]{"com.ueboot.starter.entity","com.bstek.uflo.model"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:/Users/yangkui/uflo/db;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return hibernateProperties;
    }

    @Bean
    public ServletRegistrationBean getServletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new UfloServlet());
        bean.addUrlMappings("/uflo/*");
        return bean;
    }
}
