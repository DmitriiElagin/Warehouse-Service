package elagin.dmitrii.warehouse_service.config;

import elagin.dmitrii.warehouse_service.repository.ProductRepository;
import elagin.dmitrii.warehouse_service.service.ProductManagementService;
import elagin.dmitrii.warehouse_service.service.ProductManagementServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class TestServiceConfig {
    @Bean
    public ProductRepository mockRepository() {
        return Mockito.mock(ProductRepository.class);
    }

    @Bean
    public ProductManagementService productManagementService() {
        return new ProductManagementServiceImpl(mockRepository());
    }

    @Bean
    public EntityManagerFactory mockEntityManagerFactory() {
        return Mockito.mock(EntityManagerFactory.class);
    }
}
