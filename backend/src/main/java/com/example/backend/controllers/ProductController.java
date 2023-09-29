package com.example.backend.controllers;

import com.example.backend.dtos.ProductDto;
import com.example.backend.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/Products")
    public ResponseEntity<List<ProductDto>> allProducts() {
        return ResponseEntity.ok(productService.allProduct());
    }

    @PostMapping("/Products")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto ProductDto) {
        ProductDto createdProduct = productService.createProduct(ProductDto);
        return ResponseEntity.created(URI.create("/Products/" + ProductDto.getId())).body(createdProduct);
    }

    @GetMapping("/Products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PutMapping("/Products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto ProductDto) {
        return ResponseEntity.ok(productService.updateProduct(id, ProductDto));
    }

//    @PatchMapping("/Products/{id}")
//    public ResponseEntity<ProductDto> patchProduct(@PathVariable Long id, @RequestBody ProductDto ProductDto) {
//        return ResponseEntity.ok(ProductService.patchProduct(id, ProductDto));
//    }

    @DeleteMapping("/Products/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
