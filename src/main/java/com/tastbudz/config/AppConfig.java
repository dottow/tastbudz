package com.tastbudz.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

@Configuration
@ImportResource("classpath:/com/tastbudz/datasource.xml")
@ComponentScan({"com.tastbudz.model","com.tastbudz.dao","com.tastbudz.service"})
@EnableTransactionManagement
public class AppConfig {
	private @Value("${database.url}") String url;
	private @Value("${database.username}") String username;
	private @Value("${database.password}") String password;
	private @Value("${database.driver}") String driver;
	private @Value("${database.dialect}") String dialect;
	private @Value("${hbm2ddl.settings}") String hbm2ddlSettings;

	@Bean(name="dataSource")
    public DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setUrl(url);
        return ds;
    }
	
	@Bean
	public SessionFactory getSessionFactory() {
		LocalSessionFactoryBean factoryBean = null;
		try {
			factoryBean = new LocalSessionFactoryBean();
            Properties pp = new Properties();
            pp.setProperty("hibernate.dialect", dialect);
            pp.setProperty("hibernate.max_fetch_depth", "3");
            pp.setProperty("hibernate.show_sql", "true");
            pp.setProperty("hibernate.connection.pool_size", "5");
            pp.setProperty("hibernate.cache.use_query_cache", "false");
            pp.setProperty("hibernate.cache.use_second_level_cache", "true");
            pp.setProperty("hibernate.hbm2ddl.auto", hbm2ddlSettings);
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
