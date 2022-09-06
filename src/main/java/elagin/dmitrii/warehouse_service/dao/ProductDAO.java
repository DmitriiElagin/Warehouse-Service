package elagin.dmitrii.warehouse_service.dao;

import elagin.dmitrii.warehouse_service.entities.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();

    List<Product> findByName(String name);
}
