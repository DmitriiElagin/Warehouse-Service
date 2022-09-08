package elagin.dmitrii.warehouse_service.controller;

import elagin.dmitrii.warehouse_service.dto.ProductResponse;
import elagin.dmitrii.warehouse_service.service.ProductManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductManagementService service;

    public ProductController(ProductManagementService service) {
        this.service = service;
    }

    @GetMapping()
    public List<ProductResponse> findProducts(@RequestParam(required = false) String name) {
        if (name != null && !name.isBlank()) {
            return service.findProductsByName(name);
        }
        return service.findAllProducts();
    }
}
