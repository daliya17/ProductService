package dev.daliya.productService.services;

import dev.daliya.productService.dtos.CategoryDto;
import dev.daliya.productService.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

  List<CategoryDto> getAllCategories();

  CategoryDto getCategoryById(UUID id);
}
