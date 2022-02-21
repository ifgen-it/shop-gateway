package ru.avalon.gateway.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.avalon.gateway.lib.dto.CategoryDto;
import ru.avalon.gateway.mapper.CategoryMapper;
import ru.avalon.gateway.persistance.entity.Category;
import ru.avalon.gateway.persistance.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public Optional<CategoryDto> getById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::toDto);
    }

    public List<CategoryDto> getAll() {
        return categoryMapper.toDto(categoryRepository.findAll());
    }

    public CategoryDto create(CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }
}
