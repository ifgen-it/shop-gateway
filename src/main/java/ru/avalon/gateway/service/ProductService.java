package ru.avalon.gateway.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.avalon.gateway.lib.dto.ProductDto;
import ru.avalon.gateway.mapper.ProductMapper;
import ru.avalon.gateway.persistance.entity.Category;
import ru.avalon.gateway.persistance.entity.Product;
import ru.avalon.gateway.persistance.repository.CategoryRepository;
import ru.avalon.gateway.persistance.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductMapper productMapper;

    public Optional<ProductDto> getById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toDto);
    }

    public List<ProductDto> getAll() {
        return productMapper.toDto(productRepository.findAll());
    }

    public ProductDto create(ProductDto productDto, MultipartFile image) {
        byte[] imageBytes = getImageBytes(image);
        Long categoryId = productDto.getCategory().getId();
        // todo возвращать ошибку BadRequest
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Категория не найдена: " + categoryId));
        Product product = productMapper.toEntity(productDto);
        product.setCategory(category)
                .setImage(imageBytes);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    private byte[] getImageBytes(MultipartFile image) {
        if (image == null)
            return null;
        byte[] imageBytes = null;
        try {
            if (!image.isEmpty())
                imageBytes = image.getBytes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return imageBytes;
    }

    public ProductDto update(ProductDto productDto, MultipartFile image) {
        byte[] imageBytes = getImageBytes(image);
        Long categoryId = productDto.getCategory().getId();
        // todo возвращать ошибку BadRequest
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Категория не найдена: " + categoryId));
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Продукт не найден: " + productDto.getId())); // todo BadRequest
        product.setCategory(category)
                .setName(productDto.getName())
                .setPrice(productDto.getPrice())
                .setWeight(productDto.getWeight())
                .setDescription(productDto.getDescription());
        if (imageBytes != null)
            product.setImage(imageBytes);
        return productMapper.toDto(productRepository.save(product));
    }
}
