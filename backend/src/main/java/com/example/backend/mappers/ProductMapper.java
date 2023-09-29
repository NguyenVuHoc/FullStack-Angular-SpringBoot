package com.example.backend.mappers;

import com.example.backend.dtos.ProductDto;
import com.example.backend.entites.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtos(List<Product> vehicles);

    void updateProduct(@MappingTarget Product target, Product source);
}
