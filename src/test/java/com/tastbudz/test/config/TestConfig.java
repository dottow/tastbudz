package com.tastbudz.test.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

import com.tastbudz.dao.CuisineDAO;
import com.tastbudz.dao.DishDAO;
import com.tastbudz.dao.DrinkDAO;
import com.tastbudz.dao.RestaurantDAO;
import com.tastbudz.dao.UserDAO;
import com.tastbudz.dao.hibernate.CuisineDAOHibernate;
import com.tastbudz.dao.hibernate.DishDAOHibernate;
import com.tastbudz.dao.hibernate.DrinkDAOHibernate;
import com.tastbudz.dao.hibernate.RestaurantDAOHibernate;
import com.tastbudz.dao.hibernate.UserDAOHibernate;
import com.tastbudz.service.AccountService;
import com.tastbudz.service.MenuService;
import com.tastbudz.service.RestaurantService;
import com.tastbudz.service.impl.AccountServiceImpl;
import com.tastbudz.service.impl.MenuServiceImpl;
import com.tastbudz.service.impl.RestaurantServiceImpl;

@Configuration
@PropertySource("classpath:/com/tastbudz/test/datasource.properties")
@ComponentScan("com.tastbudz.model,com.tastbudz.service,com.tastbudz.dao")
@EnableTransactionManagement
public class TestConfig {
	@Autowired
	Environment env;
	
	@Bean(name="dataSource")
    public DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(getProperty("database.driver"));
        ds.setUsername(getProperty("database.username"));
        ds.setPassword(getProperty("database.password"));
        ds.setUrl(getProperty("database.url"));
        return ds;
    }
	
	@Bean
	public SessionFactory getSessionFactory() {
		LocalSessionFactoryBean factoryBean = null;
		try {
			factoryBean = new LocalSessionFactoryBean();
            Properties pp = new Properties();
            pp.setProperty("hibernate.dialect", getProperty("database.dialect"));
            pp.setProperty("hibernate.max_fetch_depth", "3");
            pp.setProperty("hibernate.show_sql", "true");
            pp.setProperty("hibernate.connection.pool_size", "5");
            pp.setProperty("hibernate.cache.use_query_cache", "false");
            pp.setProperty("hibernate.cache.use_second_level_cache", "true");
            pp.setProperty("hibernate.hbm2ddl.auto", getProperty("hbm2ddl.settings"));
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
	
	@Bean
	public RestaurantService getRestaurantService() {
		return new RestaurantServiceImpl();
	}
	@Bean
	public MenuService getMenuService() {
		return new MenuServiceImpl();
	}
	
	@Bean
	public AccountService getAccountService() {
		return new AccountServiceImpl();
	}
	
	/**
	 * DAO beans
	 */

	@Bean
	public UserDAO getUserDAO() {
		return new UserDAOHibernate();
	}
	@Bean
	public RestaurantDAO getRestaurantDAO() {
		return new RestaurantDAOHibernate();
	}
	@Bean
	public CuisineDAO getCuisineDAO() {
		return new CuisineDAOHibernate();
	}
	@Bean
	public DishDAO getDishDAO() {
		return new DishDAOHibernate();
	}
	@Bean
	public DrinkDAO getDrinkDAO() {
		return new DrinkDAOHibernate();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder("foo");
	}

	private String getProperty(String name) {
		return env.getProperty(name);
	}
}
