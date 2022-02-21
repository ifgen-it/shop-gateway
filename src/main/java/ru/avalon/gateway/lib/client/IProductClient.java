package ru.avalon.gateway.lib.client;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.avalon.gateway.lib.dto.ProductDto;

import java.util.List;

public interface IProductClient {

    @GetMapping("/api/v1/product/{productId}")
    ResponseEntity<ProductDto> getById(@PathVariable Long productId);

    @GetMapping("/api/v1/products")
    List<ProductDto> getAll();

    @PostMapping(path = "/api/v1/product", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    ResponseEntity<ProductDto> create(@RequestPart ProductDto productDto, @RequestPart MultipartFile image);

    @PutMapping(path = "/api/v1/product", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    ResponseEntity<ProductDto> update(@RequestPart ProductDto productDto, @RequestPart MultipartFile image);

}
