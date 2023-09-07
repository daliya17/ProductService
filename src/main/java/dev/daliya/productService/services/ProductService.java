package dev.daliya.productService.services;

import dev.daliya.productService.dtos.GenericProductDto;
import dev.daliya.productService.exeptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto product);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(Long id) throws NotFoundException;

    GenericProductDto updateProductById(GenericProductDto product, Long id) throws NotFoundException;
}
