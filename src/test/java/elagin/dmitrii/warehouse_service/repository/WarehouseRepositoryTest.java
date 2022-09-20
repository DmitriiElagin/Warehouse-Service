package elagin.dmitrii.warehouse_service.repository;

import elagin.dmitrii.warehouse_service.entities.Warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig(TestRepositoryConfig.class)
@Transactional
@Sql(value = "classpath:data.sql")
class WarehouseRepositoryTest {
    @Autowired
    private WarehouseRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void findAll() {
        final var warehouses = repository.findAll();

        assertNotNull(warehouses);
        assertEquals(2, warehouses.size());
    }

    @Test
    void save() {
        final var warehouse = repository.save("Test");
        final var warehouses = entityManager.createQuery("from Warehouse", Warehouse.class).getResultList();
        assertNotNull(warehouse);
        assertEquals(3, warehouses.size());
        assertTrue(warehouses.contains(warehouse));
    }

    @Test
    void update() {
        final var name = "Test";
        final var warehouse = repository.update("Warehouse2", name);

        final var warehouses = entityManager.createQuery("from Warehouse", Warehouse.class).getResultList();

        assertNotNull(warehouse);

        assertEquals(2, warehouses.size());
        assertTrue(warehouses.contains(warehouse));
    }

    @Test
    void updateShouldThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> repository.update("test", "test"));
    }

    @Test
    void delete() {
        final var warehouse = new Warehouse("Warehouse2");

        repository.delete(warehouse.getName());

        final var warehouses = entityManager.createQuery("from Warehouse", Warehouse.class).getResultList();

        assertEquals(1, warehouses.size());
        assertFalse(warehouses.contains(warehouse));
    }

    @Test
    void deleteShouldThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> repository.delete("test"));
    }
}