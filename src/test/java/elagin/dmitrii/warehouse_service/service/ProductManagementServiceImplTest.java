package elagin.dmitrii.warehouse_service.service;

import elagin.dmitrii.warehouse_service.dto.ProductResponse;
import elagin.dmitrii.warehouse_service.entities.Product;
import elagin.dmitrii.warehouse_service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;


@SpringJUnitWebConfig(TestServiceConfig.class)
@WebAppConfiguration
class ProductManagementServiceImplTest {

    @Autowired
    ProductRepository mockRepository;
    @Autowired
    ProductManagementService service;

    Product product;

    @BeforeEach
    void init() {
        product = new Product(1234L, "Name", BigDecimal.TEN);
    }

    @Test
    void findAllProducts() {
        Mockito.when(mockRepository.findAll()).thenReturn(List.of(product));
        final var products = service.findAllProducts();

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(new ProductResponse(product), products.get(0));
        verify(mockRepository).findAll();
    }

    @Test
    void findProductsByName() {
        Mockito.when(mockRepository.findByName(anyString())).thenReturn(List.of(product));
        final var products = service.findProductsByName("Name");

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(new ProductResponse(product), products.get(0));
        verify(mockRepository).findByName(anyString());
    }
}