package com.mha.harrypotter.config;

/**
 * JPA configuration class
 * 
 * @author michel
 * @version 0.0.1
 * 
 */

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;


@Configuration //indica que é classe de configuração
@EnableJpaRepositories(basePackages = "com.mha.harrypotter.repositories", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager") //dados do repository spring data
@EnableTransactionManagement
public class JpaConfiguration {
	
	@Autowired
	private Environment environment;

	@Value("${datasource.sampleapp.maxPoolSize:10}")
	private int maxPoolSize;
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.sampleapp")
	public DataSourceProperties dataSourceProperties(){
		return new DataSourceProperties();
	}
	
	/*
	 * Configure HikariCP pooled DataSource.
	 */
	@Bean
	public DataSource dataSource() {
		DataSourceProperties dataSourceProperties = dataSourceProperties();
			HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
					.create(dataSourceProperties.getClassLoader())
					.driverClassName(dataSourceProperties.getDriverClassName())
					.url(dataSourceProperties.getUrl())
					.username(dataSourceProperties.getUsername())
					.password(dataSourceProperties.getPassword())
					.type(HikariDataSource.class)
					.build();
			dataSource.setMaximumPoolSize(maxPoolSize);
			return dataSource;
	}
	
	/*
	 * Entity Manager Factory setup.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan(new String[] { "com.mha.harrypotter.model" });
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}

	/*
	 * Provider specific adapter.
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		return hibernateJpaVendorAdapter;
	}

	/*
	 * Here you can specify any provider specific properties.
	 */
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("datasource.sampleapp.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.sampleapp.hibernate.hbm2ddl.method"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.sampleapp.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("datasource.sampleapp.hibernate.format_sql"));
		if(StringUtils.isNotEmpty(environment.getRequiredProperty("datasource.sampleapp.defaultSchema"))){
			properties.put("hibernate.default_schema", environment.getRequiredProperty("datasource.sampleapp.defaultSchema"));
		}
		return properties;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
	
//	@Bean
//	  public EmbeddedServletContainerFactory servletContainer() {
//	    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//	        @Override
//	        protected void postProcessContext(Context context) {
//	          SecurityConstraint securityConstraint = new SecurityConstraint();
//	          securityConstraint.setUserConstraint("CONFIDENTIAL");
//	          SecurityCollection collection = new SecurityCollection();
//	          collection.addPattern("/*");
//	          securityConstraint.addCollection(collection);
//	          context.addConstraint(securityConstraint);
//	        }
//	      };
//	    
//	    tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//	    return tomcat;
//	  }
//	  
//	  private Connector initiateHttpConnector() {
//	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//	    connector.setScheme("http");
//	    connector.setPort(8080);
//	    connector.setSecure(false);
//	    connector.setRedirectPort(8443);
//	    
//	    return connector;
//	  }
	

}
