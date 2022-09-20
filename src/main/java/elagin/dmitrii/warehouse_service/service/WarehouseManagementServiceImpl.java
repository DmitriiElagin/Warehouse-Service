package elagin.dmitrii.warehouse_service.service;

import elagin.dmitrii.warehouse_service.dao.WarehouseDAO;
import elagin.dmitrii.warehouse_service.dto.WarehouseRequest;
import elagin.dmitrii.warehouse_service.dto.WarehouseResponse;
import elagin.dmitrii.warehouse_service.entities.Warehouse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseManagementServiceImpl implements WarehouseManagementService {
    private final WarehouseDAO dao;

    public WarehouseManagementServiceImpl(WarehouseDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<WarehouseResponse> findAllWarehouses() {
        return dao.findAll().stream()
                .map(warehouse -> new WarehouseResponse(warehouse.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseResponse saveWarehouse(WarehouseRequest request) {
        Warehouse warehouse;

        if (request.getOldName() != null && !request.getOldName().isBlank()) {
            warehouse = dao.update(request.getOldName(), request.getName());
        } else {
            warehouse = dao.save(request.getName());
        }

        return new WarehouseResponse(warehouse.getName());
    }

    @Override
    public void deleteWarehouse(WarehouseRequest request) {
        dao.delete(request.getName());
    }
}
