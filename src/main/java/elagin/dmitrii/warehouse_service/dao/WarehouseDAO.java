package elagin.dmitrii.warehouse_service.dao;

import elagin.dmitrii.warehouse_service.entities.Warehouse;

import java.util.List;

public interface WarehouseDAO {
    List<Warehouse> findAll();

    Warehouse save(String name);

    Warehouse update(String oldName, String newName);

    void delete(String name);
}
