package elagin.dmitrii.warehouse_service.service;

import elagin.dmitrii.warehouse_service.repository.ProductRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan("elagin.dmitrii.warehouse_service.service")
class TestServiceConfig {
    @Bean
    public ProductRepository mockRepository() {
        return Mockito.mock(ProductRepository.class);
    }

    @Bean
    public EntityManagerFactory mockEntityManagerFactory() {
        return Mockito.mock(EntityManagerFactory.class);
    }
}
