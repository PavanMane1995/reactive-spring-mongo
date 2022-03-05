package com.practice.reactive.service.Impl;

import com.practice.reactive.dto.ProductDto;
import com.practice.reactive.entity.Product;
import com.practice.reactive.repository.ProductRepository;
import com.practice.reactive.service.ProductService;
import com.practice.reactive.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public Flux<ProductDto> getAllProducts(){
        Query q = new Query();
        Criteria a = Criteria.where("name").is("pavan");
        q.addCriteria(a);
        return productRepository
                .findAll().log()
                .map(AppUtils::entityToDto);

    }

    public Mono<ProductDto> getProduct(String id){
        return productRepository
                .findById(id)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> pDto){
       return pDto.map(AppUtils::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto)
               .onErrorResume(e -> {return Mono.just(new ProductDto());});

    }
}
