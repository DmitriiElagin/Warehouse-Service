package elagin.dmitrii.warehouse_service.controller;

import elagin.dmitrii.warehouse_service.config.TestControllerConfig;
import elagin.dmitrii.warehouse_service.dto.ProductResponse;
import elagin.dmitrii.warehouse_service.service.ProductManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig(TestControllerConfig.class)
@WebAppConfiguration
class ProductControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ProductManagementService service;

    @Autowired
    ProductController productController;

    MockMvc mockMvc;

    List<ProductResponse> products;

    @BeforeEach
    void init() {
        products = List.of(new ProductResponse(1234L, "Test", BigDecimal.TEN));
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void findAllProducts() throws Exception {
        Mockito.when(service.findAllProducts()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].name", is(products.get(0).getName())));

        verify(service).findAllProducts();
    }

    @Test
    void findAllProductsWithParameter() throws Exception {
        final String name = products.get(0).getName();

        Mockito.when(service.findProductsByName(name)).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products?name={name}", name))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].name", is(name)));

        verify(service).findProductsByName(name);
    }
}