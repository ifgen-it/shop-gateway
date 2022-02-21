package ru.avalon.gateway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.avalon.gateway.lib.dto.CategoryDto;
import ru.avalon.gateway.persistance.entity.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryDto source);

    CategoryDto toDto(Category source);
    List<CategoryDto> toDto(List<Category> source);
}
