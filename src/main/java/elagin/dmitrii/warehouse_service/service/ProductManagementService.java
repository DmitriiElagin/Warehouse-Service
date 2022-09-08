package elagin.dmitrii.warehouse_service.service;

import elagin.dmitrii.warehouse_service.dto.ProductResponse;

import java.util.List;

public interface ProductManagementService {
    List<ProductResponse> findAllProducts();

    List<ProductResponse> findProductsByName(String name);
}
