package elagin.dmitrii.warehouse_service.repository;

import elagin.dmitrii.warehouse_service.repository.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringJUnitConfig(TestConfig.class)
@WebAppConfiguration
class ProductRepositoryTest {
    @Autowired
    private ProductRepository repository;

    @Test
    @Sql("classpath:data.sql")
    void findAll() {
        final var products = repository.findAll();

        assertNotNull(products);
        assertEquals(2, products.size());
    }
}