package ro.delivery.products;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
//@EnableWebMvc
@ComponentScan(basePackages = { "ro.delivery.products"})
public class WebAppConfig implements WebMvcConfigurer {

    //bean - componenta care rep o setare;
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        //entitymanager - se ocupa cu operatii pe baza de date
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("ro.delivery.products");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    private Properties additionalProperties() {
        //specific dialectul adica lucram cu postgres ca baza de date
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        properties.setProperty("restart.include.all", ".*");

        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        // se ocupa cu tranzacii; in principiu insert si update
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public DataSource dataSource() {
        // specificam userul, parola si bd folosite in app
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            String username = "library";
            String password = "library";
            dataSource.setUrl("jdbc:postgresql://localhost:5432/library");
            dataSource.setUsername(username);
            dataSource.setPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

}