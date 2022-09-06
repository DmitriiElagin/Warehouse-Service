package elagin.dmitrii.warehouse_service.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("elagin.dmitrii.warehouse_service")
public class TestConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final var factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPersistenceUnitName("test");
        factoryBean.setPackagesToScan("elagin.dmitrii.warehouse_service.entities");

        factoryBean.setJpaProperties(getAdditionalProperties());

        final var vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);


        return factoryBean;
    }

    @Bean
    public DataSource dataSource() {
        final var builder = new EmbeddedDatabaseBuilder();

        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    private Properties getAdditionalProperties() {
        final var properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
