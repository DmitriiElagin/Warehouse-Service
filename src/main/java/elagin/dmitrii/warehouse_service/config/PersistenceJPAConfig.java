package elagin.dmitrii.warehouse_service.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("resources/application.properties")
@EnableTransactionManagement
public class PersistenceJPAConfig {
    final private Environment environment;

    public PersistenceJPAConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        return new HikariDataSource(getHikariConfig());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final var factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(environment.getProperty("hibernate.packagesToScan"));
        factoryBean.setPersistenceUnitName("warehouse-service");

        final var vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);

        factoryBean.setJpaProperties(getAdditionalProperties());

        return factoryBean;
    }

    private Properties getAdditionalProperties() {
        final var properties = new Properties();
        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));

        return properties;
    }

    private HikariConfig getHikariConfig() {
        final var config = new HikariConfig();

        final var poolSize = environment.getProperty("datasource.main.pool-size");

        config.setDriverClassName(environment.getProperty("datasource.main.driver-class-name"));
        config.setJdbcUrl(environment.getProperty("datasource.main.jdbc-url"));
        config.setUsername(environment.getProperty("datasource.main.username"));
        config.setPassword(environment.getProperty("datasource.main.password"));

        if(poolSize != null) {
            config.setMaximumPoolSize(Integer.parseInt(poolSize));
        }

        return config;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
