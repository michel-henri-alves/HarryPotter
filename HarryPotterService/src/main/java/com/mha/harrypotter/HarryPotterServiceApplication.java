package com.mha.harrypotter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.mha.harrypotter.config.JpaConfiguration;

//@ServletComponentScan
@Import(JpaConfiguration.class) // importa as configurações de jpa
@SpringBootApplication(scanBasePackages = { "com.mha.harrypotter" })
public class HarryPotterServiceApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HarryPotterServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(HarryPotterServiceApplication.class, args);
	}
	
	
//	@Bean
//	@Description("Spring message resolver")
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("i18n/messages");
//
//		return messageSource;
//	}
//
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
//	}

//	// Register Servlet
//	@Bean
//	public ServletRegistrationBean servletRegistrationBean2() {
//		ServletRegistrationBean bean = new ServletRegistrationBean(new ITextPDFServlet(), "/test");
//		return bean;
//	}

//	//time dialec 
//	@Bean
//	public Java8TimeDialect java8TimeDialect() {
//		return new Java8TimeDialect();
//	}

	// Register ServletContextListener
//	@Bean
//	public ServletListenerRegistrationBean<ServletContextListener> listenerRegistrationBean() {
//		ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean<>();
//		bean.setListener(new MyServletContextListener());
//		return bean;
//
//	}

}
