package elagin.dmitrii.warehouse_service.repository;

import elagin.dmitrii.warehouse_service.config.TestRepositoryConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(TestRepositoryConfig.class)
@WebAppConfiguration
@Transactional
@Sql(value = "classpath:data.sql")
class ProductRepositoryTest {
    @Autowired
    private ProductRepository repository;

    @Test
    void findAll() {
        final var products = repository.findAll();

        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    void findByName() {
        var products = repository.findByName("test");

        assertNotNull(products);
        assertTrue(products.isEmpty());

        products = repository.findByName("Product1");

        assertNotNull(products);
        assertEquals(1, products.size());
    }
}