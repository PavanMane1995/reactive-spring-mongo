package com.practice.reactive.controller;

import com.practice.reactive.dto.ProductDto;
import com.practice.reactive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(path = "product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/get-all-products", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping(path = "/get-product", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ProductDto> getCustomer(String id){
        return productService.getProduct(id);
    }

    @PostMapping(path = "/save-product")
    public Mono<ProductDto> saveCustomer(@RequestBody Mono<ProductDto> productDto){
        return productService.saveProduct(productDto);
    }
}
