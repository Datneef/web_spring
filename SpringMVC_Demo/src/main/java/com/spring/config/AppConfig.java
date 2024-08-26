package com.spring.config;

import com.spring.filter.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("classpath:/image/");
    }

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:/i18n/message_validation",
                "classpath:/i18n/label"
        );
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }

    @Autowired
    MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/admin/**");
                                              /*.excludePathPatterns("/admin/books");*/
    }

    //    @Bean
//    public SessionFactory sessionFactory() throws PropertyVetoException {
//        LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource());
//        Properties props = new Properties();
//        props.put("hibernate.show_sql", true);
//        props.put("hibernate.format_sql", true);
//        props.put("hibernate.hbm2ddl.auto", "update");
//        props.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
//        sessionFactoryBuilder.scanPackages("com.spring.entities");
//        sessionFactoryBuilder.setProperties(props);
//        return sessionFactoryBuilder.buildSessionFactory();
//    }
//
//    @Bean
//    public DataSource dataSource() throws PropertyVetoException {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setUser("sa");
//        dataSource.setPassword("Huygau91");
//        dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        dataSource.setJdbcUrl("jdbc:sqlserver://localhost:1433;database=HN24_FJW01_SPRING;encrypt=true;trustServerCertificate=true");
//        return dataSource;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManagement() throws PropertyVetoException {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory());
//        return transactionManager;
//    }

//    @Bean
//    public MappingJackson2XmlHttpMessageConverter xmlConverter() {
//        return new MappingJackson2XmlHttpMessageConverter();
//    }
//
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(xmlConverter());
//    }
}
