package ru.avalon.gateway.lib.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.avalon.gateway.lib.dto.CategoryDto;

import java.util.List;

public interface ICategoryClient {

    @GetMapping("/api/v1/category/{categoryId}")
    ResponseEntity<CategoryDto> getById(@PathVariable Long categoryId);

    @GetMapping("/api/v1/categories")
    List<CategoryDto> getAll();

    @PostMapping("/api/v1/category")
    ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto);

}
