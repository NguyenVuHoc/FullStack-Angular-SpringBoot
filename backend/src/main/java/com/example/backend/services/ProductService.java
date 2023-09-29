package com.example.backend.services;

import com.example.backend.dtos.ProductDto;
import com.example.backend.entites.Product;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.ProductMapper;
import com.example.backend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> allProduct() {
        return productMapper.toProductDtos(productRepository.findAll());
    }

    public ProductDto createProduct(ProductDto ProductDto) {
        Product product = productMapper.toProduct(ProductDto);

        Product saveProduct = productRepository.save(product);

        return productMapper.toProductDto(saveProduct);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException("Product not found", HttpStatus.NOT_FOUND));

        productMapper.updateProduct(product, productMapper.toProduct(productDto));

        Product savedProduct = productRepository.save(product);

        return productMapper.toProductDto(savedProduct);
    }

    public ProductDto patchProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException("Product not found", HttpStatus.NOT_FOUND));

        if (productDto.getBrand() != null) {
            product.setBrand(productDto.getBrand());
        }
        if (productDto.getModel() != null) {
            product.setModel(productDto.getModel());
        }
        if (productDto.getYear() != 0) {
            product.setYear(productDto.getYear());
        }
        if (productDto.getColor() != null) {
            product.setColor(productDto.getColor());
        }

        Product savedProduct = productRepository.save(product);

        return productMapper.toProductDto(savedProduct);
    }

    public ProductDto deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException("Product not found", HttpStatus.NOT_FOUND));
        ProductDto productDto = productMapper.toProductDto(product);

        productRepository.deleteById(id);

        return productDto;
    }

    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException("Product not found", HttpStatus.NOT_FOUND));
        return productMapper.toProductDto(product);
    }
}
