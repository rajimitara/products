package com.amazon.products.dao;

import com.amazon.products.ds.AmazonProduct;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<AmazonProduct,String> {
}
