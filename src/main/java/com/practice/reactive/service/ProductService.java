package com.practice.reactive.service;

import com.practice.reactive.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

     Flux<ProductDto> getAllProducts();
     Mono<ProductDto> getProduct(String id);
     void saveProduct(Mono<ProductDto> pDto);
}
