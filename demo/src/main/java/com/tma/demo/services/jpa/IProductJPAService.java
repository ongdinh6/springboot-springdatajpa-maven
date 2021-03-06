package com.tma.demo.services.jpa;

import com.tma.demo.dtos.responses.ProductResponse;
import com.tma.demo.entities.jpa.ProductJPA;

import java.util.List;

public interface IProductJPAService {
    ProductJPA save(ProductJPA productJpa);
    List<ProductJPA> getAllProduct();
    List<ProductJPA> getAllProductByQueryDsl(String clazz);
    ProductJPA getById(String productId);


}
