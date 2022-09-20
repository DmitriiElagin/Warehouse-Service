package elagin.dmitrii.warehouse_service.service;

import elagin.dmitrii.warehouse_service.dto.WarehouseRequest;
import elagin.dmitrii.warehouse_service.dto.WarehouseResponse;

import java.util.List;

public interface WarehouseManagementService {
    List<WarehouseResponse> findAllWarehouses();

    WarehouseResponse saveWarehouse(WarehouseRequest request);

    void deleteWarehouse(WarehouseRequest request);
}
