package com.electronic.store.controllers;

import com.electronic.store.dtos.ApiResponseMessage;
import com.electronic.store.dtos.ProductDto;
import com.electronic.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //create
    @PostMapping("/createProduct")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto)
    {
        ProductDto createProduct=productService.create(productDto);
        return  new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto)
    {
        ProductDto updatedProduct=productService.update(productDto,productId);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseMessage> delete(String productId)
    {
        productService.delete(productId);
        ApiResponseMessage apiResponseMessage= ApiResponseMessage.builder().message("Product is deleted successfully").build();
        return new ResponseEntity<>(apiResponseMessage,HttpStatus.OK);
    }

    //getSingle
    @GetMapping("/getSingleProduct/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable String productId)
    {
        ProductDto productDto=productService.getSingle(productId);
        return  new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    //getAll
    @GetMapping("/getAllProduct")
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
            )
    {
     PageableResponse<ProductDto> pageableResponse=productService.getAll(pageNumber,pageSize,sortBy,sortDir);
     return  new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }

    //getAllLive
    @GetMapping("/getAllLiveProduct")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLiveProduct(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    )
    {
        PageableResponse<ProductDto> pageableResponse=productService.getAll(pageNumber,pageSize,sortBy,sortDir);
        return  new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }

    //search

    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct(
            @PathVariable String query,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    )
    {
        PageableResponse<ProductDto> pageableResponse=productService.searchByTitle(query,pageNumber,pageSize,sortBy,sortDir);
        return  new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }
}
