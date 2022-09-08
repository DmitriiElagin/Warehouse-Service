package elagin.dmitrii.warehouse_service.service;

import elagin.dmitrii.warehouse_service.dao.ProductDAO;
import elagin.dmitrii.warehouse_service.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductManagementServiceImpl implements ProductManagementService {
    final private ProductDAO dao;

    public ProductManagementServiceImpl(ProductDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return dao.findAll().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findProductsByName(String name) {
        return dao.findByName(name).stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }
}
