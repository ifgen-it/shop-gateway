package ru.avalon.gateway.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.avalon.gateway.lib.client.ICategoryClient;
import ru.avalon.gateway.lib.dto.CategoryDto;
import ru.avalon.gateway.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController implements ICategoryClient {

    CategoryService categoryService;

    @Override
    public ResponseEntity<CategoryDto> getById(@PathVariable Long categoryId) {
        Optional<CategoryDto> category = categoryService.getById(categoryId);
        if (category.isPresent())
            return ResponseEntity.of(category);
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryService.getAll();
    }

    @Override
    public ResponseEntity<CategoryDto> create(CategoryDto categoryDto) {
        CategoryDto category = categoryService.create(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
}
