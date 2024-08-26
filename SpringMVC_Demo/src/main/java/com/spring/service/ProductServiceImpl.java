package com.spring.service;

import com.spring.model.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductDTO> getAll() {
        //TODO remove fake data
        ArrayList<ProductDTO> products = new ArrayList<>();
        products.add(
                ProductDTO.builder()
                        .id(1)
                        .name("product1")
                        .price(2000.0)
                        .image("/image/product1.webp")
                        .build()
        );
        products.add(
                ProductDTO.builder()
                        .id(2)
                        .name("product2")
                        .price(4000.0)
                        .image("/image/product2.jpg")
                        .build()
        );
        return products;
    }
}
