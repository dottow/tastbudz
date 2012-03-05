package com.tastbudz.config;


import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

@Configuration
@PropertySource("classpath:/com/tastbudz/datasource.properties")
@Import(SecurityConfig.class)
@ComponentScan({"com.tastbudz.model","com.tastbudz.dao","com.tastbudz.service"})
@EnableTransactionManagement
public class AppConfig {
	@Inject
	private Environment env;
		
	@Bean(name="dataSource")
    public DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getProperty("database.driver"));
        ds.setUsername(env.getProperty("database.username"));
        ds.setPassword(env.getProperty("database.password"));
        ds.setUrl(env.getProperty("database.url"));
        return ds;
    }
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory() {
		LocalSessionFactoryBean factoryBean = null;
		try {
			factoryBean = new LocalSessionFactoryBean();
            Properties pp = new Properties();
            pp.setProperty("hibernate.dialect", env.getProperty("database.dialect"));
            pp.setProperty("hibernate.max_fetch_depth", "3");
            pp.setProperty("hibernate.show_sql", "true");
            pp.setProperty("hibernate.connection.pool_size", "5");
            pp.setProperty("hibernate.cache.use_query_cache", "false");
            pp.setProperty("hibernate.cache.use_second_level_cache", "true");
            pp.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hbm2ddl.settings"));
            pp.setProperty("cache.provider_class", "org.hibernate.cache.EhCacheProvider");
            factoryBean.setHibernateProperties(pp);
            factoryBean.setDataSource(getDataSource());
            String[] packages = new String[] {"com.tastbudz.model"};
            factoryBean.setPackagesToScan(packages);
            factoryBean.setAnnotatedPackages(packages);
            factoryBean.afterPropertiesSet();
        } 
		catch (Throwable t) {
            t.printStackTrace();
        }
        return factoryBean.getObject();
	}	
	
	@Bean(name="transactionManager")
	public ResourceTransactionManager getTransactionManager() {
		return new HibernateTransactionManager(getSessionFactory());
	}
}
