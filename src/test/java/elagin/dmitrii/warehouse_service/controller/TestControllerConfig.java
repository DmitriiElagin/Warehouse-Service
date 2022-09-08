package elagin.dmitrii.warehouse_service.controller;

import elagin.dmitrii.warehouse_service.service.ProductManagementService;
import elagin.dmitrii.warehouse_service.service.ProductManagementServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("elagin.dmitrii.warehouse_service.controller")
@EnableWebMvc
class TestControllerConfig {
    @Bean
    public ProductManagementService mockProductManagementService() {
        return Mockito.mock(ProductManagementServiceImpl.class);
    }
}
