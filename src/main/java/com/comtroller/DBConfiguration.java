package com.comtroller;

import java.util.Properties;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.hibernate.event.spi.PreCollectionRecreateEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class DBConfiguration {
@Value("${db.driver}")
private String DRIVER;
@Value("${db.url}")
private String URL;
@Value("${db.username}")
private String USERNAME;
@Value("${db.password}")
private String PASSWORD;
@Value("${hibernate.dialect}")
private String DIALECT;
@Value("${hibernate.show_sql}")
private boolean SHOW_SQL;
@Value("${hibernate.hbm2ddl.auto}")
private String HBM2DDL;
@Value("${entitymanager.packagesToScan}")
private String PACKAGES_TO_SCAN;
@Bean
public DataSource dataSource()
{
	DriverManagerDataSource ds=new DriverManagerDataSource();
	ds.setDriverClassName(DRIVER);
	ds.setUrl(URL);
	ds.setUsername(USERNAME);
	ds.setPassword(PASSWORD);
	return ds;
}
@Bean
public LocalSessionFactoryBean sessionFactory()
{
	LocalSessionFactoryBean sf=new LocalSessionFactoryBean();
	sf.setDataSource(dataSource());
	 sf.setPackagesToScan(PACKAGES_TO_SCAN);
	Properties p=new Properties();
	p.put("hibernate.dialect", DIALECT);
	p.put("hibernate.show_sql", SHOW_SQL);
	p.put("hibernate.hbm2ddl.auto",HBM2DDL );
	sf.setHibernateProperties(p);
	return sf;
}
@Bean
public InternalResourceViewResolver viewResolver()
{
	InternalResourceViewResolver irvr=new InternalResourceViewResolver();
	irvr.setPrefix("/WEB-INF/jsp/");
	irvr.setSuffix(".jsp");
	return irvr;
	}
@Bean
public HibernateTransactionManager transactionManager()
{
	HibernateTransactionManager htm=new HibernateTransactionManager();
	htm.setSessionFactory(sessionFactory().getObject());
	return htm;
}
	

}

