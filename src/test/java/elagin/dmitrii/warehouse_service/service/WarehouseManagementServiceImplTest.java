package elagin.dmitrii.warehouse_service.service;

import elagin.dmitrii.warehouse_service.dto.WarehouseRequest;
import elagin.dmitrii.warehouse_service.dto.WarehouseResponse;
import elagin.dmitrii.warehouse_service.entities.Warehouse;
import elagin.dmitrii.warehouse_service.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitWebConfig(TestServiceConfig.class)
@WebAppConfiguration
class WarehouseManagementServiceImplTest {

    @Autowired
    WarehouseRepository mockWarehouseRepository;

    @Autowired
    WarehouseManagementServiceImpl service;

    Warehouse warehouse;

    @BeforeEach
    void init() {
        warehouse = new Warehouse("Test Warehouse");

    }

    @Test
    void findAllWarehouses() {
        Mockito.when(mockWarehouseRepository.findAll()).thenReturn(List.of(warehouse));

        final var warehouses = service.findAllWarehouses();

        assertNotNull(warehouses);
        assertEquals(1, warehouses.size());
        assertEquals(new WarehouseResponse(warehouse.getName()), warehouses.get(0));

        Mockito.verify(mockWarehouseRepository).findAll();
    }

    @Test
    void saveWarehouse() {
        final var request = new WarehouseRequest("New Warehouse");

        Mockito.when(mockWarehouseRepository.save(request.getName())).thenReturn(new Warehouse(request.getName()));

        final var response = service.saveWarehouse(request);

        assertNotNull(response);
        assertEquals(request.getName(), response.getName());

        Mockito.verify(mockWarehouseRepository).save(request.getName());
    }

    @Test
    void saveShouldUpdateWarehouse() {
        final var request = new WarehouseRequest(warehouse.getName(), "New Warehouse");

        Mockito.when(mockWarehouseRepository.update(request.getOldName(), request.getName()))
                .thenReturn(new Warehouse(request.getName()));

        final var response = service.saveWarehouse(request);

        assertNotNull(response);
        assertEquals(request.getName(), response.getName());

        Mockito.verify(mockWarehouseRepository).update(request.getOldName(), request.getName());
    }

    @Test
    void deleteWarehouse() {
        service.deleteWarehouse(new WarehouseRequest(warehouse.getName()));

        Mockito.verify(mockWarehouseRepository).delete(warehouse.getName());
    }
}