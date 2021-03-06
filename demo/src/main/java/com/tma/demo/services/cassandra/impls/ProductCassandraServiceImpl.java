package com.tma.demo.services.cassandra.impls;

import com.tma.demo.dtos.requests.ProductRequest;
import com.tma.demo.dtos.responses.ProductResponse;
import com.tma.demo.entities.cassandra.Product;
import com.tma.demo.exceptions.InternalServerException;
import com.tma.demo.exceptions.NotFoundException;
import com.tma.demo.repositories.cassandra.IProductCassandraRepository;
import com.tma.demo.services.cassandra.IProductCassandraService;
import com.tma.demo.utils.DateTimeUtil;
import com.tma.demo.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductCassandraServiceImpl implements IProductCassandraService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    LogUtil logUtil = LogUtil.getInstance();
    @Autowired
    IProductCassandraRepository productCassandraRepository;

    @Override
    public ProductResponse save(ProductRequest productRequest) {
        logger.debug("start:"+new DateTimeUtil().getDateTime() +this.getClass()+"- save() method");
        Product productToSave;
        if (null == (productToSave = productCassandraRepository.save(productRequest.toCassandraObject()))) {
            logger.error("Server cannot save product. Error Internal Server Exception.");
            logger.debug("end:"+new DateTimeUtil().getDateTime() +this.getClass()+"- save() method");
            logUtil.setLogUtil(this.getClass()+"- error: Internal Server Exception", logger);
            throw new InternalServerException("Server was be failed to save product !");
        } else {
            logger.debug("end:"+new DateTimeUtil().getDateTime() +this.getClass()+"- save() method");
            return productToSave.toProductResponse();
        }
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductResponse> products = new ArrayList<>();
        productCassandraRepository.findAll().forEach(product -> {
            products.add(product.toProductResponse());
        });
        return products;
    }

    @Override
    public Product getById(UUID productId) {
        Product product = productCassandraRepository.getByProductId(productId);
        if(null == product) {
            logger.warn("Product not found with id "+productId);
            logUtil.setLogUtil(this.getClass()+"- error: Not Found Exception", logger);
            throw new NotFoundException("Product not found with id: "+productId);
        }else {
            return product;
        }
    }

    @Override
    public Product findByClazz(String clazz) {
        return productCassandraRepository.findByClazz(clazz);
    }
}
