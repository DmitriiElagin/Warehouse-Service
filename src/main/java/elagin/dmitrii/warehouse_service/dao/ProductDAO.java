package elagin.dmitrii.warehouse_service.dao;

import elagin.dmitrii.warehouse_service.entities.Product;

import java.util.Set;

public interface ProductDAO {
    Set<Product> findAll();


}
