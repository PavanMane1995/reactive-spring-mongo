package com.practice.reactive.service.Impl;

import com.practice.reactive.dto.ProductDto;
import com.practice.reactive.entity.Product;
import com.practice.reactive.repository.ProductRepository;
import com.practice.reactive.service.ProductService;
import com.practice.reactive.utils.AppUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public Flux<ProductDto> getAllProducts(){
        return productRepository
                .findAll()
                .map(AppUtils::entityToDto);

    }

    public Mono<ProductDto> getProduct(String id){
        return productRepository
                .findById(id)
                .map(AppUtils::entityToDto);
    }

    public void saveProduct(ProductDto pDto){
        Product p = AppUtils.dtoToEntity(pDto);
        productRepository.save(p).log();
    }
}
