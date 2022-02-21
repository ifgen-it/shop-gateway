package ru.avalon.gateway.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.avalon.gateway.lib.client.IProductClient;
import ru.avalon.gateway.lib.dto.ProductDto;
import ru.avalon.gateway.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController implements IProductClient {

    ProductService productService;

    @Override
    public ResponseEntity<ProductDto> getById(@PathVariable Long productId) {
        Optional<ProductDto> product = productService.getById(productId);
        if (product.isPresent())
            return ResponseEntity.of(product);
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    /**
     * @param image - мб null - значит сохраняем без картинки
     */
    @Override
    public ResponseEntity<ProductDto> create(@RequestPart ProductDto productDto,
                                             @RequestPart @Nullable MultipartFile image) {
        ProductDto product = productService.create(productDto, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @Override
    public ResponseEntity<ProductDto> update(@RequestPart ProductDto productDto,
                                             @RequestPart @Nullable MultipartFile image) {
        ProductDto updatedProduct = productService.update(productDto, image);
        return ResponseEntity.ok(updatedProduct);
    }
}
