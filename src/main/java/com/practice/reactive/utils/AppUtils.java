package com.practice.reactive.utils;

import com.practice.reactive.dto.ProductDto;
import com.practice.reactive.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static Product dtoToEntity(ProductDto pDto){
        Product p = new Product();
        BeanUtils.copyProperties(pDto, p);
        return p;
    }

    public static ProductDto entityToDto(Product p){
        ProductDto pDto = new ProductDto();
        BeanUtils.copyProperties(p, pDto);
        return pDto;
    }
}
