package ru.avalon.gateway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.avalon.gateway.lib.dto.ProductDto;
import ru.avalon.gateway.persistance.entity.Product;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Mapper
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "image", ignore = true)
    Product toEntity(ProductDto source);

    @Mapping(target = "imageBase64", source = "image", qualifiedByName = "toDtoImageResolver")
    ProductDto toDto(Product source);
    List<ProductDto> toDto(List<Product> source);

    @Named("toDtoImageResolver")
    static String toDtoImageResolver(byte[] image) {
        if (image == null)
            return null;
        byte[] encodedBase64 = Base64.getEncoder().encode(image); //Base64.encode(image);
        return new String(encodedBase64, StandardCharsets.UTF_8);
    }
}
